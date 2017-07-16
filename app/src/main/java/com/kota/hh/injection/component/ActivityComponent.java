package com.kota.hh.injection.component;

import com.kota.hh.injection.PerActivity;
import com.kota.hh.injection.module.ActivityModule;
import com.kota.hh.ui.main.MainActivity;
import com.kota.hh.ui.main.auth.AuthActivity;

import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(AuthActivity authActivity);

}
