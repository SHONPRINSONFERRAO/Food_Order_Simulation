package com.apps.foodordersimulation.data.repository

import com.apps.foodordersimulation.data.database.Food
import com.apps.foodordersimulation.data.database.FoodDatabase
import kotlinx.coroutines.delay
import javax.inject.Inject

class FoodRepository @Inject constructor(
    private val repository: FoodDatabase
) {

    fun fetchOrders() = repository.foodDao().getItems()

    suspend fun updateOrder(order: Food) = repository.foodDao().update(order)

    suspend fun fetchOrders(food: Food) = repository.foodDao().insert(food)

    suspend fun deleteOrder(food: Food) {
        delay(15000)
        repository.foodDao().delete(food)
    }

    fun fetchNewStatusCount() = repository.foodDao().getNewStatusCount()

    fun fetchPreparingStatusCount() = repository.foodDao().getPreparingStatusCount()

    fun fetchReadyStausCount() = repository.foodDao().getReadyStatusCount()

    fun fetchDeliveredStatusCount() = repository.foodDao().getDeliveredStatusCount()
}