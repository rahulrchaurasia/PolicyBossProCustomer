package com.policyboss.customer.feature.tabfeatures.privilege.privilegeJourney.privilegeVerifyEmail.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

import com.policyboss.customer.feature.tabfeatures.privilege.privilegeJourney.privilegeVerifyEmail.viemodel.PrivilegeVerifyEmailViewModel
import com.policyboss.customer.ui.components.otpVerify.CommonVerifyOtpEvent

@Composable
fun PrivilegeVerifyEmailRoute(
    mobileNumber: String,
    viewModel: PrivilegeVerifyEmailViewModel,
    onNavigateBack: () -> Unit,
    onNavigateNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // Handle single-shot UI events from the ViewModel
    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
              is  CommonVerifyOtpEvent.onNavigateBack ->  onNavigateBack()
              is  CommonVerifyOtpEvent.onNavigateNext -> onNavigateNext()
            }
        }
    }

    PrivilegeVerifyEmailScreen(
        uiState = uiState,
        mobileNumber = mobileNumber,
        onOtpChange = viewModel::onOtpChange,
        onResendClick = viewModel::resendOtp,
                modifier = modifier
    )
}

