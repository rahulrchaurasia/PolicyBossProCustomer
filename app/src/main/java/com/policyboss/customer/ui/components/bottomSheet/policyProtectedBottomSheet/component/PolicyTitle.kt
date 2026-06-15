package com.policyboss.customer.ui.components.bottomSheet.policyProtectedBottomSheet.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.policyboss.customer.R


import androidx.compose.material3.*

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@Composable
public fun PolicyTitle() {

    Text(
        text =
        "Your 'Honda Amaze' is now protected! Access your policy by syncing your email",

        modifier = Modifier
            .fillMaxWidth(.95f),

        textAlign = TextAlign.Center,

        color = Color.White,

        style = MaterialTheme.typography.titleMedium.copy(

            fontWeight = FontWeight.Bold,

            fontSize = 18.sp,

            lineHeight = 28.sp
        )
    )
}