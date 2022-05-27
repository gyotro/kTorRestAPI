package com.example.routes

import com.example.models.ApiResponse
import com.example.plugins.ApacheClient
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.server.application.*
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.logging.*
import kotlinx.serialization.Serializable
import org.koin.ktor.ext.inject

fun Route.getFromClient(log: Logger) {
    val testEnv = System.getenv("TEST_ENV") ?: "Error"
    val testEnv2 = environment?.config?.propertyOrNull("ktor.environment.trasm")?.getString() ?: "nada"
    val apacheClient by inject<ApacheClient>()
    get("/client") {
        try {
            val responseClient = apacheClient.client.get("https://reqbin.com/echo/get/json").bodyAsText()
            log.info("Response from service $responseClient")
            call.respond(status = HttpStatusCode.OK, message = responseClient)
        }
        catch (e: Exception) {
            val response = ApiResponse(status = "error", message = e.message)
            call.respond(status = HttpStatusCode.BadRequest, message = response)
        }
    }
    get("/pingpong") {
        call.respond(status = HttpStatusCode.OK, message = Pingpong("ping", "pong"))
    }
    get("/env"){
        call.respond(status = HttpStatusCode.OK, message = "Environment variable read is $testEnv2")
    }
}
@Serializable
data class Pingpong(val ping: String, val pong: String)