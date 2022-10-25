package com.example.cryptoapp.di

import android.app.Application
import com.example.cryptoapp.data.database.CoinDatabase
import com.example.cryptoapp.data.database.CoinInfoDao
import com.example.cryptoapp.data.network.ApiFactory
import com.example.cryptoapp.data.network.ApiService
import com.example.cryptoapp.data.repository.CoinRepositoryImpl
import com.example.cryptoapp.domain.CoinRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    @ApplicationScope
    fun provideCoinRepository(impl : CoinRepositoryImpl) : CoinRepository {
        return impl
    }

    @Provides
    @ApplicationScope
    fun provideCoinInfoDao(application: Application) : CoinInfoDao {
        return CoinDatabase.getInstance(application).coinPriceInfoDao()
    }

    @Provides
    @ApplicationScope
    fun provideApiService() : ApiService {
        return ApiFactory.apiService
    }
}