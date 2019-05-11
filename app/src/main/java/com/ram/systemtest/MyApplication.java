package com.ram.systemtest;

import android.app.Application;
import android.content.Context;

import com.ram.systemtest.di.AppComponent;
import com.ram.systemtest.di.AppModule;
import com.ram.systemtest.di.DaggerAppComponent;
import com.ram.systemtest.di.UtilsModule;


/**
 * Created by Ramakrishna Sunkara on 10-05-2019
 */

public class MyApplication extends Application {
    AppComponent appComponent;
    Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).utilsModule(new UtilsModule()).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }
}
