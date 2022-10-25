package com.example.cryptoapp.presentation.coin_recycler_view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.ItemCoinInfoBinding
import com.example.cryptoapp.domain.CoinInfo
import com.squareup.picasso.Picasso

class CoinInfoAdapter(private val context : Context) :
    ListAdapter<CoinInfo, CoinInfoViewHolder>(CoinDiffCallback) {

    var onCoinClickListener : OnCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val binding = ItemCoinInfoBinding.inflate(LayoutInflater.from(
            parent.context), parent, false)
        return CoinInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coinInfo = getItem(position)
        with(holder.binding) {
            with(coinInfo) {
                val symbolsTemplate = context.resources.getString(R.string.symbols_template)
                val lastUpdateTemplate = context.resources.getString(R.string.last_update )
                textViewSymbols.text = String.format(symbolsTemplate, fromSymbol, toSymbol)
                textViewPrice.text = price.toString()
                textViewLastUpdate.text = String.format(lastUpdateTemplate, lastUpdate)
                Picasso.get()
                    .load(imageUrl)
                    .into(imageViewCoinLogo)
            }
            root.setOnClickListener {
                onCoinClickListener?.onCoinClick(coinInfo)
            }
        }
    }
    interface OnCoinClickListener {
        fun onCoinClick(coinFullInfo : CoinInfo)
    }
}
