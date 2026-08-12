package com.policyboss.customer.feature.policyVault.model.policyVaultModel

//enum class SortOption(
//    val title: String
//) {
//
//    UPCOMING_RENEWALS("Upcoming Renewals"),
//
//    MOST_RECENT("Most Recent"),
//
//    PREMIUM_HIGH_TO_LOW("Premium High → Low"),
//
//    PREMIUM_LOW_TO_HIGH("Premium Low → High")
//}

enum class SortOption(val displayName: String) {
    UPCOMING_RENEWALS("Upcoming Renewals"),
    MOST_RECENT("Most Recent Policies"),
    PREMIUM_HIGH_TO_LOW("Premiums from Highest to Lowest"),
    PREMIUM_LOW_TO_HIGH("Premiums from Lowest to Highest")
}