package com.policyboss.customer.root

sealed interface RootDestination {

    data object Loading : RootDestination

    data object Auth : RootDestination

    data object Main : RootDestination
}