package com.example.plugins

import io.ktor.server.application.*
import io.ktor.server.routing.*
import pl.jutupe.ktor_rabbitmq.RabbitMQ
import pl.jutupe.ktor_rabbitmq.publish

fun Application.pubblish() {
//publish example
    routing {
        get("/pub") {
            call.publish("exchangeTopic", "routingKey", null, "test message")
        }
    }
}
