package com.policyboss.customer.feature.login.model.createAccount

data class CreateAccountUiState(

    val fullName: String = "",
    val isNameError: Boolean = false,
    val nameErrorMessage: String? = null,

    val mobileNumber: String = "",
    val isMobileError: Boolean = false,
    val mobileErrorMessage: String? = null,


    // ADD THESE EMAIL PROPERTIES
    val emailId: String = "", // Added
    val isEmailError: Boolean = false, // Added
    val emailErrorMessage: String? = null, // Added

    val isLoading: Boolean = false,


)