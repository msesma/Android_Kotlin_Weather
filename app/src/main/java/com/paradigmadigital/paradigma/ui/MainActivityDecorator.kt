package com.paradigmadigital.paradigma.ui

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Button
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.paradigmadigital.paradigma.R
import javax.inject.Inject

class MainActivityDecorator
@Inject
constructor(val activity: AppCompatActivity) : MainActivityUserInterface {

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar
    @BindView(R.id.refresh)
    lateinit var refreshButton: Button
    @BindView(R.id.weather)
    lateinit var text: TextView

    private var delegate: MainActivityUserInterface.Delegate? = null
    private var city: String? = null

    fun bind(view: View) {
        ButterKnife.bind(this, view)
        initToolbar()
    }

    fun dispose() {
        delegate = null
    }

    override fun initialize(delegate: MainActivityUserInterface.Delegate) {
        this.delegate = delegate
        toolbar.title = ""
    }

    override fun showError(errorMessage: String) {
        text.setText(errorMessage)
    }

    override fun showMessage(message: String) {
        text.setText(message)
    }

    override fun setCity(city: String) {
        this.city = city
        toolbar.title = city
    }

    @OnClick(R.id.refresh)
    fun onRefreshClick() {
        delegate?.onRefreshButtonClick()
    }

    private fun initToolbar() {
        activity.setSupportActionBar(toolbar)
        val actionBar = activity.supportActionBar
        actionBar?.setDisplayShowTitleEnabled(false)
        actionBar?.setIcon(R.mipmap.ic_launcher)
    }
}
