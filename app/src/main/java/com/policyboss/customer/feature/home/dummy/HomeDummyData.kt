package com.policyboss.customer.feature.home.dummy

import com.policyboss.customer.R
import com.policyboss.customer.feature.home.model.EarningBanner
import com.policyboss.customer.feature.home.model.PromoBanner
import com.policyboss.customer.feature.home.model.QuickAction
import com.policyboss.customer.feature.home.model.banner.BannerAction
import com.policyboss.customer.feature.home.model.banner.BannerDestination
import com.policyboss.customer.feature.home.model.policy.CuratedPolicy
import com.policyboss.customer.feature.home.model.vault.VaultPolicy
import com.policyboss.customer.feature.home.model.vault.VaultTabIds
import com.policyboss.customer.feature.home.model.vault.VaultTabItem

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.policyboss.customer.feature.home.model.BadgeType
import com.policyboss.customer.feature.home.model.PartnerLogoModel
import com.policyboss.customer.feature.home.model.bossepidia.BosspediaArticle
import com.policyboss.customer.feature.home.model.bossepidia.BosspediaStory
import com.policyboss.customer.feature.home.model.video.VideoModel

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



    //region curatedPolicies
    val curatedPolicies = listOf(


        CuratedPolicy(

            id = "car",

            title = "Car",

            iconRes = R.drawable.ic_car,

            backgroundColor = Color(0xFFEFF8FF),

            isComingSoon = false
        ),

        CuratedPolicy(

            id = "bike",

            title = "Bike",

            iconRes = R.drawable.ic_bike,

            backgroundColor = Color(0xFFEFF8FF),

            isComingSoon = true
        ),

        CuratedPolicy(

            id = "cv",

            title = "CV",

            iconRes = R.drawable.ic_cv,

            backgroundColor = Color(0xFFEFF8FF),

            isComingSoon = true
        ),

        CuratedPolicy(

            id = "health",

            title = "Health",

            iconRes = R.drawable.ic_health,

            backgroundColor = Color(0xFFEFF8FF),

            isComingSoon = true
        ),

        CuratedPolicy(

            id = "life",

            title = "Life",

            iconRes = R.drawable.ic_life,

            backgroundColor = Color(0xFFFDECEC),

            isComingSoon = true
        ),

        CuratedPolicy(

            id = "travel",

            title = "Travel",

            iconRes = R.drawable.ic_travel,

            backgroundColor = Color(0xFFEFF3FF),

            isComingSoon = true
        ),

        CuratedPolicy(

            id = "smeline",

            title = "SMELINE",

            iconRes = R.drawable.ic_smeline,

            backgroundColor = Color(0xFFFFF8E6),

            isComingSoon = true
        )
    )

    //endregion
    


    //region promoBanners
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
    //endregion

    //region quickActions
    val quickActions = listOf(

        QuickAction(

            id = "renew",

            title = "Renew & Earn",

            subtitle = "Become a 'Privileged user'",

            imageRes = R.drawable.ic_money,

            badge = BadgeType.Pro

        ),

        QuickAction(

            id = "vault",

            title = "Policy Vault",

            subtitle = "All your policies in one place",

            imageRes = R.drawable.ic_dashboard2,

            badge = BadgeType.NewPolicy

        ),

        QuickAction(

            id = "claim",

            title = "Claim Support",

            subtitle = "Guided claim filing & assistance",

            imageRes = R.drawable.ic_dashboard3

        ),

        QuickAction(

            id = "bosspedia",

            title = "BOSSPedia",

            subtitle = "Daily insights about insurance",

            imageRes = R.drawable.ic_dashboard4

        )

    )

    //endregion

    //region earningBanners
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
    //endregion

    //region vaultPolicies
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
        ),


        // ================= HEALTH =================

        VaultPolicy(
            id = "health_1",
            tabId = VaultTabIds.HEALTH,
            vehicleName = "Optima Secure Plan",
            vehicleNumber = "HLT89237498",
            vehicleImage = R.drawable.ic_health_card, // Assuming you have an icon like this
            daysLeft = "18 days left",
            companyLogo = R.drawable.img_tata,
            idv = "₹10L", // Used for Sum Insured
            premium = "₹14,500",
            expiry = "06.07.26",
            title = "Family Floater Cover"
        ),

        VaultPolicy(
            id = "health_2",
            tabId = VaultTabIds.HEALTH,
            vehicleName = "ReAssure 2.0",
            vehicleNumber = "HLT44556677",
            vehicleImage = R.drawable.ic_health_card,
            daysLeft = "90 days left",
            companyLogo = R.drawable.img_tata, // Assuming Niva Bupa or similar
            idv = "₹15L",
            premium = "₹18,200",
            expiry = "16.09.26",
            title = "Individual Cover"
        ),

        // ================= LIFE =================

        VaultPolicy(
            id = "life_1",
            tabId = VaultTabIds.LIFE,
            vehicleName = "Click 2 Protect",
            vehicleNumber = "LIF11223344",
            vehicleImage = R.drawable.ic_life01,
            daysLeft = "5 days left",
            companyLogo = R.drawable.img_tata,
            idv = "₹1Cr", // Used for Sum Assured
            premium = "₹12,400",
            expiry = "23.06.26",
            title = "Term Life Insurance"
        ),

        VaultPolicy(
            id = "life_2",
            tabId = VaultTabIds.LIFE,
            vehicleName = "Smart Wealth Plan",
            vehicleNumber = "LIF99887766",
            vehicleImage = R.drawable.ic_life01,
            daysLeft = "120 days left",
            companyLogo = R.drawable.img_tata, // Assuming Max Life or similar
            idv = "₹50L",
            premium = "₹45,000",
            expiry = "16.10.26",
            title = "ULIP Plan"
        ),

        // ================= TRAVEL =================

        VaultPolicy(
            id = "travel_1",
            tabId = VaultTabIds.TRAVEL,
            vehicleName = "Explore Asia Plan",
            vehicleNumber = "TRV55443322",
            vehicleImage = R.drawable.ic_airplane,
            daysLeft = "2 days left",
            companyLogo = R.drawable.img_tata,
            idv = "$100K", // Used for Coverage Amount
            premium = "₹1,250",
            expiry = "20.06.26",
            title = "Single Trip Cover"
        ),

        VaultPolicy(
            id = "travel_2",
            tabId = VaultTabIds.TRAVEL,
            vehicleName = "Schengen Comprehensive",
            vehicleNumber = "TRV66778899",
            vehicleImage = R.drawable.ic_airplane,
            daysLeft = "60 days left",
            companyLogo = R.drawable.img_tata,
            idv = "$500K",
            premium = "₹3,800",
            expiry = "17.08.26",
            title = "Multi-Trip Cover"
        ),

        // ================= SMELINE =================

        VaultPolicy(
            id = "sme_1",
            tabId = VaultTabIds.SMELINE,
            vehicleName = "Standard Fire & Special Perils",
            vehicleNumber = "SME10293847",
            vehicleImage = R.drawable.ic_frame,
            daysLeft = "15 days left",
            companyLogo = R.drawable.img_tata, // Assuming Bajaj Allianz or similar
            idv = "₹5Cr", // Used for Total Risk Value
            premium = "₹35,000",
            expiry = "03.07.26",
            title = "Property Insurance"
        ),

        VaultPolicy(
            id = "sme_2",
            tabId = VaultTabIds.SMELINE,
            vehicleName = "Workmen Compensation",
            vehicleNumber = "SME56473829",
            vehicleImage = R.drawable.ic_frame,
            daysLeft = "45 days left",
            companyLogo = R.drawable.img_tata,
            idv = "N/A", // Or appropriate limit
            premium = "₹12,500",
            expiry = "02.08.26",
            title = "Liability Insurance"
        )
    )
    //endregion

    //region vaultTabs
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
    //endregion


    //region Bosspedia
    val bosspediaStories = listOf(
        BosspediaStory("1", hasUnseenContent = true),
        BosspediaStory("2", hasUnseenContent = true),
        BosspediaStory("3", hasUnseenContent = false),
        BosspediaStory("4", hasUnseenContent = false),
        BosspediaStory("5", hasUnseenContent = true),
        BosspediaStory("6", hasUnseenContent = false),
        BosspediaStory("7", hasUnseenContent = false),
        BosspediaStory("8", hasUnseenContent = true)
    )

    val bosspediaArticles = listOf(
        BosspediaArticle(
            id = "a1",
            title = "How to Sell Insurance to Millennials",
            readTime = "5min"
        ),
        BosspediaArticle(
            id = "a2",
            title = "Understanding Health Insurance Claims",
            readTime = "4min"
        ),
        BosspediaArticle(
            id = "a3",
            title = "Top 5 Benefits of Term Life Insurance",
            readTime = "6min"
        ),
        BosspediaArticle(
            id = "a4",
            title = "Pitching Motor Renewals Effectively",
            readTime = "3min"
        ),
        BosspediaArticle(
            id = "a5",
            title = "Tax Benefits of Life Insurance in 2026",
            readTime = "7min"
        ),
        BosspediaArticle(
            id = "a6",
            title = "Handling Customer Objections Like a Pro",
            readTime = "5min"
        ),
        BosspediaArticle(
            id = "a7",
            title = "Why Comprehensive Car Insurance is a Must",
            readTime = "4min"
        ),
        BosspediaArticle(
            id = "a8",
            title = "Navigating Corporate SME Policies",
            readTime = "8min"
        ),
        BosspediaArticle(
            id = "a9",
            title = "Travel Insurance: The Upsell You're Missing",
            readTime = "3min"
        ),
        BosspediaArticle(
            id = "a10",
            title = "Building Trust in the Digital Insurance Age",
            readTime = "6min"
        )
    )
    //endregion

    //region Videos
    val dummyVideos = listOf(
        VideoModel(
            id = "1",
            title = "Health Insurance Coverage Explained",
            youtubeVideoId = "iXl8iH-Vu8M" // Replace with real PolicyBoss video IDs
        ),
        VideoModel(
            id = "2",
            title = "How to Claim Motor Insurance",
            youtubeVideoId = "k1Dovo96MHs"
        ),
        VideoModel(
            id = "3",
            title = "Understanding Term Life Insurance",
            youtubeVideoId = "ZNi93U2O3SM"
        )

    )
    //endregion

    //region Trusted Partners
    val partnerLogos = listOf(
        PartnerLogoModel(R.drawable.ic_footer1, 0.dp),
        PartnerLogoModel(R.drawable.ic_footer2, 24.dp),  // Right column drops down
        PartnerLogoModel(R.drawable.ic_footer3, 0.dp),
        PartnerLogoModel(R.drawable.ic_footer4, 24.dp),
        PartnerLogoModel(R.drawable.ic_footer5, 0.dp),
        PartnerLogoModel(R.drawable.ic_footer6, 24.dp),
    )
    //endregion

}