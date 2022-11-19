package com.apps.foodordersimulation.utils

enum class OrderStatusEnum(val status: Int) {
    New(0),
    Preparing(1),
    Ready(2),
    Delivered(3);

    companion object {
        private val mapping = values().associateBy(OrderStatusEnum::status)
        fun fromValue(value: Int) = mapping[value] ?: "UnDefined Order Status"
    }
}