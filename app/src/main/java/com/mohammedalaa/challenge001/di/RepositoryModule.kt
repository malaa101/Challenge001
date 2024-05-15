package com.mohammedalaa.challenge001.di


import com.mohammedalaa.challenge001.data.PalettesRepository
import com.mohammedalaa.challenge001.data.PalettesRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * This module is responsible for defining the creation of any repository dependencies used in the
 * application.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindPalettesRepository(
        palettesRepositoryImp: PalettesRepositoryImp,
    ): PalettesRepository

}
