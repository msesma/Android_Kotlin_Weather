package com.paradigmadigital.paradigma.injection;

import com.paradigmadigital.paradigma.SplashActivity;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(SplashActivity splashActivity);

    //Exposed to sub-graphs
    Context provideContext();

    SharedPreferences provideSharedPreferences();
}
