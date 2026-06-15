package com.policyboss.customer.ui.components.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.policyboss.customer.ui.theme.border
import com.policyboss.customer.ui.theme.placeholder
import com.policyboss.customer.ui.theme.textPrimary
import com.policyboss.customer.ui.theme.textSecondary


import androidx.compose.material3.VerticalDivider
import androidx.compose.foundation.layout.*

import androidx.compose.material3.Text

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.policyboss.customer.ui.theme.border


import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.material3.OutlinedTextField

import androidx.compose.material3.Text

import androidx.compose.ui.unit.sp
import com.policyboss.customer.ui.theme.border
import com.policyboss.customer.ui.theme.placeholder
import com.policyboss.customer.ui.theme.textPrimary

import com.policyboss.customer.ui.theme.border
import com.policyboss.customer.ui.theme.placeholder
import com.policyboss.customer.ui.theme.textPrimary
import com.policyboss.customer.ui.theme.textSecondary


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.OutlinedTextField

import androidx.compose.material3.Text

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun AppOutlinedTextField(

    value: String,

    onValueChange: (String) -> Unit,

    modifier: Modifier = Modifier,

    placeholder: String = "",

    leadingContent: (@Composable () -> Unit)? = null,

    keyboardType: KeyboardType = KeyboardType.Text,

    singleLine: Boolean = true,

    // =========================================
    // VALIDATION
    // =========================================

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
                .height(64.dp),

            enabled = enabled,

            singleLine = singleLine,

            isError = isError,

            textStyle = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.textPrimary
            ),

            placeholder = {

                Text(
                    text = placeholder,

                    style = TextStyle(
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.placeholder
                    )
                )
            },

            leadingIcon = leadingContent,

            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            ),

            shape = RoundedCornerShape(16.dp),

            colors = OutlinedTextFieldDefaults.colors(

                // =====================================
                // BORDER
                // =====================================

                focusedBorderColor =
                if (isError) {
                    MaterialTheme.colorScheme.error
                } else {
                    MaterialTheme.colorScheme.border
                },

                unfocusedBorderColor =
                if (isError) {
                    MaterialTheme.colorScheme.error
                } else {
                    MaterialTheme.colorScheme.border
                },

                errorBorderColor =
                MaterialTheme.colorScheme.error,

                // =====================================
                // CONTAINER
                // =====================================

                focusedContainerColor =
                MaterialTheme.colorScheme.surface,

                unfocusedContainerColor =
                MaterialTheme.colorScheme.surface,

                disabledContainerColor =
                MaterialTheme.colorScheme.surface,

                // =====================================
                // CURSOR
                // =====================================

                cursorColor =
                if (isError) {
                    MaterialTheme.colorScheme.error
                } else {
                    MaterialTheme.colorScheme.primary
                },

                errorCursorColor =
                MaterialTheme.colorScheme.error,

                // =====================================
                // TEXT
                // =====================================

                focusedTextColor =
                MaterialTheme.colorScheme.textPrimary,

                unfocusedTextColor =
                MaterialTheme.colorScheme.textPrimary,

                disabledTextColor =
                MaterialTheme.colorScheme.textSecondary,

                // =====================================
                // PLACEHOLDER
                // =====================================

                focusedPlaceholderColor =
                MaterialTheme.colorScheme.placeholder,

                unfocusedPlaceholderColor =
                MaterialTheme.colorScheme.placeholder,

                disabledPlaceholderColor =
                MaterialTheme.colorScheme.placeholder
            )
        )

        // =========================================
        // ERROR MESSAGE
        // =========================================

        AnimatedVisibility(
            visible =
            isError &&
                    !errorMessage.isNullOrBlank()
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
//@Composable
//fun AppOutlinedTextField(
//    value: String,
//    onValueChange: (String) -> Unit,
//    modifier: Modifier = Modifier,
//    placeholder: String = "",
//    leadingContent: (@Composable () -> Unit)? = null,
//    keyboardType: KeyboardType = KeyboardType.Text,
//    singleLine: Boolean = true
//) {
//
//    OutlinedTextField(
//        value = value,
//
//        onValueChange = onValueChange,
//
//        modifier = modifier
//            .fillMaxWidth()
//            .height(64.dp),
//
//        singleLine = singleLine,
//
//        textStyle = TextStyle(
//            fontSize = 18.sp,
//            fontWeight = FontWeight.Medium,
//            color = MaterialTheme.colorScheme.textPrimary
//        ),
//
//        placeholder = {
//            Text(
//                text = placeholder,
//
//                style = TextStyle(
//                    fontSize = 16.sp,
//                    color = MaterialTheme.colorScheme.placeholder
//                )
//            )
//        },
//
//        leadingIcon = leadingContent,
//
//        keyboardOptions = KeyboardOptions(
//            keyboardType = keyboardType
//        ),
//
//        shape = RoundedCornerShape(16.dp),
//
//        colors = OutlinedTextFieldDefaults.colors(
//
//            focusedBorderColor =
//            MaterialTheme.colorScheme.border,
//
//            unfocusedBorderColor =
//            MaterialTheme.colorScheme.border,
//
//            focusedContainerColor =
//            MaterialTheme.colorScheme.surface,
//
//            unfocusedContainerColor =
//            MaterialTheme.colorScheme.surface,
//
//            cursorColor =
//            MaterialTheme.colorScheme.primary,
//
//            focusedTextColor =
//            MaterialTheme.colorScheme.textPrimary,
//
//            unfocusedTextColor =
//            MaterialTheme.colorScheme.textPrimary,
//
//            focusedPlaceholderColor =
//            MaterialTheme.colorScheme.placeholder,
//
//            unfocusedPlaceholderColor =
//            MaterialTheme.colorScheme.placeholder
//        )
//    )
//}


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
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = "+91",

                            fontSize = 18.sp,

                            fontWeight = FontWeight.Medium
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        VerticalDivider(
                            modifier = Modifier.height(24.dp),

                            thickness = 1.dp,

                            color = MaterialTheme.colorScheme.border
                        )

                        Spacer(modifier = Modifier.width(12.dp))
                    }
                }
            )
        }
    }
}