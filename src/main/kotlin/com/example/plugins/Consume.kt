package com.example.plugins

import io.ktor.server.application.*
import io.ktor.util.logging.*
import pl.jutupe.ktor_rabbitmq.consume
import pl.jutupe.ktor_rabbitmq.rabbitConsumer

fun Application.consume(log: Logger) {
    log.info("Starting Consumer")
    val autoAck = true
    rabbitConsumer {
        withChannel {
            basicConsume(
                "queueTopic",
                autoAck,
                // DeliveryCallback
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
                    log.error("Consume cancelled: (consumerTag = $consumerTag)")
                }
            )
        }
    }
//consume with autoack example

/*        consume<String>("queueTopic") { body ->
            log.info("Consumed message $body")
        }*/
    }
