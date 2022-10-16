package com.example.cryptoapp.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.R
import com.example.cryptoapp.domain.CoinPriceInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_coin_info.view.*

class CoinInfoAdapter(private val context : Context) :
    RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {

    var coinInfoList : List<CoinPriceInfo> = arrayListOf<CoinPriceInfo>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    var onCoinClickListener : OnCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_coin_info, parent, false)
        return CoinInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coinInfo = coinInfoList[position]
        with(holder) {
            with(coinInfo) {
                val symbolsTemplate = context.resources.getString(R.string.symbols_template)
                val lastUpdateTemplate = context.resources.getString(R.string.last_update )
                textViewSymbols.text = String.format(symbolsTemplate, fromSymbol, toSymbol)
                textViewPrice.text = price.toString()
                textViewLastUpdate.text = String.format(lastUpdateTemplate, getFormattedTime())
                Picasso.get()
                    .load(getFullImageUrl())
                    .into(imageViewCoinLogo)
            }
            itemView.setOnClickListener {
                onCoinClickListener?.onCoinClick(coinInfo)
            }
        }
    }

    override fun getItemCount(): Int {
        return coinInfoList.size
    }

    inner class CoinInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewCoinLogo = itemView.imageViewCoinLogo
        val textViewSymbols = itemView.textViewSymbols
        val textViewPrice = itemView.textViewPrice
        val textViewLastUpdate = itemView.textViewLastUpdate
    }
    interface OnCoinClickListener {
        fun onCoinClick(coinPriceInfo : CoinPriceInfo)
    }
}
