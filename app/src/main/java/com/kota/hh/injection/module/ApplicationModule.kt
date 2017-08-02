package com.kota.hh.injection.module

import android.app.Application
import android.content.Context
import com.kota.hh.data.remote.ApiService
import com.kota.hh.injection.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(val application: Application) {

    @Provides
    fun provideApplication() = application

    @Provides
    @ApplicationContext
    fun provideContext(): Context = application

    @Provides
    @Singleton
    fun provideApiService() = ApiService.newApiService()
}