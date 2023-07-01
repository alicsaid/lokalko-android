package com.example.lokalko.ui.viewModels

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
        //getCategories()
    }

    //val categories: MutableStateFlow<Categories?> = MutableStateFlow(null)

    /*private fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = lokalkoRepository.getCategories()
            categories.value = response
            println("$response")
        }
    }*/
}