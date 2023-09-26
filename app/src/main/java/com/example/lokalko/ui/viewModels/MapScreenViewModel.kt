package com.example.lokalko.ui.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lokalko.data.helpers.handleHttpException
import com.example.lokalko.data.helpers.handleNoInternetException
import com.example.lokalko.data.helpers.handleServerDownException
import com.example.lokalko.data.helpers.handleServerUnavailableException
import com.example.lokalko.data.model.City
import com.example.lokalko.data.model.Request
import com.example.lokalko.data.repository.LokalkoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class MapScreenViewModel @Inject constructor(private val lokalkoRepository: LokalkoRepository) :
    ViewModel() {

    init {
        getCityData()
        getRequestsByCity()
    }

    val reports: MutableStateFlow<List<Request>?> = MutableStateFlow(null)
    val city: MutableState<City?> = mutableStateOf(null)
    val errorMessage: MutableState<String> = mutableStateOf("No Error")

    private fun getRequestsByCity() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val cityId = 2 // TODO: postaviti grad logovanog korisnika
                val response = lokalkoRepository.getRequestsByCity(cityId = cityId)
                reports.value = response.requests
                println("$response")
            } catch (e: HttpException) {
                handleHttpException(errorMessage, e.code())
            } catch (e: UnknownHostException) {
                // Server nije pristupačan (nema konekcije sa serverom)
                handleServerUnavailableException(errorMessage)
            } catch (e: SocketTimeoutException) {
                // Vremensko ograničenje konekcije, moguće da je server pao
                handleServerDownException(errorMessage)
            } catch (e: ConnectException) {
                // Nije moguće uspostaviti konekciju sa serverom (nema interneta)
                handleNoInternetException(errorMessage)
            }
        }
    }

    private fun getCityData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val cityId = 2 // TODO: postaviti grad logovanog korisnika
                val response = lokalkoRepository.getCityData(cityId = cityId)
                city.value = response
                println("$response")
            } catch (e: HttpException) {
                handleHttpException(errorMessage, e.code())
            } catch (e: UnknownHostException) {
                // Server nije pristupačan (nema konekcije sa serverom)
                handleServerUnavailableException(errorMessage)
            } catch (e: SocketTimeoutException) {
                // Vremensko ograničenje konekcije, moguće da je server pao
                handleServerDownException(errorMessage)
            } catch (e: ConnectException) {
                // Nije moguće uspostaviti konekciju sa serverom (nema interneta)
                handleNoInternetException(errorMessage)
            }
        }
    }
}