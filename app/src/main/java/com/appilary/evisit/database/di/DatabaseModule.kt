package com.appilary.evisit.database.di

import android.content.Context
import com.appilary.evisit.database.ApiService
import com.appilary.evisit.database.MainRepository
import com.appilary.evisit.database.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context) =
        EVisitDataBase.getInstance(context)

    @Provides
    fun provideRecipeDao(appDatabase: EVisitDataBase): UserDao {
        return appDatabase.userDao()
    }


    @Provides
    @Singleton
    fun provideBrowserRepository(apiService: ApiService): MainRepository {
        return MainRepository(apiService)
    }
}