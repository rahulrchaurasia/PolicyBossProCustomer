package com.policyboss.customer.ui.components.textfield



import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AppClickableTextField(
    value: String,
    placeholder: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    errorMessage: String? = null,
    trailingIcon: (@Composable () -> Unit)? = null
) {
    Box(modifier = modifier.fillMaxWidth()) {
        AppOutlinedTextField(
            value = value,
            onValueChange = {}, // Ignored since we don't type
            placeholder = placeholder,
            isError = isError,
            errorMessage = errorMessage,
            trailingContent = trailingIcon
            // enabled remains true so colors look normal!
        )
        
        // This transparent box sits exactly on top of the TextField and intercepts clicks
        Box(
            modifier = Modifier
                .matchParentSize()
                .clickable { onClick() }
        )
    }
}