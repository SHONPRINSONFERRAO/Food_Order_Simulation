package com.apps.foodordersimulation.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {

    @Query("SELECT * from food")
    fun getItems(): Flow<List<Food>>

    @Query("SELECT * from food WHERE id = :id")
    fun getItem(id: Int): Flow<Food>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Food)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(item: Food)

    @Delete
    suspend fun delete(item: Food)

    @Query("SELECT COUNT() from food WHERE orderStatus = 0 ")
    fun getNewStatusCount(): Flow<Int>

    @Query("SELECT COUNT() from food WHERE orderStatus = 1")
    fun getPreparingStatusCount(): Flow<Int>

    @Query("SELECT COUNT() from food WHERE orderStatus = 2")
    fun getReadyStatusCount(): Flow<Int>

    @Query("SELECT COUNT() from food WHERE orderStatus = 3")
    fun getDeliveredStatusCount(): Flow<Int>
}