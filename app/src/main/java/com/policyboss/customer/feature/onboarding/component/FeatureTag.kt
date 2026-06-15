package com.policyboss.customer.feature.onboarding.component// ============================================
// REUSABLE UI COMPONENTS
// ============================================



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Text

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage



import androidx.compose.runtime.Composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape


import androidx.compose.material3.Text


import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



import coil.compose.AsyncImage

import com.policyboss.customer.R


//@Composable
//fun FeatureTag(text: String, icon: Int) {
//    Row(
//        modifier = Modifier
//            .clip(RoundedCornerShape(26.dp))
//            .background(PrimaryBlue)
//            .padding(horizontal = 14.dp, vertical = 8.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Image(
//            painter = painterResource(id = icon),
//            contentDescription = null,
//            modifier = Modifier.size(24.dp)
//        )
//        Spacer(Modifier.width(6.dp))
//        Text(text = text, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Medium)
//    }
//}

@Composable
fun FeatureTag(text: String) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(26.dp))
            .background(Color(0xFF2E90FA))
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // The Gold Star circle icon common in all pills
        Box(
            modifier = Modifier
                .size(22.dp)
                .clip(CircleShape)
                .background(Color(0xFFFFB300)), // Gold/Amber
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = R.drawable.star_circle,
                contentDescription = null,
                modifier = Modifier.size(12.dp)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}