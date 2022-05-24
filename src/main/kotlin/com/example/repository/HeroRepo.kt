package com.example.repository

import com.example.models.ApiResponse
import com.example.models.Hero

interface HeroRepo {

    // mappa pagina / Heroes
    val heroes: Map<Int, List<Hero>>

    val page1: List<Hero>
    val page2: List<Hero>
    val page3: List<Hero>
    val page4: List<Hero>
    val page5: List<Hero>

    suspend fun getHeroes(page: Int = 1): ApiResponse
    suspend fun getHero(name: String): ApiResponse
}