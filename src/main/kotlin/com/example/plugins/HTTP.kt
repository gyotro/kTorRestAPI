package com.example.plugins

import io.ktor.http.*
import io.ktor.server.plugins.defaultheaders.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import java.time.Duration

fun Application.configureHTTP() {
    install(DefaultHeaders)
    {
            val oneYearInSeconds = Duration.ofDays(365).seconds
            header(
                name = HttpHeaders.CacheControl,
                value = "public, max-age=$oneYearInSeconds, immutable"
            )

        } // will send this header with each response
    }
