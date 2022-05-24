package com.example.routes

import com.example.models.ApiResponse
import io.ktor.server.application.*
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getAllHeroes() {
    get("/boruto/heroes") {
        try {
            val page = call.request.queryParameters["page"]?.toInt() ?: 1
            require(page in 1..5) // throws an IllegalArgumentException
            call.respondText { page.toString() }
        }
        catch (e: Exception) {
            val response = ApiResponse(status = "error", message = e.message)
            call.respond(status = HttpStatusCode.BadRequest, message = response)
        }
    }
}
