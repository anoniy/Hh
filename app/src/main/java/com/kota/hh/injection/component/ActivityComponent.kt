package com.kota.hh.injection.component

import com.kota.hh.injection.PerActivity
import com.kota.hh.injection.module.ActivityModule
import com.kota.hh.ui.main.auth.AuthActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun inject(authActivity: AuthActivity)
}