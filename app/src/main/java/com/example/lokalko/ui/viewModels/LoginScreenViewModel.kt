package com.example.lokalko.ui.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lokalko.R
import com.example.lokalko.data.helpers.Result
import com.example.lokalko.data.helpers.UiEvent
import com.example.lokalko.data.preferences.Preferences
import com.example.lokalko.data.repository.LokalkoRepository
import com.example.lokalko.ui.screens.HomeDestination
import com.example.lokalko.ui.screens.LoginDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val lokalkoRepository: LokalkoRepository,
    private val preferences: Preferences
) :
    ViewModel() {

    val errorMessage: MutableState<String> = mutableStateOf("No Error")
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (email.isEmpty() || password.isEmpty()) {
                _uiEvent.send(UiEvent.ShowToast(R.string.please_fill_all_the_field))
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                println("EMAIL REGEX")
                _uiEvent.send(UiEvent.ShowToast(R.string.enter_valid_email))
            } else if (password.length < 6) {
                _uiEvent.send(UiEvent.ShowToast(R.string.password_must_me_6_char_long))
            } else {
                when (val result = lokalkoRepository.login(email, password)) {
                    is Result.Success -> {
                        println("saved token")
                        preferences.saveToken("Bearer " + result.data.token)
                        delay(1000L)
                        _uiEvent.send(UiEvent.Navigate(HomeDestination))
                    }

                    is Result.Failure -> {
                        _uiEvent.send(UiEvent.ShowToast(result.errorMessageId))
                    }
                }
            }
        }
    }

    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = lokalkoRepository.logout()) {
                is Result.Success -> {
                    println("deleted token")
                    preferences.deleteToken()
                    _uiEvent.send(UiEvent.Navigate(LoginDestination))
                }

                is Result.Failure -> {
                    _uiEvent.send(UiEvent.ShowToast(result.errorMessageId))
                }
            }
        }
    }
}