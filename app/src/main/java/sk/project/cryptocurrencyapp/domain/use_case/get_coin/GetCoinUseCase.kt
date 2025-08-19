package sk.project.cryptocurrencyapp.domain.use_case.get_coin

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import sk.project.cryptocurrencyapp.common.Resource
import sk.project.cryptocurrencyapp.data.remote.dto.toCoin
import sk.project.cryptocurrencyapp.data.remote.dto.toCoinDetail
import sk.project.cryptocurrencyapp.domain.model.Coin
import sk.project.cryptocurrencyapp.domain.model.CoinDetail
import sk.project.cryptocurrencyapp.domain.repository.CoinRepository
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coin = repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success(coin))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}