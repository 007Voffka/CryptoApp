package com.example.cryptoapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.ActivityCoinPriceListBinding
import com.example.cryptoapp.domain.CoinInfo

class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel : CoinViewModel
    private var _binding : ActivityCoinPriceListBinding? = null
    private val binding : ActivityCoinPriceListBinding
    get() = _binding ?: throw RuntimeException("ActivityCoinPriceListBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCoinPriceListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.actionBar?.hide()
        val adapter = CoinInfoAdapter(this)

        if(binding.FragmentContainerMainActivityLand == null) {
            adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
                override fun onCoinClick(coinFullInfo: CoinInfo) {
                    val intent = CoinDetailActivity.newIntent(
                        this@CoinPriceListActivity,
                        coinFullInfo.fromSymbol
                    )
                    startActivity(intent)
                }
            }
        } else {
            adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
                override fun onCoinClick(coinFullInfo: CoinInfo) {
                    val fragment = FragmentCoinDetailInfo.newInstance(coinFullInfo.fromSymbol)
                    supportFragmentManager.popBackStack()
                    supportFragmentManager.beginTransaction()
                        .add(R.id.FragmentContainerMainActivityLand, fragment)
                        .addToBackStack(null)
                        .commit()
                }
            }
        }

        binding.recyclerViewCoinPriceList.adapter = adapter
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.coinInfoList.observe(this) {
            adapter.submitList(it)
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}