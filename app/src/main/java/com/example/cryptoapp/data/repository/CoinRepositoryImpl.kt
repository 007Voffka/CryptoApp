package com.example.cryptoapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.cryptoapp.data.database.CoinDatabase
import com.example.cryptoapp.data.mapper.CoinMapper
import com.example.cryptoapp.data.workers.UpdateDataWorker
import com.example.cryptoapp.domain.CoinInfo
import com.example.cryptoapp.domain.CoinRepository

class CoinRepositoryImpl(private val application: Application) : CoinRepository {

    private val coinInfoDao = CoinDatabase.getInstance(application).coinPriceInfoDao()
    private val mapper = CoinMapper()

    override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
        return Transformations.map(coinInfoDao.getCoinList()) {
            it.map { dbModel ->
                mapper.mapDbModelToEntity(dbModel)
            }
        }
    }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> {
        return Transformations.map(coinInfoDao.getInfoAboutCoin(fromSymbol)) {
            mapper.mapDbModelToEntity(it)
        }
    }

    override suspend fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            UpdateDataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            UpdateDataWorker.makeRequest()
        )
    }

}