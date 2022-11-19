package com.apps.foodordersimulation.data.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Food(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val itemName: String,
    @ColumnInfo(name = "price")
    val itemPrice: Double,
    @ColumnInfo(name = "quantity")
    val quantity: Int,
    @ColumnInfo(name = "date")
    val date: Long,
    @ColumnInfo(name = "orderStatus")
    var orderStatus: Int = 0
): Parcelable