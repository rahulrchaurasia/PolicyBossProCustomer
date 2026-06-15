package com.policyboss.customer.feature.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.policyboss.customer.feature.login.model.createAccount.CreateAccountEvent
import com.policyboss.customer.feature.login.model.createAccount.CreateAccountUiState
import com.policyboss.customer.feature.login.model.verifyAccount.VerifyOtpSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class CreateAccountViewModel @Inject constructor() : ViewModel() {

    // =========================================================
    // UI STATE
    // =========================================================

    private val _uiState =
        MutableStateFlow(CreateAccountUiState())

    val uiState =
        _uiState.asStateFlow()

    // =========================================================
    // EVENTS
    // =========================================================

    private val _event =
        MutableSharedFlow<CreateAccountEvent>()

    val event =
        _event.asSharedFlow()

    // =========================================================
    // NAME CHANGE
    // =========================================================

    fun onNameChange(value: String) {

        _uiState.update {

            it.copy(

                fullName = value,

                isNameError = false,

                nameErrorMessage = null
            )
        }
    }

    // =========================================================
    // MOBILE CHANGE
    // =========================================================

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
    // SEND OTP
    // =========================================================

    fun onSendOtpClick() {

        val state = _uiState.value

        val isNameValid =
            state.fullName.trim().isNotEmpty()

        val isMobileValid =
            state.mobileNumber.length == 10

        // =====================================================
        // VALIDATION
        // =====================================================

        if (!isNameValid || !isMobileValid) {

            _uiState.update {

                it.copy(

                    isNameError = !isNameValid,

                    nameErrorMessage =
                    if (!isNameValid)
                        "Full name is required"
                    else null,

                    isMobileError = !isMobileValid,

                    mobileErrorMessage =
                    if (!isMobileValid)
                        "Enter valid 10-digit mobile number"
                    else null
                )
            }

            return
        }

        // =====================================================
        // NAVIGATION EVENT
        // =====================================================

        viewModelScope.launch {

            _event.emit(

                CreateAccountEvent.NavigateToVerifyOtp(

                    fullName = state.fullName.trim(),

                    mobileNumber = state.mobileNumber,

                    source =   VerifyOtpSource.REGISTER
                )
            )
        }
    }
}