package com.sample.plugins

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.request.*
import org.slf4j.event.Level

/**
 * App level logging
 */
fun Application.configureLogging(){
    install(CallLogging){
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }
}