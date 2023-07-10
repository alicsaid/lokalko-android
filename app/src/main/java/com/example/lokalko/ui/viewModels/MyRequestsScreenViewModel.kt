package com.example.lokalko.ui.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lokalko.data.model.Request
import com.example.lokalko.data.repository.LokalkoRepository
import com.example.lokalko.data.helpers.handleHttpException
import com.example.lokalko.data.helpers.handleNoInternetException
import com.example.lokalko.data.helpers.handleServerDownException
import com.example.lokalko.data.helpers.handleServerUnavailableException
import com.example.lokalko.data.model.Category
import com.example.lokalko.data.model.City
import com.example.lokalko.data.model.PostRequest
import com.example.lokalko.data.model.Severity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class MyRequestsScreenViewModel @Inject constructor(private val lokalkoRepository: LokalkoRepository) :
    ViewModel() {

    init {
        getRequests()
        getCategories()
        getSeverities()
        getCities()
    }

    val requests: MutableStateFlow<List<Request>?> = MutableStateFlow(null)
    val categories: MutableStateFlow<List<Category>?> = MutableStateFlow(null)
    val severities: MutableStateFlow<List<Severity>?> = MutableStateFlow(null)
    val cities: MutableStateFlow<List<City>?> = MutableStateFlow(null)
    val errorMessage: MutableState<String> = mutableStateOf("No Error")

    private fun getRequests() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = lokalkoRepository.getRequests()
                requests.value = response.requests
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

    private fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = lokalkoRepository.getCategories()
                categories.value = response.categories
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

    private fun getSeverities() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = lokalkoRepository.getSeverities()
                severities.value = response.severities
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

    private fun getCities() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = lokalkoRepository.getCities()
                cities.value = response.cities
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

//    fun postRequest(requestData: RequestBody, images: List<MultipartBody.Part>) {
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                val postRequest = PostRequest(requestData) // Kreirajte objekat PostRequest sa RequestBody
//                val response = lokalkoRepository.postRequest(postRequest, images)
//                // Obradite odgovor po potrebi
//                println("Request sent")
//            } catch (e: HttpException) {
//                handleHttpException(errorMessage, e.code())
//            } catch (e: UnknownHostException) {
//                // Server nije pristupačan (nema konekcije sa serverom)
//                handleServerUnavailableException(errorMessage)
//            } catch (e: SocketTimeoutException) {
//                // Vremensko ograničenje konekcije, moguće da je server pao
//                handleServerDownException(errorMessage)
//            } catch (e: ConnectException) {
//                // Nije moguće uspostaviti konekciju sa serverom (nema interneta)
//                handleNoInternetException(errorMessage)
//            }
//        }
//    }
}