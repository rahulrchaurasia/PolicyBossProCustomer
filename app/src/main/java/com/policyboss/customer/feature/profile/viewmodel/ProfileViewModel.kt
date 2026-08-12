package com.policyboss.customer.feature.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.policyboss.customer.core.datastore.AppDataManager
import com.policyboss.customer.feature.profile.model.ProfileEvent
import com.policyboss.customer.feature.profile.model.ProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val appDataManager: AppDataManager
) : ViewModel() {

    // 🚀 CHANGE 5: Expose the StateFlow to the UI
//    private val _uiState = MutableStateFlow(ProfileUiState())
//    val uiState = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<ProfileEvent>()
    val event = _event.asSharedFlow()

    // 🚀 CLEANER APPROACH: Use stateIn to directly convert the combined flows into a StateFlow
    val uiState = combine(
        appDataManager.userName,
        appDataManager.userEmail,
        appDataManager.userMobile
    ) { name, email, mobile ->
        ProfileUiState(
            userName = name,
            userEmail = email,
            userMobile = mobile
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ProfileUiState() // Default empty state while loading
    )


//    init {
//        // 🚀 CHANGE 6: Observe DataStore and update UI State automatically
//        viewModelScope.launch {
//            combine(
//                appDataManager.userName,
//                appDataManager.userEmail,
//                appDataManager.userMobile
//            ) { name, email, mobile ->
//                ProfileUiState(userName = name, userEmail = email, userMobile = mobile)
//            }.collect { state ->
//                _uiState.value = state
//            }
//        }
//    }
    fun logout() {
        // 1. Launch a coroutine to do background work
        viewModelScope.launch {
            // 2. Clear the session/database first
            appDataManager.clearSession()

            // 3. Emit the event to tell the UI to move
            _event.emit(ProfileEvent.NavigateToLogin)
        }
    }



    fun loginWithRandomUser(onLoginSuccess: () -> Unit) {
        viewModelScope.launch {
            // 1. Pick a random user from our mock list
           // val randomUser = mockUsersList.random()

            // 2. Save them to the DataStore
            appDataManager.clearSession()

            // 3. Trigger the navigation callback to go to MainGraph
            onLoginSuccess()
        }
    }
}