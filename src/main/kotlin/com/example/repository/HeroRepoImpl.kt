package com.example.repository

import com.example.models.ApiResponse
import com.example.models.Hero

class HeroRepoImpl: HeroRepo {
    override val heroes: Map<Int, List<Hero>>
        get() = TODO("Not yet implemented")
    override val page1: List<Hero>
        get() = TODO("Not yet implemented")
    override val page2: List<Hero>
        get() = TODO("Not yet implemented")
    override val page3: List<Hero>
        get() = TODO("Not yet implemented")
    override val page4: List<Hero>
        get() = TODO("Not yet implemented")
    override val page5: List<Hero>
        get() = TODO("Not yet implemented")

    override suspend fun getHeroes(page: Int): ApiResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getHero(name: String): ApiResponse {
        TODO("Not yet implemented")
    }
}