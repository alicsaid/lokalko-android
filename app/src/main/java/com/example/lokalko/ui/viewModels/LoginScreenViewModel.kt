package com.example.lokalko.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lokalko.data.model.Categories
import com.example.lokalko.data.repository.LokalkoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(private val lokalkoRepository: LokalkoRepository) :
    ViewModel() {

    fun login(email: String, password: String, onLoginResult: (Boolean) -> Unit) {
        viewModelScope.launch(Dispatchers.Main) {
            val response = lokalkoRepository.login(email, password)

            if (response?.message == "Logged in!") {
                onLoginResult(true)
            } else {
                onLoginResult(false)
            }
        }
    }

    fun logout(onLoginResult: (Boolean) -> Unit) {
        viewModelScope.launch(Dispatchers.Main) {
            val response = lokalkoRepository.logout()

            if (response?.message == "Logged out!") {
                onLoginResult(true)
            } else {
                onLoginResult(false)
            }
        }
    }
}