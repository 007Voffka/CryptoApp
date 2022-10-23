package com.example.cryptoapp.data.network

import com.example.cryptoapp.data.network.model.CoinJsonContainerDto
import com.example.cryptoapp.data.network.model.CoinListOfNamesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top/totalvolfull")
    suspend fun getTopCoinInfo(
        @Query(QUERY_PARAM_API_KEY) apiKey : String = API_KEY,
        @Query(QUERY_PARAM_LIMIT) limit : Int = 10,
        @Query(QUERY_PARAM_TO_SYMBOL) tSym : String = CURRENCY
    ) : CoinListOfNamesDto

    @GET("pricemultifull")
    suspend fun getFullPriceList(
        @Query(QUERY_PARAM_API_KEY) apiKey : String = API_KEY,
        @Query(QUERY_PARAM_FROM_SYMBOLS) fSyms : String,
        @Query(QUERY_PARAM_TO_SYMBOLS) tSyms : String = CURRENCY
    ) : CoinJsonContainerDto

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