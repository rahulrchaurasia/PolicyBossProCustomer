package com.policyboss.customer.feature.home.model.bossepidia

data class BosspediaStory(
    val id: String,
    val imageUrl: String? = null, // In case you fetch real images later
    val hasUnseenContent: Boolean = false // Determines if the blue border shows
)