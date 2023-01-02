package com.go0ose.agedeterminant

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelStoreOwner

class AgeApplication : Application() {
    companion object {
        var appComponent: AppComponent? = null
        fun initDagger(
            viewModelStoreOwner: ViewModelStoreOwner,
            context: Context
        ) {
            appComponent = DaggerAppComponent.builder()
                .viewStore(viewModelStoreOwner)
                .buildContext(context)
                .build()
        }
    }
}