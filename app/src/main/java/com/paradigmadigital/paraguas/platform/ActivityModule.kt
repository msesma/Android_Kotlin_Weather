package com.paradigmadigital.paraguas.platform

import android.support.v7.app.AppCompatActivity
import com.paradigmadigital.paraguas.injection.PerActivity
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
