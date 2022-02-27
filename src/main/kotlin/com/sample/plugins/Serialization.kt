package com.sample.plugins

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*

/**
 * Serialization using Gson.
 */
fun Application.configureSerialization(){
    install(ContentNegotiation){
        gson{
            setPrettyPrinting()
        }
    }
}