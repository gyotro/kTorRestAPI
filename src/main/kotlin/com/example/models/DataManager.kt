package com.example.models

import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.memberProperties

class DataManager {

    private var books = ArrayList<Book>()
    private fun gimmeId() = books.size.toString()

    init {
        books.add(Book(gimmeId(), "Titolo1", "Gyo", 100.0f))
        books.add(Book(gimmeId(), "Titolo2", "ryo2", 134.0f))
        books.add(Book(gimmeId(), "Titolo3", "fyo3", 109.0f))
        books.add(Book(gimmeId(), "Titolo4", "tyo4", 50.0f))
        books.add(Book(gimmeId(), "Titolo5", "yyo5", 140.0f))
        books.add(Book(gimmeId(), "Titolo6", "ayo6", 14.0f))
        books.add(Book(gimmeId(), "Titolo7", "ryo7", 19.90f))

    }
    fun addBook(book: Book): Book {
        book.id = Math.random().toString()
        books.add(book)
        return book
    }
    fun updateBook(book: Book): Book?
    {
        val foundBook = books.find {
                it.id == book.id
            }
        return foundBook?.apply {
            title = book.title
            author = book.author
            price = book.price
        }
    }
    fun deleteBook(id: String?): Book? {
        val foundBook = books.find {
            it.id == id
        }
        books.remove(foundBook)
        return foundBook
    }
    fun getAllBook() = books
    fun getBookById(id: String?) = books.find { it.id == id }
    fun sortedBooks(sortby: String, asc: Boolean): List<Book> {
        // usiamo le librerie di Java Reflection
        val member = Book::class.declaredMemberProperties.find { it.name == sortby } ?: return getAllBook()
        val sortedList = when (asc) {
            true -> books.sortedBy { member.get(it).toString() }
            false -> books.sortedByDescending { member.get(it).toString()  }

        }
        return sortedList
    }
}

