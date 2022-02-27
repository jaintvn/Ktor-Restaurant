package com.sample.feature.user

import com.sample.feature.user.service.UserApiService
import com.sample.feature.user.service.UserApiServiceImpl
import org.koin.dsl.module

/**
 * koin modules for user feature classes.
 */

private val serviceModules = module {
    single<UserApiService> { UserApiServiceImpl(get()) }
}

val userModules = listOf(serviceModules)