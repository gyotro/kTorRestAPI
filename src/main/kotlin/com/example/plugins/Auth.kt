package com.example.plugins

import io.ktor.server.application.*
import io.ktor.server.auth.*

fun Application.configureAuthentication() {
    install(Authentication) {
        basic("auth-basic") {
            // Configure basic authentication
            realm = "Access to the '/' path"
            validate { credentials ->
                if (credentials.name == "gyo" && credentials.password == "gyo") {
                    UserIdPrincipal(credentials.name)
                } else {
                    null
                }
            }
        }
    }
}