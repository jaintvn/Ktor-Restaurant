package com.sample.feature.menu

import com.sample.feature.menu.controller.MenuController
import com.sample.feature.menu.controller.MenuControllerImpl
import com.sample.feature.menu.repository.MenuRepository
import com.sample.feature.menu.repository.MenuRepositoryImpl
import com.sample.feature.menu.service.MenuAPiService
import com.sample.feature.menu.service.MenuApiServiceImpl
import org.koin.dsl.module

private val controllerModules = module {
    single<MenuController> { MenuControllerImpl(get()) }
}

private val repositoryModules = module {
    single<MenuRepository> { MenuRepositoryImpl(get(),get()) }
}

private val serviceModules = module {
    single<MenuAPiService> { MenuApiServiceImpl(get()) }
}

val menuModules = listOf(controllerModules, repositoryModules, serviceModules)