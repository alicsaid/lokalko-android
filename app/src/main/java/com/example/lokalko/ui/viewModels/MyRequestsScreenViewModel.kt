package com.example.lokalko.ui.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lokalko.data.helpers.handleHttpException
import com.example.lokalko.data.helpers.handleNoInternetException
import com.example.lokalko.data.helpers.handleServerDownException
import com.example.lokalko.data.helpers.handleServerUnavailableException
import com.example.lokalko.data.model.Category
import com.example.lokalko.data.model.City
import com.example.lokalko.data.model.Request
import com.example.lokalko.data.model.Severity
import com.example.lokalko.data.model.User
import com.example.lokalko.data.preferences.Preferences
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
class MyRequestsScreenViewModel @Inject constructor(
    private val lokalkoRepository: LokalkoRepository,
    private val preferences: Preferences
) :
    ViewModel() {

    init {
        getRequests()
        getCSC()
    }

    val maxChar35 = 35
    val maxChar70 = 70
    val title: MutableStateFlow<String> = MutableStateFlow("")
    val description: MutableStateFlow<String> = MutableStateFlow("")
    val address: MutableStateFlow<String> = MutableStateFlow("")
    val selectedCity: MutableStateFlow<City?> = MutableStateFlow(null)
    val selectedCategory: MutableStateFlow<Category?> = MutableStateFlow(null)
    val selectedSeverity: MutableStateFlow<Severity?> = MutableStateFlow(null)
    val latitude: MutableStateFlow<Double> = MutableStateFlow(43.9159)
    val longitude: MutableStateFlow<Double> = MutableStateFlow(17.6791)
    val requests: MutableStateFlow<List<Request>?> = MutableStateFlow(null)
    val categoryOptions: MutableStateFlow<List<Category>?> = MutableStateFlow(null)
    val severityOptions: MutableStateFlow<List<Severity>?> = MutableStateFlow(null)
    val cityOptions: MutableStateFlow<List<City>?> = MutableStateFlow(null)
    val errorMessage: MutableState<String> = mutableStateOf("No Error")

    fun setTitle(title: String) {
        if(title.length <= maxChar35) {
            this.title.value = capitalizeText(title)
        }
    }

    fun setDescription(description: String) {
        if(description.length <= maxChar70) {
            this.description.value = capitalizeText(description)
        }
    }

    fun setAddress(address: String) {
        if(address.length <= maxChar35) {
            this.address.value = capitalizeText(address)
        }
    }

    fun onSelectCity(city: City) {
        this.selectedCity.value = city
    }

    fun onSelectCategory(category: Category) {
        this.selectedCategory.value = category
    }

    fun onSelectSeverity(severity: Severity) {
        this.selectedSeverity.value = severity
    }

    private fun getRequests() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val userId = 1 // TODO: postaviti user_id logovanog korisnika
                val response = lokalkoRepository.getRequests(userId = userId)
                requests.value = response.requests
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

    private fun getCSC() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val citiesData = lokalkoRepository.getCities()
                val citiesList = citiesData.cities
                cityOptions.value = citiesList

                val severitiesData = lokalkoRepository.getSeverities()
                val severitiesList = severitiesData.severities
                severityOptions.value = severitiesList

                val categoriesData = lokalkoRepository.getCategories()
                val categoriesList = categoriesData.categories
                categoryOptions.value = categoriesList

                println("$categoriesList - $severitiesList - $citiesList")
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

    fun postRequest(
        title: String,
        description: String,
        address: String,
        city_id: Int,
        category_id: Int,
        severity_id: Int,
        latitude: Double,
        longitude: Double,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response =
                    lokalkoRepository.postRequest(title, description, address, city_id, category_id, severity_id, latitude, longitude)
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

    private fun capitalizeText(text: String): String {
        return text.lowercase().replaceFirstChar { it.uppercase() }
    }
}