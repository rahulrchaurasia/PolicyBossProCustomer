package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.cashlessGarage.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.cashlessGarage.model.Insurer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsurerPickerBottomSheet(
    insurers: List<Insurer>,
    selectedInsurer: Insurer?,
    onInsurerSelected: (Insurer) -> Unit,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Select Insurer",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
            )

            // Add a fallback text to easily debug if the list is empty
            if (insurers.isEmpty()) {
                Text(
                    text = "No Insurers found...",
                    modifier = Modifier.padding(24.dp)
                )
            } else {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f, fill = false) // ✅ ADD THIS LINE: Tells the list it can take up remaining space without expanding indefinitely
                        .navigationBarsPadding() // Ensures list isn't hidden behind system navigation
                ) {
                    items(
                        items = insurers,
                        key = { it.id }
                    ) { insurer ->
                        ListItem(
                            headlineContent = {
                                Text(text = insurer.name)
                            },
                            leadingContent = {
                                Image(
                                    painter = painterResource(id = insurer.logoRes),
                                    contentDescription = insurer.name,
                                    modifier = Modifier.size(32.dp)
                                )
                            },
                            trailingContent = {
                                // Only show the checkmark if this item is selected
                                if (selectedInsurer?.id == insurer.id) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = "Selected",
                                        tint = MaterialTheme.colorScheme.primary // Uses your app's primary blue color
                                    )
                                }
                            },
                            modifier = Modifier.clickable {
                                onInsurerSelected(insurer)
                                onDismiss()
                            }
                        )
                    }
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun InsurerPickerBottomSheetPreview() {
    MaterialTheme {
        // 1. Create mock data using standard Android system icons
        val mockInsurers = listOf(
            Insurer(
                id = "acko",
                name = "Acko General Insurance",
                logoRes = android.R.drawable.ic_menu_camera
            ),
            Insurer(
                id = "tata",
                name = "Tata AIG",
                logoRes = android.R.drawable.ic_menu_gallery
            ),
            Insurer(
                id = "hdfc",
                name = "HDFC Ergo",
                logoRes = android.R.drawable.ic_menu_info_details
            )
        )

        // 2. Create local state to test the radio button selection
        var selectedMockInsurer by remember { mutableStateOf<Insurer?>(mockInsurers.first()) }

        // 3. Render the component
        InsurerPickerBottomSheet(
            insurers = mockInsurers,
            selectedInsurer = selectedMockInsurer,
            onInsurerSelected = { selectedMockInsurer = it },
            onDismiss = { /* Do nothing in preview */ }
        )
    }
}