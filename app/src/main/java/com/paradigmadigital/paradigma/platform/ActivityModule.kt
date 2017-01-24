package com.paradigmadigital.paradigma.platform

import android.app.Activity
import com.paradigmadigital.paradigma.injection.PerActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    @PerActivity
    internal fun activity(): Activity {
        return this.activity
    }
}
