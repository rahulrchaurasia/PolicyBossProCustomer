package com.policyboss.customer.ui.components.loading



import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AppLoadingOverlay(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    message: String = "Please wait...",
    showBackground: Boolean = true,
    backgroundColor: Color = Color.Black.copy(alpha = 0.35f),
    content: @Composable () -> Unit
) {

    Box(
        modifier = modifier.fillMaxSize()
    ) {

        // =========================================
        // SCREEN CONTENT
        // =========================================

        content()

        // =========================================
        // LOADING OVERLAY
        // =========================================

        AnimatedVisibility(
            visible = isLoading,
            enter = fadeIn(),
            exit = fadeOut()
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        if (showBackground)
                            backgroundColor
                        else
                            Color.Transparent
                    )

                    // Consume all touch events
                    .clickable(
                        enabled = true,
                        indication = null,
                        interactionSource = remember {
                            MutableInteractionSource()
                        }
                    ) {},

                contentAlignment = Alignment.Center
            ) {

                Card(
                    shape = RoundedCornerShape(20.dp),

                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 8.dp
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(
                            horizontal = 28.dp,
                            vertical = 24.dp
                        ),

                        horizontalAlignment = Alignment.CenterHorizontally,

                        verticalArrangement = Arrangement.Center
                    ) {

                        CircularProgressIndicator(
                            strokeWidth = 3.dp
                        )

                        Spacer(
                            modifier = Modifier.height(18.dp)
                        )

                        Text(
                            text = message,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun AppLoadingOverlayPreview() {

    MaterialTheme {

        AppLoadingOverlay(

            isLoading = true,

            message = "Creating account..."
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),

                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "Create Account Screen",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
    }
}