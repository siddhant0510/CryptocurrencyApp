package sk.project.cryptocurrencyapp.domain.repository

import sk.project.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import sk.project.cryptocurrencyapp.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}