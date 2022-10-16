package com.example.cryptoapp.api

import com.example.cryptoapp.domain.CoinInfoListOfData
import com.example.cryptoapp.domain.CoinPriceInfoRawData
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top/totalvolfull")
    fun getTopCoinInfo(
        @Query(QUERY_PARAM_API_KEY) apiKey : String = API_KEY,
        @Query(QUERY_PARAM_LIMIT) limit : Int = 20,
        @Query(QUERY_PARAM_TO_SYMBOL) tSym : String = CURRENCY
    ) : Single<CoinInfoListOfData>

    @GET("pricemultifull")
    fun getFullPriceList(
        @Query(QUERY_PARAM_API_KEY) apiKey : String = API_KEY,
        @Query(QUERY_PARAM_FROM_SYMBOLS) fSyms : String,
        @Query(QUERY_PARAM_TO_SYMBOLS) tSyms : String = CURRENCY
    ) : Single<CoinPriceInfoRawData>

    companion object {
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
        private const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"

        private const val CURRENCY = "USD"
        private const val API_KEY = "0701e174bc5e68ae09591c6d7935af467f0084de0a0565f5be3885f22fdb92f0"
    }
}