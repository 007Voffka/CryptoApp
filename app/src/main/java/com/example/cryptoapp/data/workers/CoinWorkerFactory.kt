package com.example.cryptoapp.data.workers

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import javax.inject.Inject
import javax.inject.Provider

class CoinWorkerFactory @Inject constructor(
    private val workerProviders : @JvmSuppressWildcards
    Map<Class<out ListenableWorker>, Provider<ChildCoinWorkerFactory>>
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return when(workerClassName) {
            UpdateDataWorker::class.qualifiedName -> {
                val childCoinWorkerFactory = workerProviders[UpdateDataWorker::class.java]?.get()
                return childCoinWorkerFactory?.create(appContext, workerParameters)
            } else -> null
        }
    }
}