package com.example.cryptoapp.data.workers

import android.content.Context
import androidx.work.*
import com.example.cryptoapp.data.database.CoinInfoDao
import com.example.cryptoapp.data.mapper.CoinMapper
import com.example.cryptoapp.data.network.ApiService
import kotlinx.coroutines.delay
import javax.inject.Inject

class UpdateDataWorker(
    context: Context,
    workerParameters: WorkerParameters,
    private val coinInfoDao : CoinInfoDao,
    private val mapper : CoinMapper,
    private val apiService : ApiService
)
    : CoroutineWorker(context, workerParameters) {

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

    class Factory @Inject constructor(
        private val coinInfoDao : CoinInfoDao,
        private val mapper : CoinMapper,
        private val apiService : ApiService) : ChildCoinWorkerFactory {

        override fun create(
            context: Context,
            workerParameters: WorkerParameters
        ): ListenableWorker {
            return UpdateDataWorker(
                context,
                workerParameters,
                coinInfoDao,
                mapper,
                apiService
            )
        }
    }

}