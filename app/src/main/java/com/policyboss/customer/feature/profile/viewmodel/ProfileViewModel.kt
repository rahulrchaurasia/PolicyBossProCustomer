package com.policyboss.customer.feature.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.policyboss.customer.core.datastore.AppDataManager
import com.policyboss.customer.feature.profile.model.ProfileEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val appDataManager: AppDataManager
) : ViewModel() {

    private val _event =
        MutableSharedFlow<ProfileEvent>()

    val event =
        _event.asSharedFlow()

    fun logout() {

        viewModelScope.launch {

            appDataManager.clearSession()

            _event.emit(
                ProfileEvent.LogoutSuccess
            )
        }
    }
}