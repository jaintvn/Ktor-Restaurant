package com.sample

import io.ktor.application.*
import com.sample.plugins.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

fun Application.module(testing : Boolean = false) {
    configureKoin()
    configureRouting()
    configureLogging()
    configureSerialization()
}
