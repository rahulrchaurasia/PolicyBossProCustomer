package com.policyboss.customer.utils

object AppValidator {


    // =====================================================
    // REGISTRATION / LOGIN VALIDATION
    // =====================================================

    fun isValidName(name: String): Boolean {
        return name.trim().isNotEmpty()
    }

    fun isValidMobile(mobile: String): Boolean {
        // You can add the filter { it.isDigit() } check here if needed,
        // but since you restrict input in the UI, length is usually enough.
        return mobile.trim().length == 10
    }
    /**
     * Validates an optional mobile number.
     * Returns TRUE if it is completely blank.
     * Returns TRUE if it is exactly 10 digits.
     * Returns FALSE if it is partially filled (1 to 9 digits).
     */
    fun isValidOptionalMobile(mobile: String): Boolean {
        val cleanMobile = mobile.trim()
        if (cleanMobile.isEmpty()) return true
        return cleanMobile.length == 10
    }
    fun isValidEmail(email: String): Boolean {
        val emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,}\$".toRegex()
        return email.trim().isNotEmpty() && emailPattern.matches(email.trim())
    }

    // =====================================================
    // INSURANCE / VEHICLE VALIDATION
    // =====================================================

    // Validates basic Indian Vehicle Numbers (e.g., MH12AB1234)
    fun isValidVehicleNumber(number: String): Boolean {
        // Strip out any spaces or hyphens the user might have typed manually
        val cleanNumber = number.replace(Regex("[ -]"), "")
        
        // Basic check: Must be alphanumeric and generally between 8 to 11 characters
        // (Allows for standard state plates and modern BH series)
        return cleanNumber.isNotBlank() && 
               cleanNumber.length in 8..11 && 
               cleanNumber.all { it.isLetterOrDigit() }
    }

    // Validates a generic policy number
    fun isValidPolicyNumber(number: String): Boolean {
        // Assuming policy numbers must be at least 5 characters long
        return number.isNotBlank() && number.length >= 5
    }
}