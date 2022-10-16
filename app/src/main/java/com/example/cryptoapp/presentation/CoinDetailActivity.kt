package com.example.cryptoapp.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_coin_detail.*

class CoinDetailActivity : AppCompatActivity() {

    private lateinit var viewModel : CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)
        if(!intent.hasExtra(EXTRA_FROM_SYMBOL_KEY))  {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL_KEY)
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        fromSymbol?.let {
            viewModel.getDetailInfo(fromSymbol).observe(this) {
                val symbolsTemplate = this.resources.getString(R.string.symbols_template)
                with(it) {
                    textViewDetailPriceInfo.text = price.toString()
                    textViewDailyMinimumInfo.text = lowDay.toString()
                    textViewDailyMaximumInfo.text = highDay.toString()
                    textViewLastTradeInfo.text = lastMarket
                    textViewLastUpdateInfo.text = getFormattedTime()
                    Picasso.get().load(getFullImageUrl()).into(imageViewCoinLogoDetail)
                    textViewDetailSymbols.text = String.format(symbolsTemplate, fromSymbol, toSymbol)
                }
        } }
    }
    companion object {
        private const val EXTRA_FROM_SYMBOL_KEY = "fSym"


    fun newIntent(context : Context, fSymForIntent :String) : Intent {
        val intent = Intent(context, CoinDetailActivity::class.java)
        intent.putExtra(EXTRA_FROM_SYMBOL_KEY, fSymForIntent)
        return intent
    }
    }
}