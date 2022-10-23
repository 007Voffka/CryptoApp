package com.example.cryptoapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.FragmentCoinDetailInfoBinding
import com.squareup.picasso.Picasso

private const val ARG_PARAM_COIN_NAME = "coin_name"

class FragmentCoinDetailInfo : Fragment() {
    private var coinName: String? = null

    private var _binding : FragmentCoinDetailInfoBinding? = null
    private val binding : FragmentCoinDetailInfoBinding
        get() = _binding ?: throw RuntimeException("FragmentCoinDetailInfoBinding == null")

    private lateinit var viewModel : CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            coinName = it.getString(ARG_PARAM_COIN_NAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinDetailInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]

        coinName?.let {
            val symbolsTemplate = this.resources.getString(R.string.symbols_template)
            viewModel.getDetailInfo(it).observe(viewLifecycleOwner) { coinInfo ->
                with(coinInfo) {
                    binding.textViewDetailPriceInfo.text = price.toString()
                    binding.textViewDailyMinimumInfo.text = lowDay.toString()
                    binding.textViewDailyMaximumInfo.text = highDay.toString()
                    binding.textViewLastTradeInfo.text = lastMarket
                    binding.textViewLastUpdateInfo.text = lastUpdate
                    Picasso.get().load(imageUrl)
                        .into(binding.imageViewCoinLogoDetail)
                    binding.textViewDetailSymbols.text =
                        String.format(symbolsTemplate, fromSymbol, toSymbol)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        fun newInstance(coinName: String) =
            FragmentCoinDetailInfo().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM_COIN_NAME, coinName)
                }
            }
    }
}