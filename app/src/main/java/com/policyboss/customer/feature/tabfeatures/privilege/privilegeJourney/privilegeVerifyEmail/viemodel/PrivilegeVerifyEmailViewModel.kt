package com.policyboss.customer.feature.tabfeatures.privilege.privilegeJourney.privilegeVerifyEmail.viemodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.policyboss.customer.feature.login.component.verifyAccount.OTPFieldState
import com.policyboss.customer.feature.login.model.verifyAccount.VerifyOtpUiState
import com.policyboss.customer.ui.components.otpVerify.CommonVerifyOtpEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class PrivilegeVerifyEmailViewModel @Inject constructor(
    // Inject your use cases or repositories here
) : ViewModel() {

    private val _uiState = MutableStateFlow(VerifyOtpUiState())
    val uiState: StateFlow<VerifyOtpUiState> = _uiState.asStateFlow()

    private val _uiEvent = Channel< CommonVerifyOtpEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var timerJob: Job? = null

    init {
        startResendTimer()
    }

    fun onOtpChange(otp: String, isComplete: Boolean) {
        _uiState.update { 
            it.copy(
                otp = otp,
                fieldState = OTPFieldState.DEFAULT, 
                errorMessage = null,
                resendMessage = null 
            ) 
        }
        
        if (isComplete) {
            verifyOtp(otp)
        }
    }

    private fun verifyOtp(otp: String) {
        _uiState.update { it.copy(isVerifyingOtp = true) }
        
        viewModelScope.launch {
            // TODO: Replace with your actual API call
            delay(1500.milliseconds)
            
            val isSuccess = otp == "0000" // Mock condition
            
            if (isSuccess) {
                _uiState.update { 
                    it.copy(
                        isVerifyingOtp = false, 
                        fieldState = OTPFieldState.SUCCESS, 
                        isOtpVerified = true 
                    ) 
                }
                delay(500.milliseconds) // Small delay to let the user see the success state
                _uiEvent.send(CommonVerifyOtpEvent.onNavigateNext)
            } else {
                _uiState.update { 
                    it.copy(
                        isVerifyingOtp = false, 
                        fieldState = OTPFieldState.ERROR, 
                        errorMessage = "Invalid OTP entered. Please try again." 
                    ) 
                }
            }
        }
    }

    fun resendOtp() {
        _uiState.update { it.copy(isResendingOtp = true) }
        
        viewModelScope.launch {
            // TODO: Replace with your actual API call to resend OTP
            delay(1000.milliseconds)
            
            _uiState.update { 
                it.copy(
                    isResendingOtp = false, 
                    resendMessage = "OTP resent successfully", 
                    resendSeconds = 120 
                ) 
            }
            startResendTimer()
        }
    }

    private fun startResendTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (_uiState.value.resendSeconds > 0) {
                delay(1000.milliseconds)
                _uiState.update { it.copy(resendSeconds = it.resendSeconds - 1) }
            }
        }
    }
}