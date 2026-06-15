package com.policyboss.customer.feature.home.model.banner

sealed interface BannerDestination {

    data object Privilege : BannerDestination

    data class PolicyAction(
        val action: BannerAction
    ) : BannerDestination
}