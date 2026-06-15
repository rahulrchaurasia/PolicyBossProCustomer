package com.policyboss.customer.feature.profile.model

sealed interface ProfileEvent {

    data object LogoutSuccess : ProfileEvent
}