package com.policyboss.customer.ui.components.badge

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.policyboss.customer.R
import com.policyboss.customer.feature.home.model.BadgeType
import com.policyboss.customer.ui.theme.AppColors

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment

import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.feature.home.model.backgroundColor
import com.policyboss.customer.feature.home.model.borderColor

import com.policyboss.customer.feature.home.model.iconRes
import com.policyboss.customer.feature.home.model.text
import com.policyboss.customer.feature.home.model.textColor
import com.policyboss.customer.ui.theme.captionSmall

@Composable
fun ActionBadge(

    badge: BadgeType,

    modifier: Modifier = Modifier

) {

    val shape = RoundedCornerShape(

        bottomStart = 14.dp,

        bottomEnd = 14.dp

    )

    Row(

        modifier = modifier

            .clip(shape)

            .border(

                width = 0.7.dp,

                color = badge.borderColor,

                shape = shape

            )

            .background(

                color = badge.backgroundColor,

                shape = shape

            )

            .padding(

                horizontal = 14.dp,

                vertical = 8.dp

            ),

        verticalAlignment = Alignment.CenterVertically

    ) {

        badge.iconRes?.let {

            Image(

                painter = painterResource(it),

                contentDescription = null,

                modifier = Modifier.size(15.dp)

            )

            Spacer(

                modifier = Modifier.width(6.dp)

            )
        }

        Text(

            text = badge.text,

            color = badge.textColor,

            style = MaterialTheme.typography.captionSmall

        )
    }
}
//@Composable
//public fun ActionBadge(
//
//    badge: BadgeType,
//
//    modifier: Modifier = Modifier
//
//) {
//
//    val backgroundColor: Color
//
//    val textColor: Color
//
//    val text: String
//
//    val icon: Int?
//
//
//
//    when (badge) {
//
//        BadgeType.Pro -> {
//
//            backgroundColor = AppColors.ProBadgeBackground
//
//            textColor = AppColors.ProBadgeText
//
//            text = "Pro Mode"
//
//            icon = R.drawable.ic_gold
//
//        }
//
//        BadgeType.NewPolicy -> {
//
//            backgroundColor = AppColors.NewBadgeBackground
//
//            textColor = AppColors.NewBadgeText
//
//            text = "New Policy"
//
//            icon = null
//        }
//    }
//
//    Row(
//
//        modifier = modifier
//
//            .clip(
//
//                RoundedCornerShape(
//
//                    bottomStart = 14.dp,
//
//                    bottomEnd = 14.dp
//
//                )
//
//            )
//
//            .background(backgroundColor)
//
//            .padding(
//
//                horizontal = 14.dp,
//
//                vertical = 10.dp
//
//            ),
//
//        verticalAlignment = Alignment.CenterVertically
//
//    ) {
//
//        icon?.let {
//
//            Image(
//
//                painter = painterResource(it),
//
//                contentDescription = null,
//
//                modifier = Modifier.size(18.dp)
//
//            )
//
//            Spacer(
//
//                modifier = Modifier.width(6.dp)
//
//            )
//        }
//
//        Text(
//
//            text = text,
//
//            color = textColor,
//
//            style = MaterialTheme.typography.labelMedium
//
//        )
//    }
//}

@Preview(showBackground = true)
@Composable
private fun ActionBadgePreview() {

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        ActionBadge(
            badge = BadgeType.Pro
        )

        ActionBadge(
            badge = BadgeType.NewPolicy
        )
    }
}