package com.apps.foodordersimulation.ui.home.intent

sealed class OrderListIntent {

    object FetchAllOrders: OrderListIntent()
    object UpdateOrderStatus: OrderListIntent()
    object OrderDelivered: OrderListIntent()
    object PlaceOrder: OrderListIntent()
    object FetchNewStatusCount: OrderListIntent()
    object FetchPreparingStatusCount: OrderListIntent()
    object FetchReadyStausCount: OrderListIntent()
    object FetchDeliveredStatusCount: OrderListIntent()
}