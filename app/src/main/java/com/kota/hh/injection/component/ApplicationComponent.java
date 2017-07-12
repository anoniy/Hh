package com.kota.hh.injection.component;

import android.app.Application;
import android.content.Context;

import com.kota.hh.data.DataManager;
import com.kota.hh.data.remote.TestService;
import com.kota.hh.injection.ApplicationContext;
import com.kota.hh.injection.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();
    Application application();
    TestService testService();
    DataManager dataManager();
}
