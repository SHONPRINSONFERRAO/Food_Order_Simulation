package com.apps.foodordersimulation.di

import android.content.Context
import androidx.room.Room
import com.apps.foodordersimulation.data.database.FoodDao
import com.apps.foodordersimulation.data.database.FoodDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideChannelDao(appDatabase: FoodDatabase): FoodDao {
        return appDatabase.foodDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): FoodDatabase {
        return Room.databaseBuilder(
            appContext,
            FoodDatabase::class.java,
            "food_order_db"
        ).build()
    }
}
