package com.policyboss.customer.feature.policyVault.ui.policyVaultScreen.component.card


import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.R
import com.policyboss.customer.ui.theme.bodyMediumSemiBold

// Ensure you import your R file and the models we just created
// import your.package.name.R
// import your.package.name.models.AddPolicyItem
// import your.package.name.models.AddPolicyType

@Composable
fun PolicyOptionCard(
    title: String,
    @DrawableRes iconRes: Int,
    backgroundColor: Color,
    iconTint: Color? = null, // Optional tint if your Home Screen icons are pre-colored
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopCenter
    )
    {
        Column(
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .background(backgroundColor)
                .clickable(onClick = onClick)
                .padding(
                    top = 18.dp,
                    bottom = 14.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Preserve XML gradients
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = title,
                modifier = Modifier.size(40.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.bodyMediumSemiBold,

                )
        }

    }
}
//@Composable
//fun PolicyOptionCard(
//    title: String,
//    @DrawableRes iconRes: Int,
//    backgroundColor: Color,
//    iconTint: Color? = null, // Optional tint if your Home Screen icons are pre-colored
//    modifier: Modifier = Modifier,
//    onClick: () -> Unit
//) {
//    Column(
//        modifier = modifier
//            .fillMaxWidth()
//            .clip(RoundedCornerShape(16.dp)) // Tighter curve for bottom sheet
//            .background(backgroundColor)
//            .clickable(onClick = onClick)
//            .padding(vertical = 16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Icon(
//            painter = painterResource(id = iconRes),
//            contentDescription = title,
//            tint = iconTint ?: Color.Unspecified, // Uses original colors if tint is null
//            modifier = Modifier.size(32.dp) // Slightly smaller than Home Screen's 40.dp
//        )
//
//        Spacer(modifier = Modifier.height(12.dp))
//
//        Text(
//            text = title,
//            // Reusing your Home Screen typography!
//            style = MaterialTheme.typography.bodyMediumSemiBold,
//            color = Color(0xFF111827),
//            textAlign = TextAlign.Center
//        )
//    }
//}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PolicyOptionCardPreview() {
    MaterialTheme {
        // Wrapped in a Box with a fixed width to simulate the 3-column grid constraint
        Box(
            modifier = Modifier
                .padding(16.dp)
                .width(100.dp)
        ) {
            PolicyOptionCard(
                title = "Car",
                iconRes = R.drawable.ic_health, // Replace with your actual icon
                backgroundColor = Color(0xFFF0F5FF),
                iconTint = Color(0xFF3B82F6),
                modifier = Modifier.padding(16.dp),
                onClick = {}
            )
        }
    }
}