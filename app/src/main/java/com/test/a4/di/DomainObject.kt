package com.test.a4.di

import com.test.a4.domain.NetworkRepository
import com.test.a4.domain.NetworkUseCase
import com.test.a4.domain.PreferencesRepository
import com.test.a4.domain.PreferencesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainObject {

    @Singleton
    @Provides
    fun provideNetworkUseCase(networkRepository: NetworkRepository) =
        NetworkUseCase(networkRepository)

    @Singleton
    @Provides
    fun providePreferencesUseCase(preferencesRepository: PreferencesRepository) =
        PreferencesUseCase(preferencesRepository)
}