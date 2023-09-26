package com.example.lokalko.ui.viewModels

import android.util.Patterns
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lokalko.R
import com.example.lokalko.data.helpers.Result
import com.example.lokalko.data.helpers.UiEvent
import com.example.lokalko.data.helpers.handleHttpException
import com.example.lokalko.data.helpers.handleNoInternetException
import com.example.lokalko.data.helpers.handleServerDownException
import com.example.lokalko.data.helpers.handleServerUnavailableException
import com.example.lokalko.data.model.ServerResponse
import com.example.lokalko.data.preferences.Preferences
import com.example.lokalko.data.repository.LokalkoRepository
import com.example.lokalko.ui.screens.HomeDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class RegisterScreenViewModel @Inject constructor(
    private val lokalkoRepository: LokalkoRepository,
    private val preferences: Preferences
) :
    ViewModel() {

    val errorMessage: MutableState<String> = mutableStateOf("No Error")
    val serverResponse: MutableState<String> = mutableStateOf("")
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun register(
        email: String,
        password: String,
        confirmPassword: String,
        city: String,
        firstName: String,
        lastName: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            if (firstName.isEmpty() || lastName.isEmpty() || city.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                _uiEvent.send(UiEvent.ShowToast(R.string.please_fill_all_the_field))
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                _uiEvent.send(UiEvent.ShowToast(R.string.enter_valid_email))
            } else if (password.length < 6) {
                _uiEvent.send(UiEvent.ShowToast(R.string.password_must_me_6_char_long))
            } else if (password != confirmPassword) {
                _uiEvent.send(UiEvent.ShowToast(R.string.passwords_do_not_match))
            } else {
                when (val result = lokalkoRepository.register(
                    email,
                    password,
                    city,
                    firstName,
                    lastName
                )) {
                    is Result.Success -> {
                        preferences.saveToken("Bearer " + result.data.token)
                        _uiEvent.send(UiEvent.Navigate(HomeDestination))
                    }
                    is Result.Failure -> {
                        _uiEvent.send(UiEvent.ShowToast(result.errorMessageId))
                    }
                }
            }
        }
    }

    fun capitalizeText(text: String): String {
        return text.lowercase().replaceFirstChar { it.uppercase() }
    }
}