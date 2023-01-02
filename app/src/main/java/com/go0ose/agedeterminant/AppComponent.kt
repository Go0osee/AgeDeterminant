package com.go0ose.agedeterminant

import android.content.Context
import androidx.lifecycle.ViewModelStoreOwner
import com.go0ose.agedeterminant.data.di.DataModule
import com.go0ose.agedeterminant.data.di.RetrofitModule
import com.go0ose.agedeterminant.data.di.RoomModule
import com.go0ose.agedeterminant.domain.di.DomainModule
import com.go0ose.agedeterminant.presentation.mainScreen.MainActivity
import com.go0ose.agedeterminant.presentation.mainScreen.MainFragment
import com.go0ose.agedeterminant.presentation.di.SharedViewModelModule
import com.go0ose.agedeterminant.presentation.di.ViewModelFactory
import com.go0ose.agedeterminant.presentation.di.ViewModelModule
import com.go0ose.agedeterminant.presentation.mainScreen.pages.favorite.FavoriteFragment
import com.go0ose.agedeterminant.presentation.mainScreen.pages.search.SearchFragment

import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DomainModule::class,
        RetrofitModule::class,
        RoomModule::class,
        DataModule::class,
        ViewModelModule::class,
        SharedViewModelModule::class
    ]
)
interface AppComponent {

    fun viewModelsFactory(): ViewModelFactory
    fun inject(target: MainActivity)
    fun inject(target: SearchFragment)
    fun inject(target: FavoriteFragment)
    fun inject(target: MainFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun viewStore(viewModelStoreOwner: ViewModelStoreOwner): Builder

        @BindsInstance
        fun buildContext(context: Context): Builder

        fun build(): AppComponent
    }
}