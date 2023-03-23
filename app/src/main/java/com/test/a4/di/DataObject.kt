package com.test.a4.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.test.a2.data.PreferencesRepositoryImpl
import com.test.a4.domain.PreferencesRepository
import com.test.a4.data.DatabaseRepositoryImpl
import com.test.a4.data.network.NetworkApi
import com.test.a4.data.NetworkRepositoryImpl
import com.test.a4.data.db.AppDatabase
import com.test.a4.data.db.dao.TeamDao
import com.test.a4.domain.DatabaseRepository
import com.test.a4.domain.DatabaseUseCase
import com.test.a4.domain.NetworkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataObject {

    private const val DB_NAME = "app_database"
    private const val BASE_URL = "http://94.130.75.196/"
    private const val SHARED_PREF = "SHARED_PREF"

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
            .build()
    }

    @Provides
    @Singleton
    fun provideDatabaseRepository(db:TeamDao): DatabaseRepository = DatabaseRepositoryImpl(db)

    @Provides
    @Singleton
    fun provideDatabaseUseCase(repository: DatabaseRepository) = DatabaseUseCase(repository)

    @Provides
    @Singleton
    fun provideDao(appDatabase: AppDatabase): TeamDao = appDatabase.teamDao()

    @Provides
    @Singleton
    fun retrofitService(): NetworkApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        return retrofit.create(NetworkApi::class.java)
    }

    @Provides
    @Singleton
    fun providePreferencesRepository(@ApplicationContext context: Context): PreferencesRepository =
        PreferencesRepositoryImpl(context)

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun provideNetworkRepository(api: NetworkApi): NetworkRepository = NetworkRepositoryImpl(api)

}
