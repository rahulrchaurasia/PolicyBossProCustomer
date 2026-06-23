package com.policyboss.customer.feature.home.component.home.card


// ---------------------------- IMPORTS ----------------------------

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.ui.res.painterResource

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Surface


import com.policyboss.customer.R
import com.policyboss.customer.feature.home.dummy.HomeDummyData
import com.policyboss.customer.feature.home.model.BadgeType
import com.policyboss.customer.feature.home.model.QuickAction

import com.policyboss.customer.ui.components.badge.ActionBadge
import com.policyboss.customer.ui.theme.AppColors
import com.policyboss.customer.ui.theme.captionSmall

@Composable
fun QuickActionCard(

    action: QuickAction,

    modifier: Modifier = Modifier,

    onClick: () -> Unit

) {

    val shape = RoundedCornerShape(24.dp)

    val borderModifier =

        if (action.badge == BadgeType.Pro)

            Modifier.border(

                width = 0.7.dp,

                color = AppColors.ProCardBorder,

                shape = shape

            )

        else

            Modifier

    Box(

        modifier = modifier

            .height(205.dp)

            .clip(shape)

            .background(AppColors.CardBackground)

            .then(borderModifier)

            //region comment : if we keep border Pro and New
//            .then(
//
//                action.badge?.let {
//
//                    Modifier.border(
//                        width = 0.7.dp,
//                        color = it.borderColor,
//                        shape = shape
//                    )
//
//                } ?: Modifier
//
//            )
            //endregion

            .clickable {

                onClick()

            }

    ) {

        Column(

            modifier = Modifier

                .fillMaxSize()

                .padding(top = 26.dp)
                .padding(horizontal = 24.dp)
                .padding(bottom = 12.dp)


        ) {

            Image(

                painter = painterResource(action.imageRes),

                contentDescription = action.title,

                modifier = Modifier.size(62.dp)

            )

            Spacer(

                modifier = Modifier.height(20.dp)

            )

            Text(

                text = action.title.uppercase(),

                style = MaterialTheme.typography.captionSmall,

                color = AppColors.TextSecondary

            )

            Spacer(

                modifier = Modifier.height(8.dp)

            )

            Text(

                text = action.subtitle,

                style = MaterialTheme.typography.headlineSmall,

                color = AppColors.TextPrimary

            )
        }

        action.badge?.let {

            ActionBadge(

                badge = it,

                modifier = Modifier

                    .align(Alignment.TopEnd)

                    .padding(end = 18.dp)

            )
        }
    }
}




@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
    widthDp = 400
)
@Composable
private fun QuickActionCardPreview() {

    MaterialTheme {

        Surface(
            modifier = Modifier.padding(16.dp)
        ) {

            Row(

                horizontalArrangement = Arrangement.spacedBy(16.dp)

            ) {

                HomeDummyData.quickActions
                    .take(2)
                    .forEach { action ->

                        QuickActionCard(

                            modifier = Modifier.weight(1f),

                            action = action,

                            onClick = {}

                        )
                    }
            }
        }
    }
}