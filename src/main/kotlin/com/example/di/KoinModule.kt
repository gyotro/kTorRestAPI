package com.example.di

import com.example.models.DataManager
import com.example.plugins.ApacheClient
import com.example.repository.HeroRepo
import com.example.repository.HeroRepoImpl
import org.koin.dsl.module

val koinModule = module(createdAtStart = true) {
    single<HeroRepo> { HeroRepoImpl() }
    single { ApacheClient() }
    single { DataManager() }
}