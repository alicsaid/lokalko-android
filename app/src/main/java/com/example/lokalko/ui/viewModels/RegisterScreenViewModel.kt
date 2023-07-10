package com.example.lokalko.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lokalko.data.repository.LokalkoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterScreenViewModel @Inject constructor(private val lokalkoRepository: LokalkoRepository) :
    ViewModel() {

    fun register(firstName: String, lastName: String, city: String, email: String, password: String) {
        viewModelScope.launch(Dispatchers.Main) {
            val response = lokalkoRepository.register(firstName, lastName, city, email, password)
            println("$response")
        }
    }
}