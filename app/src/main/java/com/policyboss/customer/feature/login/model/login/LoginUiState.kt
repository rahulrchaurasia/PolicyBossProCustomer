package com.policyboss.customer.feature.login.model.login

data class LoginUiState(

    val mobileNumber: String = "",

    val isMobileError: Boolean = false,

    val mobileErrorMessage: String? = null
)