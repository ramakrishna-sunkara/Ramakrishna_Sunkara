package com.ram.systemtest.di;

import androidx.lifecycle.ViewModelProvider;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.ram.systemtest.manager.DataProcessManager;
import com.ram.systemtest.manager.Repository;
import com.ram.systemtest.utils.ApiCallInterface;
import com.ram.systemtest.utils.Urls;
import com.ram.systemtest.viewmodel.ViewModelFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Ramakrishna Sunkara on 10-05-2019
 */

@Module
public class UtilsModule {

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {

        return new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    ApiCallInterface getApiCallInterface(Retrofit retrofit) {
        return retrofit.create(ApiCallInterface.class);
    }

    @Provides
    @Singleton
    OkHttpClient getRequestHeader() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(httpLoggingInterceptor);

        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .build();
            return chain.proceed(request);
        })
                .connectTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS);

        return httpClient.build();
    }

    @Provides
    @Singleton
    Repository getRepository(ApiCallInterface apiCallInterface) {
        return new Repository(apiCallInterface);
    }

    @Provides
    @Singleton
    DataProcessManager getDataProcessManager() {
        return new DataProcessManager();
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory getViewModelFactory(Repository myRepository, DataProcessManager dataProcessManager) {
        return new ViewModelFactory(myRepository, dataProcessManager);
    }
}
