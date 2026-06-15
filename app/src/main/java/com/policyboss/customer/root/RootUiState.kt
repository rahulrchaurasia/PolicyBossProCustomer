package com.policyboss.customer.root

import com.policyboss.customer.navigation.Dest

data class RootUiState(

    val isLoading: Boolean = true,

    val startDestination: Dest? = null
)