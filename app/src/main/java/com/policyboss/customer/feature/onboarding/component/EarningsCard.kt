package com.policyboss.customer.feature.onboarding.component

import androidx.compose.runtime.Composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Text

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.policyboss.customer.R

//@Composable
//fun EarningsCard() {
//    Column(
//        modifier = Modifier
//            .border(1.dp, Color(0xFFEAECF0), RoundedCornerShape(12.dp))
//            .background(Color(0xFFFEFDF0), RoundedCornerShape(12.dp))
//            .padding(16.dp)
//    ) {
//        // Yellow Header
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.Center,
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(Color(0xFFFFE493), CircleShape)
//                .padding(vertical = 6.dp)
//        ) {
//            Text("Earn back ₹2500", color = Color(0xFFCA8403), fontSize = 12.sp, fontWeight = FontWeight.Bold)
//        }
//
//        Spacer(Modifier.height(16.dp))
//
//        // UI Skeletons (Fake Text Lines)
//        Row {
//            Box(modifier = Modifier.padding(end = 8.dp).width(40.dp).height(8.dp).background(Color(0xFFFFF5D8), CircleShape))
//            Box(modifier = Modifier.width(60.dp).height(8.dp).background(Color(0xFFFFF5D8), CircleShape))
//        }
//        Spacer(Modifier.height(8.dp))
//        Box(modifier = Modifier.fillMaxWidth().height(24.dp).background(Color(0xFFFFF5D8), RoundedCornerShape(6.dp)))
//    }
//}


@Composable
fun EarningsCard() {
    Column(
        modifier = Modifier
            .width(220.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFFEFDF0))
            .border(1.dp, Color(0xFFEAECF0).copy(0.5f), RoundedCornerShape(16.dp))
            .padding(12.dp)
    ) {
        // Yellow Header with Right Arrow
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(CircleShape)
                .background(Color(0xFFFFE493))
                .padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Earn back ₹2500",
                color = Color(0xFFCA8403),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
            // The white circle arrow shown in the image
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = R.drawable.arrow_right_color,
                    contentDescription = null,
                    modifier = Modifier.size(12.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Skeleton UI
        Row {
            Box(Modifier.width(40.dp).height(8.dp).background(Color(0xFFFFF5D8), CircleShape))
            Spacer(Modifier.width(8.dp))
            Box(Modifier.width(60.dp).height(8.dp).background(Color(0xFFFFF5D8), CircleShape))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            Modifier
                .fillMaxWidth()
                .height(24.dp)
                .background(Color(0xFFFFF5D8), RoundedCornerShape(6.dp))
        )
    }
}
@Preview(
    name = "Earnings Card Preview",
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
fun PreviewEarningsCard() {
    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            EarningsCard()
        }
    }
}