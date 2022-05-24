package com.example.plugins

import com.example.routes.books
import com.example.routes.getAllHeroes
import com.example.routes.getFromClient
import com.example.routes.routes
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.util.logging.*

fun Application.configureRouting(log: Logger) {
    routing {
        trace { application.log.trace(it.buildText()) }
        routes()
        getAllHeroes()
        getFromClient(log)
        books(log)
    }
}
