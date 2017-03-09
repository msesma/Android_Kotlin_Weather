package com.paradigmadigital.paradigma.ui

import android.os.Bundle
import com.paradigmadigital.paradigma.R
import com.paradigmadigital.paradigma.platform.BaseActivity
import pub.devrel.easypermissions.EasyPermissions
import javax.inject.Inject


class MainActivity : BaseActivity() {

    @Inject
    lateinit var delegate: Delegate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
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
