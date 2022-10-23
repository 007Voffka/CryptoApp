package com.example.cryptoapp.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptoapp.R

class CoinDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)
        if(!intent.hasExtra(EXTRA_FROM_SYMBOL_KEY))  {
            finish()
            return
        }
        val coinName = intent.getStringExtra(EXTRA_FROM_SYMBOL_KEY)
        coinName?.let {
            launchFragment(it)
        }
    }

    private fun launchFragment(coinName : String) {
        val fragment = FragmentCoinDetailInfo.newInstance(coinName)
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainerActivityCoinDetailInfo, fragment)
            .commit()
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