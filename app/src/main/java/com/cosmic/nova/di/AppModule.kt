package com.cosmic.nova.di

import com.cosmic.nova.data.repository.SpaceRepository
import com.cosmic.nova.data.repository.SpaceRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindSpaceRepository(
        spaceRepositoryImpl: SpaceRepositoryImpl
    ): SpaceRepository
}
