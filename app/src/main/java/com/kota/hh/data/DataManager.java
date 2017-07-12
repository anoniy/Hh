package com.kota.hh.data;

import com.kota.hh.data.remote.TestService;

import junit.framework.Test;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

@Singleton
public class DataManager {

    private final TestService testService;

    @Inject
    public DataManager(TestService testService) {
        this.testService = testService;
    }

    public Observable<ArrayList<Test>> getRibots() {
        return Observable.just(new ArrayList<Test>());
    }

}
