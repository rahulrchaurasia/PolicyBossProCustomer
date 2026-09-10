package com.policyboss.customer.feature.tabfeatures.policyVault.model.policyVaultModel

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import kotlinx.serialization.Serializable


// In your actions or models file
@Serializable
enum class AddPolicyType {
    CAR, BIKE, CV, HEALTH, LIFE, TRAVEL, SMELINE;

    // 2. Make it a computed property using 'get()'
    val displayTitle: String
        get() = when (this) { // 3. Use 'this' instead of 'productType'
            CAR -> "Car Insurance"
            BIKE -> "Bike Insurance"
            CV -> "Commercial Vehicle"
            HEALTH -> "Health Insurance"
            LIFE -> "Life Insurance"
            TRAVEL -> "Travel Insurance"
            SMELINE -> "SME Line"
            // 4. No 'else' branch needed! Kotlin knows you covered all cases.
        }

}

data class AddPolicyItem(
    val type: AddPolicyType,
    val title: String,
    @DrawableRes
    val iconRes: Int,
    val backgroundColor: Color,
    val iconTint: Color
)

