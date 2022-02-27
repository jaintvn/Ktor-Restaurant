package com.sample.core

import com.sample.core.auth.JWTConfig
import com.sample.core.auth.JWTConfigImpl
import com.sample.core.db.DataBaseImpl
import com.sample.core.db.Database
import com.sample.core.errohandler.ExceptionHandler
import com.sample.core.errohandler.ExceptionHandlerImpl
import org.koin.dsl.module

/**
 * All core classes dependencies defined here
 */
val coreModules = module {
    single<Database> { DataBaseImpl("restaurant") }
    single<JWTConfig> { JWTConfigImpl("sample-restaurant") }
    single<ExceptionHandler> { ExceptionHandlerImpl() }
}