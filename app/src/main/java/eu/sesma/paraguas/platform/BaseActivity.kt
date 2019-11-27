package eu.sesma.paraguas.platform

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.ViewGroup
import eu.sesma.paraguas.injection.ActivityComponent
import eu.sesma.paraguas.injection.ApplicationComponent
import eu.sesma.paraguas.injection.DaggerActivityComponent


open class BaseActivity : AppCompatActivity() {

    fun Activity.getRootView() = this.findViewById<ViewGroup>(android.R.id.content).getChildAt(0) as ViewGroup

    private val applicationComponent: ApplicationComponent
        get() = (application as AndroidApplication).applicationComponent

    lateinit var activityComponent: ActivityComponent

    public override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(applicationComponent)
                .activityModule(ActivityModule(this))
                .build()
    }
}
