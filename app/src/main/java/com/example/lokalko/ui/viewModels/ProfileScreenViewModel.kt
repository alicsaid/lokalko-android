package com.example.lokalko.ui.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lokalko.data.helpers.handleHttpException
import com.example.lokalko.data.helpers.handleNoInternetException
import com.example.lokalko.data.helpers.handleServerDownException
import com.example.lokalko.data.helpers.handleServerUnavailableException
import com.example.lokalko.data.model.UpdateUser
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
class ProfileScreenViewModel @Inject constructor(private val lokalkoRepository: LokalkoRepository) :
    ViewModel() {

    init {
        getUserData()
    }

    val user: MutableStateFlow<User?> = MutableStateFlow(null)
    val errorMessage: MutableState<String> = mutableStateOf("No Error")

    private fun getUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val userId = 13
                val response = lokalkoRepository.getUserData()
                user.value = response
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

    fun updateUser(email: String, firstName: String, lastName: String, city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val userId = 13
                val updateUser = UpdateUser(email, firstName, lastName, city)
                val response = lokalkoRepository.updateUser(userId, updateUser)
                println("$response")
            } catch (e: HttpException) {
                handleHttpException(errorMessage, e.code())
            } catch (e: UnknownHostException) {
                handleServerUnavailableException(errorMessage)
            } catch (e: SocketTimeoutException) {
                handleServerDownException(errorMessage)
            } catch (e: ConnectException) {
                handleNoInternetException(errorMessage)
            }
        }
    }
}