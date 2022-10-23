package com.example.cryptoapp.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinFullInfoDto(
    @SerializedName("TYPE")
    @Expose
    val type: String? = null,

    @SerializedName("MARKET")
    @Expose
    val market: String? = null,

    @SerializedName("FROMSYMBOL")
    @Expose
    val fromSymbol: String = "",

    @SerializedName("TOSYMBOL")
    @Expose
    val toSymbol: String? = null,

    @SerializedName("FLAGS")
    @Expose
    val flags: String? = null,

    @SerializedName("PRICE")
    @Expose
    val price: Double = 0.0,

    @SerializedName("LASTUPDATE")
    @Expose
    val lastUpdate: Int = 0,

    @SerializedName("MEDIAN")
    @Expose
    val median: Double = 0.0,

    @SerializedName("LASTVOLUME")
    @Expose
    val lastVolume: Double = 0.0,

    @SerializedName("LASTVOLUMETO")
    @Expose
    val lastVolumeTo: Double = 0.0,

    @SerializedName("LASTTRADEID")
    @Expose
    val lastTradeId: String? = null,

    @SerializedName("VOLUMEDAY")
    @Expose
    val volumeDay: Double = 0.0,

    @SerializedName("VOLUMEDAYTO")
    @Expose
    val volumeDayTo: Double = 0.0,

    @SerializedName("VOLUME24HOUR")
    @Expose
    val volume24hour: Double = 0.0,

    @SerializedName("VOLUME24HOURTO")
    @Expose
    val volume24hourTo: Double = 0.0,

    @SerializedName("OPENDAY")
    @Expose
    val openDay: Double = 0.0,

    @SerializedName("HIGHDAY")
    @Expose
    val highDay: Double = 0.0,

    @SerializedName("LOWDAY")
    @Expose
    val lowDay: Double = 0.0,

    @SerializedName("OPEN24HOUR")
    @Expose
    val open24hour: Double = 0.0,

    @SerializedName("HIGH24HOUR")
    @Expose
    val high24hour: Double = 0.0,

    @SerializedName("LOW24HOUR")
    @Expose
    val low24hour: Double = 0.0,

    @SerializedName("LASTMARKET")
    @Expose
    val lastMarket: String? = null,

    @SerializedName("VOLUMEHOUR")
    @Expose
    val volumeHour: Double = 0.0,

    @SerializedName("VOLUMEHOURTO")
    @Expose
    val volumeHourTo: Double = 0.0,

    @SerializedName("OPENHOUR")
    @Expose
    val openHour: Double = 0.0,

    @SerializedName("HIGHHOUR")
    @Expose
    val highHour: Double = 0.0,

    @SerializedName("LOWHOUR")
    @Expose
    val lowHour: Double = 0.0,

    @SerializedName("TOPTIERVOLUME24HOUR")
    @Expose
    val topTierVolume24hour: Double = 0.0,

    @SerializedName("TOPTIERVOLUME24HOURTO")
    @Expose
    val topTierVolume24hourTo: Double = 0.0,

    @SerializedName("CHANGE24HOUR")
    @Expose
    val change24hour: Double = 0.0,

    @SerializedName("CHANGEPCT24HOUR")
    @Expose
    val changePct24hour: Double = 0.0,

    @SerializedName("CHANGEDAY")
    @Expose
    val changeday: Double = 0.0,

    @SerializedName("CHANGEPCTDAY")
    @Expose
    val changePctDay: Double = 0.0,

    @SerializedName("CHANGEHOUR")
    @Expose
    val changeHour: Double = 0.0,

    @SerializedName("CHANGEPCTHOUR")
    @Expose
    val changePctHour: Double = 0.0,

    @SerializedName("CONVERSIONTYPE")
    @Expose
    val conversionType: String? = null,

    @SerializedName("CONVERSIONSYMBOL")
    @Expose
    val conversionSymbol: String? = null,

    @SerializedName("SUPPLY")
    @Expose
    val supply: Int = 0,

    @SerializedName("MKTCAP")
    @Expose
    val mktCap: Double = 0.0,

    @SerializedName("MKTCAPPENALTY")
    @Expose
    val mktCapPenalty: Int = 0,

    @SerializedName("CIRCULATINGSUPPLY")
    @Expose
    val circulatingSupply: Int = 0,

    @SerializedName("CIRCULATINGSUPPLYMKTCAP")
    @Expose
    val circulatingSupplyMktCap: Double = 0.0,

    @SerializedName("TOTALVOLUME24H")
    @Expose
    val totalVolume24h: Double = 0.0,

    @SerializedName("TOTALVOLUME24HTO")
    @Expose
    val totalVolume24hTo: Double = 0.0,

    @SerializedName("TOTALTOPTIERVOLUME24H")
    @Expose
    val totalTopTierVolume24h: Double = 0.0,

    @SerializedName("TOTALTOPTIERVOLUME24HTO")
    @Expose
    val totalTopTierVolume24hto: Double = 0.0,

    @SerializedName("IMAGEURL")
    @Expose
    val imageUrl: String? = null
)