package com.sample

import com.sample.plugins.*
import io.ktor.application.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    configureKoin()
    configureLogging()
    configureSerialization()
    configureSecurity()
    configureStatusPages()
    configureRouting()
}
