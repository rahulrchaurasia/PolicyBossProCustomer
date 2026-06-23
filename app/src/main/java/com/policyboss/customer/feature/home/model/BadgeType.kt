package com.policyboss.customer.feature.home.model

import androidx.compose.ui.graphics.Color
import com.policyboss.customer.R
import com.policyboss.customer.ui.theme.AppColors

sealed class BadgeType {

    data object Pro : BadgeType()

    data object NewPolicy : BadgeType()
}

//region comment : if we keep border Pro and New
val BadgeType.borderColor: Color
    get() = when (this) {

        BadgeType.Pro ->
            AppColors.ProCardBorder

        BadgeType.NewPolicy ->
            AppColors.NewBadgeBorder
    }
//endregion

val BadgeType.text: String
    get() = when (this) {

        BadgeType.Pro ->
            "Pro Mode"

        BadgeType.NewPolicy ->
            "New Policy"
    }

val BadgeType.textColor: Color
    get() = when (this) {

        BadgeType.Pro ->
            AppColors.ProBadgeText

        BadgeType.NewPolicy ->
            AppColors.NewBadgeText
    }
val BadgeType.backgroundColor: Color
    get() = when (this) {

        BadgeType.Pro ->
            AppColors.ProBadgeBackground

        BadgeType.NewPolicy ->
            AppColors.NewBadgeBackground
    }
val BadgeType.iconRes: Int?
    get() = when (this) {

        BadgeType.Pro ->
            R.drawable.ic_privilege

        BadgeType.NewPolicy ->
            null
    }