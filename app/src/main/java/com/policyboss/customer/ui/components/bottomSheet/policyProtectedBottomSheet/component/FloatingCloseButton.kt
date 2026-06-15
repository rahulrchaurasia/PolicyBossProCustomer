package com.policyboss.customer.ui.components.bottomSheet.policyProtectedBottomSheet.component

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
 fun FloatingCloseButton(
    onDismiss: () -> Unit,

    modifier: Modifier = Modifier
) {

    Box(

        modifier = modifier

            .size(48.dp)

            .clip(
                CircleShape
            )

            .background(
                Color.White
            )

            .clickable {
                onDismiss()
            },

        contentAlignment = Alignment.Center
    ) {

        Icon(

            imageVector = Icons.Default.Close,

            contentDescription = "Close",

            tint = Color.Black
        )
    }
}