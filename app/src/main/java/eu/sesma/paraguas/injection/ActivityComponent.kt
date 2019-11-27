package eu.sesma.paraguas.injection

import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import eu.sesma.paraguas.platform.ActivityModule
import eu.sesma.paraguas.ui.detail.DetailActivity
import eu.sesma.paraguas.ui.master.MainActivity
import dagger.Component

@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(detailActivity: DetailActivity)

    //Exposed to sub-graphs.
    fun provideActivity(): AppCompatActivity
}
