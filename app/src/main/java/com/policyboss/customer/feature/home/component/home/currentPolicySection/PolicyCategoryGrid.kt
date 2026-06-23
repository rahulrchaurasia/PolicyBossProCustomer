package com.policyboss.customer.feature.home.component.home.currentPolicySection




import android.annotation.SuppressLint
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width


import androidx.compose.ui.unit.dp
import com.policyboss.customer.feature.home.model.policy.CuratedPolicy


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.policyboss.customer.R

// Required imports for Preview
import androidx.compose.ui.tooling.preview.Preview
import com.policyboss.customer.ui.theme.AppColors
import com.policyboss.customer.ui.theme.bodyMediumSemiBold

private const val POLICY_COLUMNS = 3

// Removed @SuppressLint and @OptIn because we don't need BoxWithConstraints or FlowRow anymore!
@Composable
fun PolicyCategoryGrid(
    modifier: Modifier = Modifier,
    policies: List<CuratedPolicy>, // Make sure this matches your actual data class name
    onPolicyClick: (String) -> Unit
) {
    val horizontalSpacing = 12.dp
    val verticalSpacing = 16.dp

    // The main container holding all the rows vertically
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(verticalSpacing)
    ) {
        // Break the single list down into smaller lists of 3 items each
        policies.chunked(POLICY_COLUMNS).forEach { rowPolicies ->

            // Render each chunk as a horizontal Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(horizontalSpacing)
            ) {
                // 1. Draw the actual cards for this specific row
                rowPolicies.forEach { policy ->
                    PolicyCategoryCard(
                        modifier = Modifier.weight(1f), // Forces equal width for all items
                        title = policy.title,
                        iconRes = policy.iconRes,
                        backgroundColor = policy.backgroundColor,
                        isComingSoon = policy.isComingSoon,
                        onClick = {
                            onPolicyClick(policy.id)
                        }
                    )
                }

                // 2. THE FIX: If the row isn't full (like the last row having only 1 item),
                // draw invisible Spacers with weight(1f) to fill out the remaining space.
                // This prevents that final item from stretching across the whole screen!
                val emptySpots = POLICY_COLUMNS - rowPolicies.size
                repeat(emptySpots) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}