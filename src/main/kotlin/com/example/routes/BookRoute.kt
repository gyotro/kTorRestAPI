package com.example.routes

import com.example.models.Book
import com.example.models.DataManager
import io.ktor.server.application.*
import io.ktor.http.*
import io.ktor.resources.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.routing.get
import io.ktor.util.logging.*
import kotlinx.serialization.Serializable
import org.koin.ktor.ext.inject

fun Route.books(log: Logger) {
    val dataManager by inject<DataManager>()
    authenticate("auth-basic") {
        route("/book") {
            get<BookListLocation>() {
                call.respond(HttpStatusCode.OK, dataManager.sortedBooks(it.sortby, it.asc))
            }
            route("/{id}", HttpMethod.Get) {
                handle {
                    val id = call.parameters["id"]
                    log.info("Get by id: $id")
                    call.respond(HttpStatusCode.OK, dataManager.getBookById(id) ?: "No book selected")
                }
            }
            get("") {
                log.info("Get all Books")
                val user = call.principal<UserIdPrincipal>()?.name ?: "no auth"
                log.info("Logged ad user: $user")
                call.respond(HttpStatusCode.OK, dataManager.getAllBook())
            }
            put("") {
                val book = call.receive(Book::class)
                dataManager.updateBook(book)
                call.respond(HttpStatusCode.OK, "The book has been updated")
            }
            post("") {
                val book = call.receive(Book::class)
                val newBook = dataManager.addBook(book)
                call.respond(HttpStatusCode.OK, newBook)
            }
            delete("/{id}") {
                val id = call.parameters["id"]
                log.info("Delete by id: $id")
                val deletedBook = dataManager.deleteBook(id)
                call.respond(HttpStatusCode.OK, deletedBook ?: "No book found")
            }
        }
    }
}
@Serializable
@Resource("/list")
data class BookListLocation(val sortby: String, val asc: Boolean = true)
