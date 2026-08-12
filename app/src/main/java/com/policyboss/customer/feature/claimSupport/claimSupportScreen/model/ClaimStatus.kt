package com.policyboss.customer.feature.claimSupport.claimSupportScreen.model

enum class ClaimStatus(
    val apiValue: String,
    val displayName: String
) {

    FILED(
        apiValue = "FILED",
        displayName = "Filed"
    ),

    UNDER_REVIEW(
        apiValue = "UNDER_REVIEW",
        displayName = "Under Review"
    ),

    APPROVED(
        apiValue = "APPROVED",
        displayName = "Approved"
    ),

    REJECTED(
        apiValue = "REJECTED",
        displayName = "Rejected"
    ),

    SETTLED(
        apiValue = "SETTLED",
        displayName = "Settled"
    ),

    CLOSED(
        apiValue = "CLOSED",
        displayName = "Closed"
    );

    companion object {

        fun from(value: String): ClaimStatus {
            return entries.firstOrNull {
                it.apiValue.equals(value, ignoreCase = true)
            } ?: FILED
        }
    }
}