package com.policyboss.customer.feature.home.model.banner

sealed interface BannerAction {

    data object RenewEarn : BannerAction

    data object RenewCar : BannerAction

    data object RenewLife : BannerAction

    data object BuildPortfolioRenew : BannerAction

    data object BuildPortfolioSync : BannerAction
}