package com.policyboss.customer.ui.components.otpVerify



import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.policyboss.customer.R
import com.policyboss.customer.feature.login.component.LoadingSpinner
import com.policyboss.customer.feature.login.component.verifyAccount.OTPFieldState
import com.policyboss.customer.feature.login.component.verifyAccount.OTPStatusMessage
import com.policyboss.customer.feature.login.component.verifyAccount.OTPTextField
import com.policyboss.customer.ui.theme.AppColors

@Composable
fun OTPVerifyAccountContent(

    @DrawableRes headerIcon: Int,       // Pass R.drawable.ic_login or R.drawable.ic_email
    title: String,                      // "Verify your number" vs "Verify your email"
    subtitle: String,                   // "We’ve sent the code to your..."
    targetValue: String,                // The mobile number or email address
    mobileNumber: String,
    otpText: String,
    otpLength: Int = 4,
    fieldState: OTPFieldState,
    errorMessage: String?,
    resendMessage: String?,
    resendSeconds: Int,
    isVerifyingOtp: Boolean,
    isResendingOtp: Boolean,
    onOtpChange: (String, Boolean) -> Unit,
    onResendClick: () -> Unit,
    modifier : Modifier
) {
    var hasUserInteracted by remember { mutableStateOf(false) }

    val formattedTime = remember(resendSeconds) {
        val minute = resendSeconds / 60
        val second = resendSeconds % 60
        "$minute:${second.toString().padStart(2, '0')}"
    }

    val scrollState = rememberScrollState()

    // =====================================
    // ROOT LAYER (Contains both scrollable content and pinned footer)
    // =====================================
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(AppColors.Background)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {

        // =====================================
        // LAYER 1: SCROLLABLE MAIN CONTENT
        // =====================================
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .imePadding() // Pushes the scrollable content up for the keyboard
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(56.dp))

            // =====================================
            // TOP IMAGE
            // =====================================
            Image(
                painter = painterResource(id = R.drawable.ic_login),
                contentDescription = null,
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(24.dp))

            // =====================================
            // TITLE
            // =====================================
            Text(
                text = "Verify your number",
                style = MaterialTheme.typography.titleLarge,
                color = AppColors.TextPrimary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "We’ve sent the code to your mobile number",
                style = MaterialTheme.typography.bodyLarge,
                color = AppColors.TextSecondary,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            // =====================================
            // MOBILE NUMBER
            // =====================================
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = mobileNumber,
                    style = MaterialTheme.typography.titleSmall,
                    color = AppColors.TextPrimary
                )
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = null,
                    tint = AppColors.PrimaryBlue,
                    modifier = Modifier.size(18.dp)
                )
            }

            Spacer(modifier = Modifier.height(34.dp))

            // =====================================
            // OTP FIELD
            // =====================================
            OTPTextField(
                otpText = otpText,
                otpLength = otpLength,
                fieldState = fieldState,
                isLoading = isVerifyingOtp,
                onOtpTextChange = { value, isComplete ->
                    hasUserInteracted = true
                    onOtpChange(value, isComplete)
                }
            )

            // =====================================
            // STATUS MESSAGE
            // =====================================
            when {
                fieldState == OTPFieldState.ERROR && errorMessage != null && hasUserInteracted -> {
                    Spacer(modifier = Modifier.height(14.dp))
                    OTPStatusMessage(
                        icon = R.drawable.ic_checkbox,
                        message = errorMessage,
                        color = AppColors.ErrorRed
                    )
                }
                resendMessage != null -> {
                    Spacer(modifier = Modifier.height(14.dp))
                    OTPStatusMessage(
                        icon = R.drawable.ic_check_circle,
                        message = resendMessage,
                        color = AppColors.SuccessGreen
                    )
                }
                fieldState == OTPFieldState.SUCCESS -> {
                    Spacer(modifier = Modifier.height(14.dp))
                    OTPStatusMessage(
                        icon = R.drawable.ic_check_circle,
                        message = "OTP verified successfully",
                        color = AppColors.SuccessGreen
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // =====================================
            // RESEND SECTION
            // =====================================
            if (resendSeconds > 0) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    LoadingSpinner(modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Code will resend in ",
                        style = MaterialTheme.typography.titleSmall,
                        color = AppColors.TextSecondaryDark
                    )
                    Text(
                        text = formattedTime,
                        style = MaterialTheme.typography.titleSmall,
                        color = AppColors.PrimaryBlue,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            } else {
                TextButton(
                    onClick = onResendClick,
                    enabled = !isResendingOtp
                ) {
                    if (isResendingOtp) {
                        LoadingSpinner(modifier = Modifier.size(18.dp))
                    } else {
                        Text(
                            text = "Resend Code",
                            style = MaterialTheme.typography.titleSmall,
                            color = AppColors.PrimaryBlue
                        )
                    }
                }
            }

            // Ensures the scrollable content doesn't get permanently stuck behind the security footer
            Spacer(modifier = Modifier.height(80.dp))

        } // <-- END OF SCROLLABLE COLUMN

        // =====================================
        // LAYER 2: SECURITY TEXT (Pinned to Bottom)
        // =====================================
        // This is a direct child of the Box, so .align(Alignment.BottomCenter) works perfectly.
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(bottom = 18.dp)
        )
        {
            Image(
                painter = painterResource(id = R.drawable.ic_security),
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "No harm to privacy, no spams, trusted security",
                style = MaterialTheme.typography.bodyMedium,
                color = AppColors.TextSecondary
            )
        }

    } // <-- END OF ROOT BOX
}



