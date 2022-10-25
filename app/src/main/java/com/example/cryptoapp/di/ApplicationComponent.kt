package com.example.cryptoapp.di

import android.app.Application
import com.example.cryptoapp.presentation.CoinApp
import com.example.cryptoapp.presentation.CoinPriceListActivity
import com.example.cryptoapp.presentation.FragmentCoinDetailInfo
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class, WorkerModule::class])
interface ApplicationComponent {

    fun inject(activity : CoinPriceListActivity)

    fun inject(fragment : FragmentCoinDetailInfo)

    fun inject(application: CoinApp)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application : Application) : ApplicationComponent
    }
}