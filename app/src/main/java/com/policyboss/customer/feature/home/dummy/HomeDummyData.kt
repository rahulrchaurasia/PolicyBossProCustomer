package com.policyboss.customer.feature.home.dummy

import com.policyboss.customer.R
import com.policyboss.customer.feature.home.model.EarningBanner
import com.policyboss.customer.feature.home.model.PromoBanner
import com.policyboss.customer.feature.home.model.QuickAction
import com.policyboss.customer.feature.home.model.banner.BannerAction
import com.policyboss.customer.feature.home.model.banner.BannerDestination
import com.policyboss.customer.feature.home.model.vault.VaultPolicy

/*
HomeDummyData
        │
        ▼
HomeViewModel
        │
        ▼
HomeUiState
        │
        ▼
HomeScreen
        │
        ▼
PolicyVaultSection
 */
object HomeDummyData {

    val curatedPolicies = listOf(
        "Car",
        "Bike",
        "CV",
        "Health",
        "Life",
        "Travel"
    )

    val promoBanners = listOf(
        PromoBanner(
            id = "renew_earn",
            tagText = "RENEW & EARN",
            title = "Become a ‘Privileged user’ and earn instantly on renewals",
            buttonText = "Complete Setup",
            imageRes = R.drawable.ic_banner1, // Replace with your actual drawable
            isYellowTheme = true, // Triggers the yellow gradient
            destination = BannerDestination.Privilege
        ),
        PromoBanner(
            id = "renew_car",
            tagText = "RENEW CAR INSURANCE",
            title = "Act fast — your ‘Honda Amaze’ protection expires in 21 days",
            buttonText = "Renew Now",
            imageRes = R.drawable.ic_banner2, // Replace with your actual drawable
            isYellowTheme = false, // Triggers the blue gradient
            destination = BannerDestination.PolicyAction(
                BannerAction.RenewCar
            )

        ),
        PromoBanner(
            id = "build_portfolio_renew",
            tagText = "BUILD YOUR POLICY PORTFOLIO",
            title = "Link and access all your policies in just one click",
            buttonText = "Renew Now",
            imageRes = R.drawable.ic_banner3, // Replace with your actual drawable
            isYellowTheme = false,
            destination = BannerDestination.PolicyAction(
                BannerAction.RenewLife
            )
        ),
        PromoBanner(
            id = "renew_life",
            tagText = "RENEW LIFE INSURANCE",
            title = "Act fast — your ‘life insurance’ expires in 21 days",
            buttonText = "Renew Now",
            imageRes = R.drawable.ic_banner5, // Replace with your actual drawable
            isYellowTheme = false,
            destination = BannerDestination.PolicyAction(
                BannerAction.BuildPortfolioRenew
            )
        ),
        PromoBanner(
            id = "build_portfolio_sync",
            tagText = "BUILD YOUR POLICY PORTFOLIO",
            title = "Link and access all your policies in just one click",
            buttonText = "Sync Email",
            imageRes = R.drawable.ic_banner4, // Replace with your actual drawable
            isYellowTheme = false,
            destination = BannerDestination.PolicyAction(
                BannerAction.BuildPortfolioSync
            )
        )

    )

    val quickActions = listOf(
        QuickAction(
            "renew",
            "Renew & Earn",
            "Become a 'Privileged user'",
            imageRes = R.drawable.ic_money,
            isPro = true
        ),
        QuickAction(
            "vault",
            "Policy Vault",
            "All your policies in one place",
            imageRes = R.drawable.ic_dashboard2,
        ),
        QuickAction(
            "claim",
            "Claim Support",
            "Guided claim filing & assistance",
            imageRes = R.drawable.ic_dashboard3,
        ), QuickAction(
            "bosspedia",
            "BOSSPedia",
            "Daily insights about insurance",
            imageRes = R.drawable.ic_dashboard4,
        )
    )
    val earningBanners = listOf(
        // Make sure to replace these with your actual drawable resources
        EarningBanner("1", "Earnings instantly hit your account upon renewal", R.drawable.ic_money),
        EarningBanner(
            "2",
            "Help your network save smart, while boosting your income.",
            R.drawable.ic_earning_banner2
        ),
        EarningBanner(
            "3",
            "Compare and secure the best policies curated for you",
            R.drawable.ic_earning_banner3
        ),
        EarningBanner(
            "4",
            "See transparent potential earnings on each policy",
            R.drawable.ic_earning_banner2
        )
    )
    val vaultPolicies = listOf(

        VaultPolicy(
            vehicleName = "Hyundai i20",

            vehicleNumber = "MH02AB1234",

            carImage = R.drawable.ic_car_protect,

            daysLeft = "12 days left",

            companyLogo = R.drawable.img_tata,

            idv = "₹7,50,000",

            premium = "₹12,500",

            expiry = "28 Jun 2026",
            title = "Title"
        ),

        VaultPolicy(
            vehicleName = "Honda City",

            vehicleNumber = "MH04CD5678",

            carImage = R.drawable.ic_car_protect,

            daysLeft = "30 days left",

            companyLogo = R.drawable.ic_banner3,

            idv = "₹9,20,000",

            premium = "₹14,800",

            expiry = "10 Jul 2026",

            title = "Title"
        )
    )
}