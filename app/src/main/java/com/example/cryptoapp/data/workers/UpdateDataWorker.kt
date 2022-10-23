package com.example.cryptoapp.data.workers

import android.content.Context
import androidx.work.*
import com.example.cryptoapp.data.database.CoinDatabase
import com.example.cryptoapp.data.mapper.CoinMapper
import com.example.cryptoapp.data.network.ApiFactory
import kotlinx.coroutines.delay

class UpdateDataWorker(context: Context, workerParameters: WorkerParameters)
    : CoroutineWorker(context, workerParameters) {

    private val coinInfoDao = CoinDatabase.getInstance(context).coinPriceInfoDao()
    private val mapper = CoinMapper()
    private val apiService = ApiFactory.apiService

    override suspend fun doWork(): Result {
        while(true) {
            try {
                val topCoins = apiService.getTopCoinInfo(limit = 10)
                val fSyms = mapper.mapCoinListNamesToStringDto(topCoins)
                val jsonContainer = apiService.getFullPriceList(fSyms = fSyms)
                val coinInfoDtoList = mapper.mapJsonContainerToListCoinInfo(jsonContainer)
                val dbModelList = coinInfoDtoList.map { mapper.mapDtoToDbModel(it) }
                coinInfoDao.insertCoinList(dbModelList)
            } catch (e: Exception) {
            }
            delay(10_000)
        }
    }

    companion object {
        const val NAME = "UpdateDataWorker"

        fun makeRequest() : OneTimeWorkRequest {
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .build()
            return OneTimeWorkRequestBuilder<UpdateDataWorker>()
                .setConstraints(constraints)
                .build()
        }
    }

}