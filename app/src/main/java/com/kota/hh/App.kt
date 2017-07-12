package com.kota.hh

import android.app.Application
import android.content.Context
import com.kota.hh.injection.component.ApplicationComponent
import com.kota.hh.injection.component.DaggerApplicationComponent
import com.kota.hh.injection.module.ApplicationModule

class App : Application() {

    private var applicationComponent: ApplicationComponent? = null

    companion object {
        fun get(context: Context): App = context.applicationContext as App
    }

    fun getComponent(): ApplicationComponent {
        if (applicationComponent == null) {
            applicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(ApplicationModule(this))
                    .build()
        }
        return applicationComponent!!
    }

}