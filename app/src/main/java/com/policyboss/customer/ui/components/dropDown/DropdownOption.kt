package com.policyboss.customer.ui.components.dropDown

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class DropdownOption<T>(
    val value: T,
    val label: String,
    val iconRes: Int? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> GenericM3Dropdown(
    label: String,
    items: List<DropdownOption<T>>,
    selectedItem: DropdownOption<T>?,
    onItemSelected: (DropdownOption<T>) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it }
        ) {
            OutlinedTextField(
                value = selectedItem?.label.orEmpty(),
                onValueChange = {},
                readOnly = true,
                leadingIcon = selectedItem?.iconRes?.let {
                    {
                        Image(
                            painter = painterResource(id = it),
                            contentDescription = null,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                shape = RoundedCornerShape(12.dp),
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                modifier = Modifier
                    .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                items.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option.label) },
                        leadingIcon = option.iconRes?.let {
                            {
                                Image(
                                    painter = painterResource(id = it),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        },
                        onClick = {
                            onItemSelected(option)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun GenericM3DropdownPreview() {
    MaterialTheme {
        // 1. Create mock data for the preview
        val sampleOptions = listOf(
            DropdownOption("acko", "Acko General Insurance"),
            DropdownOption("hdfc", "HDFC Ergo"),
            DropdownOption("icici", "ICICI Lombard")
        )

        // 2. Mock state
        var selectedOption by remember { mutableStateOf<DropdownOption<String>?>(sampleOptions.first()) }

        // 3. Render component
        GenericM3Dropdown(
            label = "Insurer",
            items = sampleOptions,
            selectedItem = selectedOption,
            onItemSelected = { selectedOption = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}