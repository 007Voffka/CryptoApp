package com.example.cryptoapp.data.mapper

import com.example.cryptoapp.data.database.CoinInfoDbModel
import com.example.cryptoapp.data.network.model.CoinFullInfoDto
import com.example.cryptoapp.data.network.model.CoinJsonContainerDto
import com.example.cryptoapp.data.network.model.CoinListOfNamesDto
import com.example.cryptoapp.domain.CoinInfo
import com.google.gson.Gson
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CoinMapper @Inject constructor() {

    fun mapDtoToDbModel(dto : CoinFullInfoDto) = CoinInfoDbModel(
            fromSymbol = dto.fromSymbol,
            toSymbol = dto.toSymbol,
            price = dto.price,
            lastUpdate = dto.lastUpdate,
            highDay = dto.highDay,
            lowDay = dto.lowDay,
            lastMarket = dto.lastMarket,
            imageUrl = BASE_IMAGE_URL + dto.imageUrl
        )

    fun mapJsonContainerToListCoinInfo(jsonContainer : CoinJsonContainerDto) : List<CoinFullInfoDto> {
        val result = mutableListOf<CoinFullInfoDto>()
        val jsonObject = jsonContainer.json ?: return result
        val coinKeySet = jsonObject.keySet()
        for (key in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(key)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinFullInfoDto::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

    fun mapCoinListNamesToStringDto(listNamesDto : CoinListOfNamesDto) : String {
        return listNamesDto.names?.map { names -> names.coinName?.name }
            ?.joinToString(",") ?: ""
    }

    fun mapDbModelToEntity(coinInfo : CoinInfoDbModel) : CoinInfo {
        return CoinInfo(
            fromSymbol = coinInfo.fromSymbol,
            toSymbol = coinInfo.toSymbol,
            price = coinInfo.price,
            lastUpdate = convertTimestampToTime(coinInfo.lastUpdate),
            highDay = coinInfo.highDay,
            lowDay = coinInfo.lowDay,
            lastMarket = coinInfo.lastMarket,
            imageUrl = coinInfo.imageUrl
        )
    }

    companion object {
        const val BASE_IMAGE_URL = "https://cryptocompare.com"
    }

    private fun convertTimestampToTime(timestamp : Int?) : String {
        timestamp?.let {
            val timestampFormatted = timestamp.toLong()
            val stamp = Timestamp(timestampFormatted * 1000)
            val date = Date(stamp.time)
            val pattern = "HH:mm:ss"
            val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
            simpleDateFormat.timeZone = TimeZone.getDefault()
            return simpleDateFormat.format(date)
        }
        return ""
    }

}