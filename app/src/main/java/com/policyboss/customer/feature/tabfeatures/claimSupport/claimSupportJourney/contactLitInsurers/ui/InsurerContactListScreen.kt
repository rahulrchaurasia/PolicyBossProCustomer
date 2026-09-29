package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.contactLitInsurers.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ViewList
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.contactLitInsurers.model.InsurerContactModel
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.contactLitInsurers.state.InsurerContactListAction
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.contactLitInsurers.state.InsurerContactListUiState
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.contactLitInsurers.ui.componet.InsurerCard
import com.policyboss.customer.ui.theme.AppColors
import com.policyboss.customer.ui.theme.gradients.AppGradients
import com.policyboss.customer.ui.theme.titleLargeBold

// Make sure to import your gradient

@Composable
fun InsurerContactListScreen(
    productName: String,
    uiState: InsurerContactListUiState,
    onAction: (InsurerContactListAction) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(AppGradients.ScreenSurfaceGradient) // Uses your custom gradient
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(48.dp)) // Status bar spacing

            // --- Custom Back Button (Matching Screenshot) ---
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .border(1.dp, Color.Gray.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.Transparent)
                    .clickable { onBackClick() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = AppColors.TextPrimary
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // --- Title & View Toggle Row ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "List of $productName Insurers",
                    style = MaterialTheme.typography.titleLargeBold,
                    color = AppColors.TextPrimary
                )

                // Toggle Icon (Grid vs List)
                IconButton(onClick = { onAction(InsurerContactListAction.OnToggleViewMode) }) {
                    Icon(
                        imageVector = if (uiState.isGridView) Icons.AutoMirrored.Filled.ViewList else  Icons.Default.GridView,
                        contentDescription = "Toggle View",
                        tint = AppColors.TextSecondary
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // --- Search Bar ---
            OutlinedTextField(
                value = uiState.searchQuery,
                onValueChange = { onAction(InsurerContactListAction.OnSearchQueryChanged(it)) },
                placeholder = { Text("Search insurers...", color = Color.Gray) },
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.Gray)
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                singleLine = true, // 🚀 FIX: Keeps search bar to exactly one line
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    // 🚀 FIX: Added visible borders for better UI definition
                    unfocusedBorderColor = Color.LightGray.copy(alpha = 0.6f),
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    cursorColor = MaterialTheme.colorScheme.primary
                ),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search)
            )

            Spacer(modifier = Modifier.height(24.dp))




            // --- Dynamic Grid / List Content ---
            LazyVerticalGrid(
                columns = GridCells.Fixed(if (uiState.isGridView) 2 else 1),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 16.dp)
            ) {
                items(
                    items = uiState.insurers,
                    key = { it.id } // 'it' works here because the parameter isn't explicitly named yet
                ) { insurer -> // You named the item 'insurer' here
                    InsurerCard(
                        insurer = insurer,
                        isGridView = uiState.isGridView,
                        // FIX: Use 'insurer.id' instead of 'it.id'
                        onClick = { onAction(InsurerContactListAction.OnInsurerClicked(insurer.id)) }
                    )
                }
            }

        }
    }
}




//Dummy data for previews
private val previewInsurers = listOf(
    InsurerContactModel("1", "Acko General Insurance", android.R.drawable.ic_menu_gallery),
    InsurerContactModel("2", "Tata AIG General Insurance", android.R.drawable.ic_menu_gallery),
    InsurerContactModel("3", "HDFC ERGO", android.R.drawable.ic_menu_gallery),
    InsurerContactModel("4", "ICICI Lombard", android.R.drawable.ic_menu_gallery)
)

@Preview(showBackground = true, name = "1. Grid View (Default)")
@Composable
private fun InsurerContactListScreenGridPreview() {
    MaterialTheme {
        InsurerContactListScreen(
            productName = "Car",
            uiState = InsurerContactListUiState(
                searchQuery = "",
                insurers = previewInsurers,
                isGridView = true // 2-column grid
            ),
            onAction = {},
            onBackClick = {}
        )
    }
}

@Preview(showBackground = true, name = "2. List View")
@Composable
private fun InsurerContactListScreenListPreview() {
    MaterialTheme {
        InsurerContactListScreen(
            productName = "Car",
            uiState = InsurerContactListUiState(
                searchQuery = "",
                insurers = previewInsurers,
                isGridView = false // 1-column list
            ),
            onAction = {},
            onBackClick = {}
        )
    }
}