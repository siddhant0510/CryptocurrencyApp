package sk.project.cryptocurrencyapp.presentation.coin_list

import sk.project.cryptocurrencyapp.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
