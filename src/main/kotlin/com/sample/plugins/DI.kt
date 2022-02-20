package com.sample.plugins

import com.sample.core.mainModules
import io.ktor.application.*
import org.koin.ktor.ext.koin
import org.koin.logger.SLF4JLogger

fun Application.configureKoin(){
    koin {
        SLF4JLogger()
        modules(mainModules)
    }
}