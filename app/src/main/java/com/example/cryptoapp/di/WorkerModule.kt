package com.example.cryptoapp.di

import androidx.work.ListenableWorker
import com.example.cryptoapp.data.workers.ChildCoinWorkerFactory
import com.example.cryptoapp.data.workers.UpdateDataWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(UpdateDataWorker::class)
    fun bindUpdateDataWorkerFactory(worker : UpdateDataWorker.Factory) : ChildCoinWorkerFactory
}