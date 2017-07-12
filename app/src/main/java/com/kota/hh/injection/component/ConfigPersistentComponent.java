package com.kota.hh.injection.component;

import com.kota.hh.injection.ConfigPersistent;
import com.kota.hh.injection.module.ActivityModule;

import dagger.Component;

@ConfigPersistent
@Component(dependencies = ApplicationComponent.class)
public interface ConfigPersistentComponent {

    ActivityComponent activityComponent(ActivityModule activityModule);

}