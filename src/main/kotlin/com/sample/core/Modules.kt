package com.sample.core

import com.typesafe.config.ConfigFactory
import io.ktor.config.*
import org.koin.dsl.module


private fun provideKtorConfig() : ApplicationConfig{
    return HoconApplicationConfig(ConfigFactory.load())
}

val appConfigModule = module {
    single {
        provideKtorConfig()
    }
}

val mainModules = appConfigModule


