package eu.sesma.paraguas.ui.master

import android.os.Bundle
import eu.sesma.paraguas.R
import eu.sesma.paraguas.platform.BaseActivity
import pub.devrel.easypermissions.EasyPermissions
import javax.inject.Inject


class MainActivity : BaseActivity() {

    @Inject
    lateinit var delegate: MainActivityDelegate

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        setContentView(R.layout.activity_main)
        activityComponent.inject(this)

        delegate.onCreate(getRootView())
    }

    override fun onResume() {
        super.onResume()
        delegate.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        delegate.onDestroy()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }
}
