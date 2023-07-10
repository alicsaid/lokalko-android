package com.example.lokalko.ui.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lokalko.data.repository.LokalkoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val lokalkoRepository: LokalkoRepository) :
    ViewModel() {

    init {
        getRequestsAnalytics()
    }

    val totalRequests: MutableStateFlow<Int?> = MutableStateFlow(null)
    val error: MutableStateFlow<String?> = MutableStateFlow("No error")

    private fun getRequestsAnalytics() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val userId = 1
                val response = lokalkoRepository.getAnalytics(userId = userId)
                val totalRequestsValue = response.totalRequests
                totalRequests.value = totalRequestsValue
                println("$response")
            } catch (e: Exception) {
                error.value = e.message ?: "Error occurred"
            }
        }
    }
}