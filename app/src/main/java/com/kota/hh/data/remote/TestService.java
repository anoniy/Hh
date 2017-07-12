package com.kota.hh.data.remote;

import com.kota.hh.data.model.TestModel;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.http.GET;
import rx.Observable;

public interface TestService {

    String ENDPOINT = "https://api.ribot.io/";

    @GET("ribots")
    Observable<List<TestModel>> getRibots();

    /******** Helper class that sets up a new services *******/
    class Creator {

        public static TestService newRibotsService() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(TestService.ENDPOINT)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(TestService.class);
        }
    }
}
