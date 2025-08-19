package sk.project.cryptocurrencyapp.data.repository

import sk.project.cryptocurrencyapp.data.remote.CoinPaprikaApi
import sk.project.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import sk.project.cryptocurrencyapp.data.remote.dto.CoinDto
import sk.project.cryptocurrencyapp.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepository {

    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}