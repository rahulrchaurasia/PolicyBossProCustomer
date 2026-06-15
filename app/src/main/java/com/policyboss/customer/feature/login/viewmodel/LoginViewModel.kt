package com.policyboss.customer.feature.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.policyboss.customer.feature.login.model.login.LoginEvent
import com.policyboss.customer.feature.login.model.login.LoginUiState
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
class LoginViewModel @Inject constructor() : ViewModel() {

    // =====================================
    // UI STATE
    // =====================================

    private val _uiState =
        MutableStateFlow(LoginUiState())

    val uiState =
        _uiState.asStateFlow()

    // =====================================
    // EVENTS
    // =====================================

    private val _event =
        MutableSharedFlow<LoginEvent>()

    val event =
        _event.asSharedFlow()

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

    // =====================================
    // SEND OTP
    // =====================================

    fun onSendOtpClick() {

        val mobile =
            uiState.value.mobileNumber

        val isValid =
            mobile.length == 10

        if (!isValid) {

            _uiState.update {

                it.copy(

                    isMobileError = true,

                    mobileErrorMessage =
                    "Enter valid 10-digit mobile number"
                )
            }

            return
        }

        viewModelScope.launch {

            _event.emit(

                LoginEvent.NavigateToVerifyOtp(

                    mobileNumber = mobile,

                    source = VerifyOtpSource.LOGIN
                )
            )
        }
    }
}