package com.policyboss.customer.feature.privilege.model.privilegeState// PrivilegeContract.kt

// 1. What the screen displays
data class PrivilegeUiState(
    val isLoading: Boolean = false,
    val currentSetupStep: Int = 1
)



