package com.policyboss.customer.feature.claimSupport.claimSupportScreen.model

import androidx.annotation.DrawableRes
import com.policyboss.customer.R

enum class ClaimSupportMenu(
    val title: String,
    @DrawableRes val icon: Int
) {

    FILE_GUIDE(
        "File a Claim Guide",
        R.drawable.ic_claim_guide
    ),

    CASHLESS_GARAGE(
        "Find Cashless Garages",
        R.drawable.ic_garage
    ),

    INSURER_CONTACT(
        "Contact List of Insurers",
        R.drawable.ic_contact
    ),

    FAQ(
        "FAQs",
        R.drawable.ic_faq
    )
}