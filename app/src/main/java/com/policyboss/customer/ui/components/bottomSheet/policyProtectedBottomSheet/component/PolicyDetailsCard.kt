package com.policyboss.customer.ui.components.bottomSheet.policyProtectedBottomSheet.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.policyboss.customer.R

@Composable
fun PolicyDetailsCard() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(Color.White)
            .padding(
                horizontal = 16.dp,
                vertical = 14.dp
            ),

        verticalAlignment = Alignment.CenterVertically
    ) {

        LogoSection()

        Spacer(
            modifier = Modifier.width(16.dp)
        )

        VerticalDivider(
            modifier = Modifier.height(40.dp),
            color = Color(0xFFE5E7EB)
        )

        DataColumn(
            title = "IDV",
            value = "₹5L",
            modifier = Modifier.weight(1f)
        )

        VerticalDivider(
            modifier = Modifier.height(40.dp),
            color = Color(0xFFE5E7EB)
        )

        DataColumn(
            title = "Premium",
            value = "₹6,403",
            modifier = Modifier.weight(1f)
        )

        VerticalDivider(
            modifier = Modifier.height(40.dp),
            color = Color(0xFFE5E7EB)
        )

        DataColumn(
            title = "Expiry",
            value = "21.02.26",
            modifier = Modifier.weight(1f)
        )
    }
}


@Composable
private fun LogoSection() {

    Box(

        modifier = Modifier

            .width(100.dp)

            .height(58.dp)

            .clip(
                RoundedCornerShape(12.dp)
            )

            .background(
                Color(0xFFF2F3F5)
            ),

        contentAlignment = Alignment.Center
    ) {

        Image(
            painter = painterResource(
                R.drawable.img_tata
            ),

            contentDescription = null,

            modifier = Modifier

                .fillMaxWidth(.7f)

                .height(38.dp),

            contentScale = ContentScale.Fit
        )
    }
}


@Composable
private fun DataColumn(
    title: String,

    value: String,

    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier,

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = value,

            fontSize = 14.sp,

            fontWeight = FontWeight.Bold,

            color = Color(0xFF111827)
        )

        Spacer(
            modifier = Modifier.height(2.dp)
        )

        Text(
            text = title,

            fontSize = 12.sp,

            color = Color(0xFF6B7280)
        )
    }
}