package com.mohammedalaa.challenge001.di

import android.content.Context
import android.content.SharedPreferences
import com.mohammedalaa.challenge001.data.JsonDataStore
import com.mohammedalaa.challenge001.data.PalettesRepository
import com.mohammedalaa.challenge001.data.util.JsonUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Provides
    fun provideJsonUtil(
        @ApplicationContext
        applicationContext: Context,
    ): JsonUtil {
        return JsonUtil(applicationContext)
    }

    @Provides
    fun provideDataStore(
        jsonUtil: JsonUtil,
    ): JsonDataStore {
        return JsonDataStore(jsonUtil)
    }
}
