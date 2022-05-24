package com.example.plugins

import io.ktor.server.application.*
import io.ktor.util.logging.*
import pl.jutupe.ktor_rabbitmq.consume
import pl.jutupe.ktor_rabbitmq.rabbitConsumer

fun Application.consume(log: Logger) {
    log.info("Starting Consumer")
val autoAck = false
//consume with autoack example
    rabbitConsumer {
/*        this.withChannel {
            basicConsume(
                "queueTopic",
                autoAck,
                { consumerTag, message ->
                    runCatching {
                        val mappedEntity = deserialize<String>(message.body)
                        logger?.info("MappedEntity: $mappedEntity, /n" +
                        "routingKey: ${message.envelope.routingKey}")
                    }.getOrElse { throwable ->
                        logger?.error(
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
        }*/
        consume<String>("queueTopic") { body ->
            log.info("Consumed message $body")
        }
    }
}