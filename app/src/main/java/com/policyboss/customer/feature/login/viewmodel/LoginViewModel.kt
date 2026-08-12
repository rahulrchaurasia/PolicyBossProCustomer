package com.policyboss.customer.feature.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.policyboss.customer.core.datastore.AppDataManager
import com.policyboss.customer.feature.login.model.login.LoginEvent
import com.policyboss.customer.feature.login.model.login.LoginUiState
import com.policyboss.customer.feature.login.model.verifyAccount.VerifyOtpSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

    private val appDataManager: AppDataManager
) : ViewModel() {

    // =====================================
    // UI STATE
    // =====================================

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    // =====================================
    // EVENTS
    // =====================================

    private val _event = MutableSharedFlow<LoginEvent>()
    val event = _event.asSharedFlow()

    // =====================================
    // MOBILE CHANGE
    // =====================================

    fun onMobileChange(value: String) {

        val digitsOnly =
            value.filter { it.isDigit() }

        if (digitsOnly.length <= 10) {

            _uiState.update {

                it.copy(

                    mobileNumber = digitsOnly,

                    isMobileError = false,

                    mobileErrorMessage = null
                )
            }
        }
    }

    // =========================================================
    // LOGIN ACTION
    // =========================================================
    fun onLoginClick() {
        val enteredMobile = _uiState.value.mobileNumber

        // 1. Basic Format Validation
        if (enteredMobile.length != 10) {
            _uiState.update {
                it.copy(
                    isMobileError = true,
                    mobileErrorMessage = "Please enter a valid 10-digit mobile number."
                )
            }
            return
        }

        viewModelScope.launch {
            // 2. Fetch the saved mobile number from DataStore
            // Because we imported kotlinx.coroutines.flow.first, this works natively!
            val savedMobile = appDataManager.userMobile.first()

            // 3. Validation Logic against DataStore
            when {
                savedMobile.isEmpty() -> {
                    // No user has been registered on this device yet
                    _uiState.update {
                        it.copy(
                            isMobileError = true,
                            mobileErrorMessage = "Account not found. Please create an account first."
                        )
                    }
                }
                savedMobile != enteredMobile -> {
                    // The number exists, but doesn't match the one entered
                    _uiState.update {
                        it.copy(
                            isMobileError = true,
                            mobileErrorMessage = "Invalid mobile number. Please check and try again."
                        )
                    }
                }
                else -> {
                    // Success! The numbers match.
                    _event.emit(
                        LoginEvent.NavigateToVerifyOtp(
                            mobileNumber = enteredMobile,
                            source = VerifyOtpSource.LOGIN
                        )
                    )
                }
            }
        }
    }
    // =====================================
    // SEND OTP
    // =====================================


    fun onSendOtpClick() {
        val enteredMobile = _uiState.value.mobileNumber

        // 1. Basic Format Validation
        if (enteredMobile.length != 10) {
            _uiState.update {
                it.copy(
                    isMobileError = true,
                    mobileErrorMessage = "Please enter a valid 10-digit mobile number."
                )
            }
            return
        }

        viewModelScope.launch {
            // 2. Fetch the saved mobile number from DataStore
            val savedMobile = appDataManager.userMobile.first()

            // 3. Validation Logic against DataStore
            when {
                savedMobile.isEmpty() -> {
                    _uiState.update {
                        it.copy(
                            isMobileError = true,
                            mobileErrorMessage = "Account not found. Please create an account first."
                        )
                    }
                }
                savedMobile != enteredMobile -> {
                    _uiState.update {
                        it.copy(
                            isMobileError = true,
                            mobileErrorMessage = "Invalid mobile number. Please check and try again."
                        )
                    }
                }
                else -> {
                    // Success!
                    _event.emit(
                        LoginEvent.NavigateToVerifyOtp(
                            mobileNumber = enteredMobile,
                            source = VerifyOtpSource.LOGIN
                        )
                    )
                }
            }
        }
    }
}