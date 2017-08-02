package com.kota.hh.injection.component

import android.app.Application
import android.content.Context
import com.kota.hh.data.DataManager
import com.kota.hh.data.remote.ApiService
import com.kota.hh.injection.ApplicationContext
import com.kota.hh.injection.module.ApplicationModule
import com.kota.hh.util.Validator
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    @ApplicationContext
    fun context(): Context
    fun application(): Application
    fun apiService(): ApiService
    fun dataManager(): DataManager
    fun validator(): Validator
}