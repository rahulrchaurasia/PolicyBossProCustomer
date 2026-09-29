package com.policyboss.customer.ui.components.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType

/**
 * Reusable PAN input field.
 *
 * PAN format:
 *
 *      ABCDE1234F
 *
 * 5 letters + 4 digits + 1 letter
 */
@Composable
fun PanTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Enter your PAN",
    isError: Boolean = false,
    errorMessage: String? = null,
    imeAction: ImeAction = ImeAction.Done,
    onDone: (() -> Unit)? = null
) {

    /*
     * Decide which keyboard should be shown
     * based on the next PAN character.
     */
    val keyboardType = when {
        value.length < 5 -> KeyboardType.Ascii
        value.length < 9 -> KeyboardType.Number
        else -> KeyboardType.Ascii
    }

    /*
     * PAN should normally be uppercase.
     */
    val capitalization = when {
        value.length < 5 -> KeyboardCapitalization.Characters
        value.length == 9 -> KeyboardCapitalization.Characters
        else -> KeyboardCapitalization.None
    }

    AppOutlinedTextField(
        value = value,

        onValueChange = { input ->

            /*
             * Normalize the user's input.
             */
            val normalized = input
                .uppercase()
                .filter { it.isLetterOrDigit() }

            /*
             * Build a valid PAN character by character.
             */
            val filtered = buildString {

                normalized.forEach { char ->

                    when (length) {

                        /*
                         * Position 0-4
                         * Only letters allowed.
                         */
                        in 0..4 -> {
                            if (char.isLetter()) {
                                append(char)
                            }
                        }

                        /*
                         * Position 5-8
                         * Only digits allowed.
                         */
                        in 5..8 -> {
                            if (char.isDigit()) {
                                append(char)
                            }
                        }

                        /*
                         * Position 9
                         * Only letter allowed.
                         */
                        9 -> {
                            if (char.isLetter()) {
                                append(char)
                            }
                        }
                    }

                    /*
                     * PAN has maximum 10 characters.
                     */
                    if (length == 10) {
                        return@buildString
                    }
                }
            }

            onValueChange(filtered)
        },

        modifier = modifier.fillMaxWidth(),

        placeholder = placeholder,

        isError = isError,

        errorMessage = errorMessage,

        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            capitalization = capitalization,
            imeAction = imeAction
        ),

        keyboardActions = KeyboardActions(
            onDone = {
                onDone?.invoke()
            }
        ),

        singleLine = true
    )
}