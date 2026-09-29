package com.policyboss.customer.ui.components.textfield


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.policyboss.customer.ui.theme.AppColors
import com.policyboss.customer.ui.theme.border
import com.policyboss.customer.ui.theme.placeholder
import com.policyboss.customer.ui.theme.textPrimary
import com.policyboss.customer.ui.theme.textSecondary


@Composable
fun AppOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    leadingContent: (@Composable () -> Unit)? = null,
    trailingContent: (@Composable () -> Unit)? = null, // ⭐ ADDED: Trailing Icon Support

    // ⭐ ADDED: Controls the background color, defaults to transparent
    containerColor: Color = Color.Transparent,

    keyboardType: KeyboardType = KeyboardType.Text,

    // ⭐ ADDED: ImeAction.Next prevents accidental new lines when singleLine = true
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = keyboardType,
        imeAction = ImeAction.Next
    ),
    keyboardActions: KeyboardActions = KeyboardActions.Default,

    singleLine: Boolean = true,
    minLines: Int = 1, // ⭐ ADDED: Allows the field to start larger for multi-line inputs

    maxLines: Int = Int.MAX_VALUE, // ⭐ ADDED: Defaults to infinity so it doesn't break existing fields

    // =========================================
    // VALIDATION
    // =========================================

    //  ADDED: Expose the readOnly parameter
    readOnly: Boolean = false,
    isError: Boolean = false,
    errorMessage: String? = null,
    enabled: Boolean = true
) {

    Column(
        modifier = modifier.fillMaxWidth()
    ) {

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 64.dp), // ⭐ CHANGED: .height(64.dp) to defaultMinSize so it can grow
            enabled = enabled,
            readOnly = readOnly,
            singleLine = singleLine,
            minLines = minLines, // ⭐ APPLIED
            maxLines = maxLines,
            isError = isError,

//            textStyle = TextStyle(
//                fontSize = 18.sp,
//                fontWeight = FontWeight.Medium,
//                color = MaterialTheme.colorScheme.textPrimary // RESTORED YOUR CUSTOM COLOR
//            ),
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                color = AppColors.TextPrimary
            ),





            placeholder = {
                Text(
                    text = placeholder,
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.placeholder // RESTORED YOUR CUSTOM COLOR
                    )
                )
            },

            leadingIcon = leadingContent,
            trailingIcon = trailingContent, // ⭐ APPLIED

            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,

            shape = RoundedCornerShape(16.dp),

            // ⭐ RESTORED YOUR ENTIRE ORIGINAL COLORS BLOCK
            colors = OutlinedTextFieldDefaults.colors(
                // =====================================
                // BORDER
                // =====================================
                focusedBorderColor = if (isError) {
                    MaterialTheme.colorScheme.error
                } else {
                    MaterialTheme.colorScheme.border
                },
                unfocusedBorderColor = if (isError) {
                    MaterialTheme.colorScheme.error
                } else {
                    MaterialTheme.colorScheme.border
                },
                errorBorderColor = MaterialTheme.colorScheme.error,

                // =====================================
                // CONTAINER
                // =====================================
//                focusedContainerColor = MaterialTheme.colorScheme.surface,
//                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
//                disabledContainerColor = MaterialTheme.colorScheme.surface,

                focusedContainerColor = containerColor,
                unfocusedContainerColor = containerColor,
                disabledContainerColor = containerColor,

                // =====================================
                // CURSOR
                // =====================================
                cursorColor = if (isError) {
                    MaterialTheme.colorScheme.error
                } else {
                    MaterialTheme.colorScheme.primary
                },
                errorCursorColor = MaterialTheme.colorScheme.error,

                // =====================================
                // TEXT
                // =====================================
                focusedTextColor = MaterialTheme.colorScheme.textPrimary,
                unfocusedTextColor = MaterialTheme.colorScheme.textPrimary,
                disabledTextColor = MaterialTheme.colorScheme.textSecondary,

                // =====================================
                // PLACEHOLDER
                // =====================================
                focusedPlaceholderColor = MaterialTheme.colorScheme.placeholder,
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.placeholder,
                disabledPlaceholderColor = MaterialTheme.colorScheme.placeholder
            )
        )

        // =========================================
        // ERROR MESSAGE
        // =========================================
        AnimatedVisibility(
            visible = isError && !errorMessage.isNullOrBlank()
        ) {
            Text(
                text = errorMessage.orEmpty(),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(
                    start = 16.dp,
                    top = 6.dp
                )
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────
// PREVIEWS
// ─────────────────────────────────────────────────────────────────────────

@Preview(showBackground = true)
@Composable
private fun AppOutlinedTextFieldPreview() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .background(Color.White)
        ) {
            AppOutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = "Enter your mobile number"
            )
            Spacer(modifier = Modifier.height(16.dp))
            AppOutlinedTextField(
                value = "9876543210",
                onValueChange = {},
                placeholder = "Enter your mobile number"
            )
        }
    }
}




@Preview(showBackground = true)
@Composable
private fun AppOutlinedTextFieldWithPrefixPreview() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            AppOutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = "Enter your mobile number",
                keyboardType = KeyboardType.Number,
                leadingContent = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        // ⭐ THIS IS WHAT FIXED IT! ⭐
                        modifier = Modifier.padding(start = 16.dp)
                    ) {
                        Text(
                            text = "+91",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF101828) // Your specific color
                        )
                        Spacer(modifier = Modifier.width(12.dp))

                        // Using your Box divider (or VerticalDivider works too!)
                        Box(
                            modifier = Modifier
                                .height(24.dp)
                                .width(1.dp)
                                .background(Color(0xFFD0D5DD))
                        )

                        Spacer(modifier = Modifier.width(12.dp))
                    }
                }
            )
        }
    }
}