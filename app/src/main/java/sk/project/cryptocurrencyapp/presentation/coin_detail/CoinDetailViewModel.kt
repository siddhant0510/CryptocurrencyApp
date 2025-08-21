package sk.project.cryptocurrencyapp.presentation.coin_detail

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.Constraints
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import sk.project.cryptocurrencyapp.common.Constants
import sk.project.cryptocurrencyapp.common.Resource
import sk.project.cryptocurrencyapp.domain.use_case.get_coin.GetCoinUseCase
import sk.project.cryptocurrencyapp.domain.use_case.get_coins.GetCoinsUseCase
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandel: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> = _state

    init {
        savedStateHandel.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getCoin(coinId)
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    private fun getCoin(coinId: String) {
        getCoinUseCase(coinId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CoinDetailState(coin = result.data)
                }
                is Resource.Error -> {
                    _state.value = CoinDetailState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = CoinDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}