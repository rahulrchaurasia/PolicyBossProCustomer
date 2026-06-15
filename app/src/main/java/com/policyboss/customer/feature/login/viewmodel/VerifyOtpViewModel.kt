package com.policyboss.customer.feature.login.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.policyboss.customer.core.datastore.AppDataManager
import com.policyboss.customer.feature.login.component.verifyAccount.OTPFieldState
import com.policyboss.customer.feature.login.model.verifyAccount.VerifyOtpEvent
import com.policyboss.customer.feature.login.model.verifyAccount.VerifyOtpSource
import com.policyboss.customer.feature.login.model.verifyAccount.VerifyOtpUiState
import kotlinx.coroutines.Job


import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class VerifyOtpViewModel @Inject constructor(
    private val appDataManager: AppDataManager
    // repository
) : ViewModel() {




    //region UI State
    // =========================================
    // UI STATE
    // =========================================
    private val _uiState =
        MutableStateFlow(VerifyOtpUiState())

    val uiState = _uiState.asStateFlow()
    //endregion

    //region Event
     // =========================================
     // EVENTS
     // =========================================
    private val _event = MutableSharedFlow<VerifyOtpEvent>()

    val event = _event.asSharedFlow()

    //endregion

    //region Declearation
    // =========================================
    // TIMER JOB
    // =========================================

    private var timerJob: Job? = null

    // =========================================
    // INIT
    // =========================================

    init {
        startResendTimer()
    }
    //endregion


    //region method

    // =========================================
    // OTP CHANGE
    // =========================================

    fun onOtpChange(
        otp: String,

        isComplete: Boolean,

        source: VerifyOtpSource,

        mobile: String
    ) {
       // Prevent typing beyond the max length
        if (otp.length > 4) return

        _uiState.update {

            it.copy(
                otp = otp,

                fieldState =
                OTPFieldState.DEFAULT,

                errorMessage = null
            )
        }

        // AUTO VERIFY
        if (isComplete && !_uiState.value.isVerifyingOtp) {

            verifyOtp(

                otp = otp,

                source = source,

                mobile = mobile
            )
        }
    }

    // =========================================
    // VERIFY OTP
    // =========================================

    private fun verifyOtp(otp: String,

                          source: VerifyOtpSource,

                          mobile: String
    ) {

        // =====================================
        // 1. STRICT VALIDATION CHECK
        // =====================================
        if (otp.length < 4) {
            _uiState.update {
                it.copy(
                    fieldState = OTPFieldState.ERROR,
                    errorMessage = "Please enter the complete 4-digit code"
                )
            }
            return // Stop execution immediately, do not call API
        }
        viewModelScope.launch {

            _uiState.update {

                it.copy(
                    isVerifyingOtp = true
                )
            }

            delay(2000)

            // =====================================
            // API CALL HERE
            // =====================================

            val isSuccess = otp == "0000"

            if (isSuccess) {

                // STOP TIMER
                pauseTimer()

                _uiState.update {

                    it.copy(
                        isVerifyingOtp = false,

                        fieldState = OTPFieldState.SUCCESS,

                        isOtpVerified = true
                    )
                }

                // WAIT LITTLE
                delay(500)

                // NAVIGATE EVENT HERE
                // SEND NAVIGATION EVENT

                onVerifyOtpSuccess(

                    source = source,

                    mobile = mobile
                )
//                _event.emit(
//                    VerifyOtpEvent.NavigateToLogin
//                )

            } else {

                _uiState.update {

                    it.copy(
                        isVerifyingOtp = false,

                        fieldState =  OTPFieldState.ERROR,

                        errorMessage =  "Invalid code"
                    )
                }
            }
        }
    }



    private suspend fun onVerifyOtpSuccess(

        source: VerifyOtpSource,

        mobile: String
    ) {

        when(source) {

            VerifyOtpSource.LOGIN -> {



                appDataManager.saveUserMobile(mobile)

                appDataManager.saveLoginState(true)

                _event.emit(
                    VerifyOtpEvent.NavigateToHome
                )
            }

            VerifyOtpSource.REGISTER -> {

                _event.emit(
                    VerifyOtpEvent.NavigateToLogin
                )
            }
        }
    }
    // =========================================
    // RESEND OTP
    // =========================================

    fun resendOtp() {

        // Guard: don't resend if already verifying
        if (_uiState.value.isVerifyingOtp) return


        // PAUSE TIMER
        pauseTimer()

        viewModelScope.launch {

            _uiState.update {

                it.copy(
                    isResendingOtp = true,

                    errorMessage = null,

                    resendMessage = null
                )
            }

            delay(1500)

            // =====================================
            // API CALL HERE
            // =====================================

            val isSuccess = true

            if (isSuccess) {

                _uiState.update {

                    it.copy(
                        isResendingOtp = false,

                        otp = "",

                        fieldState =
                        OTPFieldState.DEFAULT,

                        resendMessage =
                        "OTP resent successfully",

                        resendSeconds = 120
                    )
                }

                // START AGAIN
                startResendTimer()

            } else {

                _uiState.update {

                    it.copy(
                        isResendingOtp = false,

                        errorMessage =
                        "Failed to resend OTP"
                    )
                }

                // RESUME OLD TIMER
                resumeTimer()
            }
        }
    }

    // =========================================
    // START TIMER
    // =========================================

    private fun startResendTimer() {

        timerJob?.cancel()

        timerJob = viewModelScope.launch {

            while (_uiState.value.resendSeconds > 0) {

                delay(1000)

                _uiState.update {

                    it.copy(
                        resendSeconds =
                        it.resendSeconds - 1
                    )
                }
            }
        }
    }

    // =========================================
    // PAUSE TIMER
    // =========================================

    private fun pauseTimer() {
        timerJob?.cancel()
    }

    // =========================================
    // RESUME TIMER
    // =========================================

    private fun resumeTimer() {

        if (_uiState.value.resendSeconds > 0) {
            startResendTimer()
        }
    }

    //endregion
}