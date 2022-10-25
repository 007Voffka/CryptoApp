package com.example.cryptoapp.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptoapp.databinding.ActivityCoinDetailBinding

class CoinDetailActivity : AppCompatActivity() {

    private var _binding : ActivityCoinDetailBinding? = null
    private val binding : ActivityCoinDetailBinding
    get() = _binding ?: throw RuntimeException("ActivityCoinDetailBinding == null")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCoinDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
            .add(binding.fragmentContainerActivityCoinDetailInfo.id, fragment)
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

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}