package com.appilary.evisit.database.di

import android.content.Context
import com.appilary.evisit.database.ApiService
import com.appilary.evisit.database.Repository
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
        ChromiumDataBase.getInstance(context)

    @Provides
    fun provideRecipeDao(appDatabase: ChromiumDataBase): UserDao {
        return appDatabase.userDao()
    }


    @Provides
    @Singleton
    fun provideBrowserRepository(apiService: ApiService): Repository {
        return Repository(apiService)
    }
}