package com.example.plugins

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import pl.jutupe.ktor_rabbitmq.RabbitMQ
import pl.jutupe.ktor_rabbitmq.publish

fun Application.pubblish() {
//publish example
    routing {
        post("/pub") {
            call.publish("exchangeTopic", "message.bouete", null, call.receive(String::class))
            call.respondText("OK")
        }
    }
}
