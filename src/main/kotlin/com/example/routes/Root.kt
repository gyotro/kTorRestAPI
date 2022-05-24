package com.example.routes

import io.ktor.server.application.*
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import pl.jutupe.ktor_rabbitmq.publish

fun Route.routes() {
    get("/") {
        call.respondText("Welkome to Boruto API!", contentType = ContentType.Text.Plain, HttpStatusCode.OK)
    }

}
