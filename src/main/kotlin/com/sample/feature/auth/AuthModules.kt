package com.sample.feature.auth

import com.sample.feature.auth.controller.AuthController
import com.sample.feature.auth.controller.AuthControllerImpl
import com.sample.feature.auth.repository.AuthRepository
import com.sample.feature.auth.repository.AuthRepositoryImpl
import org.koin.dsl.module

/**
 * koin modules for auth feature classes.
 */

private val repositoryModules = module {
    single<AuthRepository> {
        AuthRepositoryImpl(get(), get(), get())
    }
}

private val controller = module {
    single<AuthController> { AuthControllerImpl(get()) }
}

val authModules = listOf(repositoryModules, controller)