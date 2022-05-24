package com.example.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@Serializable
data class Book(
    var id: String = "",
    @JsonNames("title", "name") // indica che il Json in input può avere più nomi
    var title: String,
    @SerialName("autoreLibro") // è più forte dell'annotazione precedente: indica che il Json in entrata ed uscita avrà quel nome
    var author: String,
    var price: Float
)
@Serializable
data class ShoppingCart(val id: String, val userId: String, val items: ArrayList<ShoppingItem>)
@Serializable
data class ShoppingItem(val bookid: String, var qty: Int)
@Serializable
data class User(
    val userId: String,
    val name: String,
    val username: String,
    val pass: String
)
