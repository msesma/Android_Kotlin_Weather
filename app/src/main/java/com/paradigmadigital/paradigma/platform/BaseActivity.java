package com.paradigmadigital.paradigma.platform;

import com.paradigmadigital.paradigma.injection.ApplicationComponent;

import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }
}
