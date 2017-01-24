package com.paradigmadigital.paradigma.platform;

import com.paradigmadigital.paradigma.injection.ApplicationComponent;
import com.paradigmadigital.paradigma.injection.ApplicationModule;
import com.paradigmadigital.paradigma.injection.DaggerApplicationComponent;

import android.app.Activity;
import android.app.Application;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

public class AndroidApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        registerActivityLifecycleCallbacks(new ActivityLifecycleAdapter() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
        });
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
