package com.ram.systemtest.di;


import com.ram.systemtest.view.HomeActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Ramakrishna Sunkara on 10-05-2019
 */

@Component(modules = {AppModule.class, UtilsModule.class})
@Singleton
public interface AppComponent {

    void doInjection(HomeActivity homeActivity);

}
