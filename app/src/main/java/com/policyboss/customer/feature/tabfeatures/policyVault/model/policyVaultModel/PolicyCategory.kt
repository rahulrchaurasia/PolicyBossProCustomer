package com.policyboss.customer.feature.tabfeatures.policyVault.model.policyVaultModel

enum class PolicyCategory(
    val id: Int,
    val title: String
) {

    ALL(-1, "All"),

    MOTOR(0, "Motor"),

    BIKE(1, "Bike"),

    CV(2, "CV"),

    HEALTH(3, "Health"),

    LIFE(4, "Life"),

    TRAVEL(5, "Travel"),

    SMELINE(6, "Smeline");

    companion object {

        fun fromId(id: Int): PolicyCategory {

            return entries.firstOrNull { it.id == id } ?: ALL
        }
    }
}