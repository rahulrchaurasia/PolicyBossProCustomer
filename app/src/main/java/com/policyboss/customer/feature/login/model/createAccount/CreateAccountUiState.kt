package com.policyboss.customer.feature.login.model.createAccount

data class CreateAccountUiState(

    val fullName: String = "",

    val mobileNumber: String = "",

    val isNameError: Boolean = false,

    val nameErrorMessage: String? = null,

    val isMobileError: Boolean = false,

    val mobileErrorMessage: String? = null,

    val isLoading: Boolean = false
)