package com.sample.feature.order

import com.sample.feature.order.controller.OrderController
import com.sample.feature.order.controller.OrderControllerImpl
import com.sample.feature.order.repository.OrderRepository
import com.sample.feature.order.repository.OrderRepositoryImpl
import com.sample.feature.order.service.OrderApiService
import com.sample.feature.order.service.OrderApiServiceImpl
import org.koin.dsl.module

private val controllerModules = module {
    single<OrderController> { OrderControllerImpl(get(), get()) }
}

private val repositoryModules = module {
    single<OrderRepository> { OrderRepositoryImpl(get(), get()) }
}

private val serviceModules = module {
    single<OrderApiService> { OrderApiServiceImpl(get(), get()) }
}

val orderModules = listOf(controllerModules, repositoryModules, serviceModules)