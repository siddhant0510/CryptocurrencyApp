package sk.project.cryptocurrencyapp.presentation.coin_detail

import sk.project.cryptocurrencyapp.domain.model.Coin
import sk.project.cryptocurrencyapp.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)
