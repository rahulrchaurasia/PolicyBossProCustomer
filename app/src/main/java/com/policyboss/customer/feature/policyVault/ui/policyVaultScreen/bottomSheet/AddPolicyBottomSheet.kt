package com.policyboss.customer.feature.policyVault.ui.policyVaultScreen.bottomSheet

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.policyboss.customer.R
import com.policyboss.customer.feature.policyVault.model.policyVaultModel.AddPolicyItem
import com.policyboss.customer.feature.policyVault.model.policyVaultModel.AddPolicyType
import com.policyboss.customer.ui.theme.AppColors
import com.policyboss.customer.ui.theme.headlineMediumBold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPolicyBottomSheet(

    title: String = "Which policy do you want to add?",
    onDismissRequest: () -> Unit,
    onOptionSelected: (AddPolicyType) -> Unit
) {


    // 1. Define the data here so the UI knows what to render
    val policyOptions = listOf(
        AddPolicyItem(
            AddPolicyType.CAR,
            "Car",

            R.drawable.ic_car,

            Color(0xFFedf6fd),

            Color.Unspecified
        ),
        AddPolicyItem(
            AddPolicyType.BIKE,
            "Bike",
            R.drawable.ic_bike,
            Color(0xFFedf6fd),
            Color.Unspecified
        ),
        AddPolicyItem(
            AddPolicyType.CV,
            "CV",
            R.drawable.ic_cv,
            Color(0xFFedf6fd),
            Color.Unspecified
        ),
        AddPolicyItem(
            AddPolicyType.HEALTH,
            "Health",
            R.drawable.ic_health,
            Color(0xFFf0fdf9),
            Color.Unspecified
        ),
        AddPolicyItem(
            AddPolicyType.LIFE,
            "Life",
            R.drawable.ic_life,
            Color(0xFFfef3f2),
            Color.Unspecified
        ),
        AddPolicyItem(
            AddPolicyType.TRAVEL,
            "Travel",
            R.drawable.ic_travel,
            Color(0xFFedf3fe),
            Color.Unspecified
        ),
        AddPolicyItem(
            AddPolicyType.SMELINE,
            "SMELINE",
            R.drawable.ic_smeline,
            Color(0xFFfffaeb),
            Color.Unspecified
        )
    )

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        containerColor = Color.Transparent, // Allows the floating 'X'
        // Add this line to make the background noticeably darker
        scrimColor = Color.Black.copy(alpha = 0.6f),
        dragHandle = null
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
               // .padding(bottom = 32.dp)
        ) {
            
            // The White Sheet Area
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 28.dp) // Pushes down to make room for 'X'
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .background(AppColors.Background)
                    .navigationBarsPadding() // for handling bottom bar
                    .padding(top = 40.dp, bottom = 24.dp, start = 24.dp, end = 24.dp),

                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineMediumBold,

                    modifier = Modifier.padding(bottom = 24.dp)
                )

                // 2. Call your Content Grid Component here!
                AddPolicyBottomSheetContent(
                    options = policyOptions,
                    onOptionSelected = onOptionSelected
                )
            }

            // The Floating Close Button
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .align(Alignment.TopCenter)
                    .background(AppColors.Background, CircleShape)
                    .border(1.dp, AppColors.BorderSecondary, CircleShape)
                    .clip(CircleShape)
                    .clickable { onDismissRequest() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_close), // Your close icon
                    contentDescription = "Close",
                    tint = Color.Unspecified
                )
            }
        }
    }
}