package com.policyboss.customer.root

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.policyboss.customer.core.datastore.AppDataManager
import com.policyboss.customer.navigation.Dest

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RootViewModel @Inject constructor(
    private val appDataManager: AppDataManager
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(RootUiState())

    val uiState =
        _uiState.asStateFlow()

    init {
        checkSession()
    }

    private fun checkSession() {

        viewModelScope.launch {

            val isLoggedIn =
                appDataManager.isLogin.first()

            _uiState.update {

                it.copy(
                    isLoading = false,
                    startDestination =
                        if (isLoggedIn) {
                            Dest.MainGraph
                        } else {
                            Dest.CustomSplash
                        }
                )
            }
        }
    }
}