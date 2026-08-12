package com.policyboss.customer.feature.profile.model

sealed interface ProfileEvent {



    object NavigateToLogin : ProfileEvent
}