package com.go0ose.agedeterminant.presentation.mainScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.go0ose.agedeterminant.AgeApplication
import com.go0ose.agedeterminant.AppComponent
import com.go0ose.agedeterminant.DaggerAppComponent
import com.go0ose.agedeterminant.R
import com.go0ose.agedeterminant.utils.openFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: SharedViewModel

    lateinit var appComponent: AppComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AgeApplication.initDagger(this, this)
        appComponent = DaggerAppComponent.builder()
            .buildContext(this)
            .viewStore(this)
            .build()
        AgeApplication.appComponent?.inject(this)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        openFragment(R.id.container, MainFragment.TAG, MainFragment.newInstance())
    }
}