package com.example.routes

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.logging.*
import pl.jutupe.ktor_rabbitmq.RabbitMQ
import pl.jutupe.ktor_rabbitmq.consume
import pl.jutupe.ktor_rabbitmq.publish
import pl.jutupe.ktor_rabbitmq.rabbitConsumer

fun Application.configureRabbitMQ(log: Logger) {

    install(RabbitMQ) {
        log.info("Starting RabbiMQ Adapter....")
        uri = "amqp://guest:guest@localhost:5672"
        connectionName = "Connection name"

        enableLogging()

        //serialize and deserialize functions are required
        serialize { jacksonObjectMapper().writeValueAsBytes(it) }
        deserialize { bytes, type -> jacksonObjectMapper().readValue(bytes, type.javaObjectType) }

        //example initialization logic (create queues etc.)
        initialize { // RabbitMQ Channel.() block ->
            exchangeDeclare(/* exchange = */ "exchangeTopic", /* type = */ "topic", /* durable = */ true)
            queueDeclare(
                /* queue = */ "queueTopic",
                /* durable = */true,
                /* exclusive = */false,
                /* autoDelete = */false,
                /* arguments = */emptyMap()
            )
            queueBind(/* queue = */ "queueTopic", /* exchange = */ "exchangeTopic", /* routingKey = */ "message.#")

        }
        log.info("RabbiMQ Adapter initialized....")

        //consume with autoack example

    }
    // publish
    //pubblish()
    // subscribe
    //consume(log)
/*    rabbitConsumer {
        consume<Any>("queueTopic") { body ->
            log.info("Consumed message $body")
        }
    }*/
    rabbitConsumer {
        withChannel {
            basicConsume(
                "queueTopic",
                true,
                { consumerTag, message ->
                    runCatching {
                        val mappedEntity = deserialize<String>(message.body)
                        log.info("MappedEntity: $mappedEntity, \n " +
                        "routingKey: ${message.envelope.routingKey}")
                    }.getOrElse { throwable ->
                        log.error(
                            "DeliverCallback error: (" +
                                    "messageId = ${message.properties.messageId}, " +
                                    "consumerTag = $consumerTag)",
                            throwable,
                        )
                    }
                },
                { consumerTag ->
                    logger?.error("Consume cancelled: (consumerTag = $consumerTag)")
                }
            )
        }
    }
    routing {
        post("/pub") {
            call.publish("exchangeTopic", "message.bouete", null, call.receive(String::class))
            call.respondText("OK")
        }
    }

}