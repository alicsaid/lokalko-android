package com.example.lokalko.ui.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lokalko.data.helpers.handleHttpException
import com.example.lokalko.data.helpers.handleNoInternetException
import com.example.lokalko.data.helpers.handleServerDownException
import com.example.lokalko.data.helpers.handleServerUnavailableException
import com.example.lokalko.data.model.User
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
class HomeScreenViewModel @Inject constructor(private val lokalkoRepository: LokalkoRepository) :
    ViewModel() {

    init {
        getRequestsAnalytics()
        getReportedIssues()
        getResolvedIssues()
        getUserData()
    }

    val totalRequests: MutableStateFlow<Int?> = MutableStateFlow(null)
    val reportedIssues: MutableStateFlow<Int?> = MutableStateFlow(null)
    val resolvedIssues: MutableStateFlow<Int?> = MutableStateFlow(null)
    val error: MutableStateFlow<String?> = MutableStateFlow("No error")
    val errorMessage: MutableState<String> = mutableStateOf("No Error")
    val user: MutableState<User?> = mutableStateOf(null)

    private fun getRequestsAnalytics() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                //val userId = 1 // TODO: postaviti user_id logovanog korisnika
                val response = lokalkoRepository.getAnalytics()
                val totalRequestsValue = response.totalRequests
                totalRequests.value = totalRequestsValue
                println("$response")
            } catch (e: Exception) {
                error.value = e.message ?: "Error occurred"
            }
        }
    }

    private fun getUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val userId = 1 // TODO: postaviti user_id logovanog korisnika
                val response = lokalkoRepository.getUserData(userId = userId)
                user.value = response
                println("$response")
            } catch (e: UnknownHostException) {
                // Server nije pristupačan (nema konekcije sa serverom)
                handleServerUnavailableException(errorMessage)
            } catch (e: SocketTimeoutException) {
                // Vremensko ograničenje konekcije, moguće da je server pao
                handleServerDownException(errorMessage)
            } catch (e: ConnectException) {
                // Nije moguće uspostaviti konekciju sa serverom (nema interneta)
                handleNoInternetException(errorMessage)
            } catch (e: HttpException) {
                // Klijent errori
                handleHttpException(errorMessage, e.code())
            }
        }
    }

    private fun getReportedIssues() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = lokalkoRepository.getReportedIssues()
                reportedIssues.value = response.reportedIssues
                println("rep $response")
            } catch (e: UnknownHostException) {
                // Server nije pristupačan (nema konekcije sa serverom)
                handleServerUnavailableException(errorMessage)
            } catch (e: SocketTimeoutException) {
                // Vremensko ograničenje konekcije, moguće da je server pao
                handleServerDownException(errorMessage)
            } catch (e: ConnectException) {
                // Nije moguće uspostaviti konekciju sa serverom (nema interneta)
                handleNoInternetException(errorMessage)
            } catch (e: HttpException) {
                // Klijent errori
                handleHttpException(errorMessage, e.code())
            }
        }
    }

    private fun getResolvedIssues() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = lokalkoRepository.getResolvedIssues()
                resolvedIssues.value = response.resolvedIssues
                println("res $response")
            } catch (e: UnknownHostException) {
                // Server nije pristupačan (nema konekcije sa serverom)
                handleServerUnavailableException(errorMessage)
            } catch (e: SocketTimeoutException) {
                // Vremensko ograničenje konekcije, moguće da je server pao
                handleServerDownException(errorMessage)
            } catch (e: ConnectException) {
                // Nije moguće uspostaviti konekciju sa serverom (nema interneta)
                handleNoInternetException(errorMessage)
            } catch (e: HttpException) {
                // Klijent errori
                handleHttpException(errorMessage, e.code())
            }
        }
    }
}