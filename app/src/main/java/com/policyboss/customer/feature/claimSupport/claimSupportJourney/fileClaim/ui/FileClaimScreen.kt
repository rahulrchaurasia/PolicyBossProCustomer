package com.policyboss.customer.feature.claimSupport.claimSupportJourney.fileClaim.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.policyboss.customer.feature.policyVault.model.policyVaultModel.AddPolicyType

@Composable
fun FileClaimScreen(
    productType: AddPolicyType,
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit,
    onContinueClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // 1. Dynamic UI state based on Enum
    val headerText = when (productType) {
        AddPolicyType.HEALTH -> "File a Health Claim"
        AddPolicyType.CAR -> "File a Car Claim"
        AddPolicyType.BIKE -> "File a Bike Claim"
        AddPolicyType.CV -> "File a Commercial Vehicle Claim"
        AddPolicyType.LIFE -> "File a Life Claim"
        AddPolicyType.TRAVEL -> "File a Travel Claim"
        AddPolicyType.SMELINE -> "File an SME Claim"

    }

    // Root Container
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // ==========================================
        // TOP: Sticky Header
        // ==========================================
        ClaimTopBar(
            title = headerText,
            onBackClick = onBackClick,
            onCloseClick = onCloseClick
        )

        // ==========================================
        // MIDDLE: Scrollable Content area
        // ==========================================
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // ✅ This pushes the footer to the bottom and allows this area to scroll
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            
            Text(
                text = "Documents Required",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            
            Spacer(modifier = Modifier.height(16.dp))

            // Dynamic Checklist based on Product Type
            when (productType) {
                AddPolicyType.HEALTH -> {
                    ChecklistItem("E-health card or Policy Copy")
                    ChecklistItem("Hospital Discharge Summary")
                    ChecklistItem("Final Hospital Bill with breakdown")
                }
                AddPolicyType.CAR, AddPolicyType.BIKE, AddPolicyType.CV -> {
                    ChecklistItem("Valid Driving License")
                    ChecklistItem("Vehicle RC Copy")
                    ChecklistItem("FIR (if applicable)")
                }
                else -> {
                    ChecklistItem("Original Policy Document")
                    ChecklistItem("Claim Form duly filled and signed")
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Action Button
            Button(
                onClick = onContinueClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0056D2) // Replace with your theme color
                )
            ) {
                Text(
                    text = "Continue",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            
            // Add some padding at the bottom of the scroll list
            Spacer(modifier = Modifier.height(20.dp))
        }

        // ==========================================
        // BOTTOM: Sticky Footer
        // ==========================================
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            HorizontalDivider(color = Color(0xFFE0E0E0), thickness = 1.dp)
            
            NeedHelpRMFooter(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────
// HELPER COMPOSABLES (Replace with your existing project components)
// ─────────────────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClaimTopBar(
    title: String,
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = { 
            Text(
                text = title, 
                fontSize = 18.sp, 
                fontWeight = FontWeight.SemiBold 
            ) 
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        actions = {
            IconButton(onClick = onCloseClick) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "Close Flow")
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.White
        )
    )
}

@Composable
fun ChecklistItem(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Bullet point
        Box(
            modifier = Modifier
                .size(6.dp)
                .background(Color.Gray, shape = RoundedCornerShape(50))
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = text,
            fontSize = 14.sp,
            color = Color.DarkGray
        )
    }
}

@Composable
fun NeedHelpRMFooter(modifier: Modifier = Modifier) {
    // Replace with your actual RM Footer design
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Need help? Connect with your RM",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF0056D2)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "Health Claim Preview")
@Composable
fun FileClaimScreenHealthPreview() {
    MaterialTheme {
        FileClaimScreen(
            productType = AddPolicyType.HEALTH,
            onBackClick = { /* Do nothing in preview */ },
            onCloseClick = { /* Do nothing in preview */ },
            onContinueClick = { /* Do nothing in preview */ }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "Car Claim Preview")
@Composable
fun FileClaimScreenCarPreview() {
    MaterialTheme {
        FileClaimScreen(
            productType = AddPolicyType.CAR,
            onBackClick = { /* Do nothing in preview */ },
            onCloseClick = { /* Do nothing in preview */ },
            onContinueClick = { /* Do nothing in preview */ }
        )
    }
}