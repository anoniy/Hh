package com.kota.hh.injection.module

import android.app.Activity
import com.kota.hh.injection.ActivityContext
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(val activity: Activity) {

    @Provides
    fun provideActivity() = activity

    @Provides
    @ActivityContext
    fun provideContext() = activity

}