package com.policyboss.customer.ui.components.bottomSheet.policyProtectedBottomSheet.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.policyboss.customer.R

@Composable
fun PolicyHeaderImage() {

    Box(
        contentAlignment = Alignment.Center
    ) {

        // Shield (background)
        Image(
            painter = painterResource(
                R.drawable.ic_security_check
            ),

            contentDescription = null,

            modifier = Modifier
                .size(120.dp)

                // Move slightly left
                .offset(
                    x = (-18).dp,
                    y = (-6).dp
                )

                // Slight left rotation
                .rotate(-12f)
        )

        Image(
            painter = painterResource(
                R.drawable.ic_car_protect
            ),

            contentDescription = null,

            modifier = Modifier
                .width(180.dp)
                .height(110.dp),

            contentScale = ContentScale.Fit
        )
    }
}