package com.paradigmadigital.paradigma.platform

import android.support.v7.app.AppCompatActivity
import com.paradigmadigital.paradigma.injection.PerActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    @PerActivity
    internal fun activity(): AppCompatActivity {
        return this.activity
    }
}
