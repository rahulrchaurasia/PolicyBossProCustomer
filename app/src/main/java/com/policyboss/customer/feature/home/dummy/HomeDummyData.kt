package com.policyboss.customer.feature.home.dummy

import com.policyboss.customer.R
import com.policyboss.customer.feature.home.model.EarningBanner
import com.policyboss.customer.feature.home.model.PromoBanner
import com.policyboss.customer.feature.home.model.QuickAction
import com.policyboss.customer.feature.home.model.banner.BannerAction
import com.policyboss.customer.feature.home.model.banner.BannerDestination
import com.policyboss.customer.feature.home.model.vault.VaultPolicy
import com.policyboss.customer.feature.home.model.vault.VaultTabIds
import com.policyboss.customer.feature.home.model.vault.VaultTabItem

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

        // ================= MOTOR =================

        VaultPolicy(

            id = "motor_1",

            tabId = VaultTabIds.MOTOR,

            vehicleName = "Honda Amaze",

            vehicleNumber = "MH12AB3456",

            vehicleImage = R.drawable.ic_car_protect,

            daysLeft = "21 days left",

            companyLogo = R.drawable.img_tata,

            idv = "₹5L",

            premium = "₹6,403",

            expiry = "21.02.26",

            title = "Comprehensive Cover"
        ),

        VaultPolicy(

            id = "motor_2",

            tabId = VaultTabIds.MOTOR,

            vehicleName = "Hyundai i20",

            vehicleNumber = "MH02AB1234",

            vehicleImage = R.drawable.ic_car_protect,

            daysLeft = "12 days left",

            companyLogo = R.drawable.img_tata,

            idv = "₹6L",

            premium = "₹7,250",

            expiry = "05.03.26",

            title = "Zero Dep Cover"
        ),

        VaultPolicy(

            id = "motor_3",

            tabId = VaultTabIds.MOTOR,

            vehicleName = "Tata Nexon",

            vehicleNumber = "MH14XY5678",

            vehicleImage = R.drawable.ic_car_protect,

            daysLeft = "40 days left",

            companyLogo = R.drawable.img_tata,

            idv = "₹10L",

            premium = "₹9,850",

            expiry = "15.04.26",

            title = "Third Party Cover"
        ),

        // ================= BIKE =================

        VaultPolicy(

            id = "bike_1",

            tabId = VaultTabIds.BIKE,

            vehicleName = "Royal Enfield Classic",

            vehicleNumber = "MH01EF9012",

            vehicleImage = R.drawable.ic_bike01,

            daysLeft = "45 days left",

            companyLogo = R.drawable.img_tata,

            idv = "₹2L",

            premium = "₹2,100",

            expiry = "15.03.26",

            title = "Comprehensive Cover"
        ),

        VaultPolicy(

            id = "bike_2",

            tabId = VaultTabIds.BIKE,

            vehicleName = "TVS Jupiter",

            vehicleNumber = "MH14JK8888",

            vehicleImage = R.drawable.ic_bike01,

            daysLeft = "70 days left",

            companyLogo = R.drawable.img_tata,

            idv = "₹90K",

            premium = "₹1,800",

            expiry = "25.05.26",

            title = "Third Party Cover"
        ),

        // ================= CV =================

        VaultPolicy(

            id = "cv_1",

            tabId = VaultTabIds.CV,

            vehicleName = "Ashok Leyland Truck",

            vehicleNumber = "MH20TR6789",

            vehicleImage = R.drawable.ic_cv01,

            daysLeft = "28 days left",

            companyLogo = R.drawable.img_tata,

            idv = "₹20L",

            premium = "₹18,500",

            expiry = "01.04.26",

            title = "Commercial Vehicle Cover"
        )

        // Health

        // Life

        // Travel

        // SMELINE

        // intentionally empty for now
    )


    val vaultTabs = listOf(

        VaultTabItem(
            id = 0,
            title = "Motor",
            iconRes = R.drawable.ic_motor01
        ),

        VaultTabItem(
            id = 1,
            title = "Bike",
            iconRes = R.drawable.ic_bike01
        ),

        VaultTabItem(
            id = 2,
            title = "CV",
            iconRes = R.drawable.ic_cv01
        ),

        VaultTabItem(
            id = 3,
            title = "Health",
            iconRes = R.drawable.ic_health01
        ),

        VaultTabItem(
            id = 4,
            title = "Life",
            iconRes = R.drawable.ic_life01
        ),

        VaultTabItem(
            id = 5,
            title = "Travel",
            iconRes = R.drawable.ic_travel01
        ),

        VaultTabItem(
            id = 6,
            title = "SMELINE",
            iconRes = R.drawable.ic_smeline01
        )
    )
}