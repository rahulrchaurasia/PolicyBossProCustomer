package com.policyboss.customer.feature.privilege.privillageState

// PrivilegeContract.kt

// 1. What the screen displays
data class PrivilegeUiState(
    val isLoading: Boolean = false,
    val currentSetupStep: Int = 2,
    val isFloatingVideoVisible: Boolean = false // New flag for the PiP video


    //val privillageBenefits: List<PrivilegeBenefit> = emptyList(),
)



