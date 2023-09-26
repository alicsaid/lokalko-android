package com.example.lokalko.data.helpers

import com.example.lokalko.ui.navigation.NavigationDestination

sealed interface UiEvent {
    data class ShowToast(val messageId: Int) : UiEvent
    data class Navigate(val route: NavigationDestination) : UiEvent
}