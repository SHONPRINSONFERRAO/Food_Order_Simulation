package com.apps.foodordersimulation.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import com.apps.foodordersimulation.data.database.Food
import com.apps.foodordersimulation.data.repository.FoodRepository
import com.apps.foodordersimulation.utils.OrderStatusEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class FoodOrdersViewModel @Inject constructor(
    private val repository: FoodRepository
): ViewModel() {

    private val mockNames = listOf<String>("Beef Stake", "Chicken Soup", "Noodles", "Fish")

    fun fetchAllOrders() = repository.fetchOrders()

    suspend fun placeOrder() = repository.fetchOrders(
        Food(itemName = mockNames.random(),
            itemPrice = Random.nextDouble(10.00, 1000.00),
            quantity = Random.nextInt(1,20),
            orderStatus = OrderStatusEnum.New.status,
            date = System.currentTimeMillis()
        ))

    suspend fun updateOrderStatus(order: Food) = repository.updateOrder(order)

    suspend fun orderDelivered(order: Food) = repository.deleteOrder(order)

    fun fetchNewStatusCount() = repository.fetchNewStatusCount()

    fun fetchPreparingStatusCount() = repository.fetchPreparingStatusCount()

    fun fetchReadyStausCount() = repository.fetchReadyStausCount()

    fun fetchDeliveredStatusCount() = repository.fetchDeliveredStatusCount()

}