package eu.sesma.paraguas.platform

import android.content.Context
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
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
    fun provideLinearLayoutManager(activity: AppCompatActivity) =
        androidx.recyclerview.widget.LinearLayoutManager(activity as Context)

}
