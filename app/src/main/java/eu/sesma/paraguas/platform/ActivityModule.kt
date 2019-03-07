package eu.sesma.paraguas.platform

import android.content.Context
import android.location.Geocoder
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides
import eu.sesma.paraguas.injection.PerActivity
import java.util.*

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    @PerActivity
    fun activity(): AppCompatActivity = this.activity

    @Provides
    @PerActivity
    fun provideLinearLayoutManager(activity: AppCompatActivity) = LinearLayoutManager(activity as Context)

}
