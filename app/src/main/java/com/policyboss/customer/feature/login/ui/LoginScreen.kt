package com.policyboss.customer.feature.login.ui

import androidx.compose.foundation.layout.padding

import androidx.compose.material3.OutlinedTextField


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

import androidx.compose.material3.Button

import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.policyboss.customer.R
import com.policyboss.customer.ui.components.button.PrimaryCTAButton
import com.policyboss.customer.ui.components.textfield.AppOutlinedTextField
import com.policyboss.customer.ui.theme.PolicyBossCustomerTheme
import com.policyboss.customer.ui.theme.textPrimary
import com.policyboss.customer.ui.theme.textSecondary

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.material3.HorizontalDivider

import androidx.compose.material3.Text
import androidx.compose.runtime.*

import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint

import androidx.compose.ui.res.painterResource

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.policyboss.customer.ui.components.button.PrimaryCTAButton
import com.policyboss.customer.ui.components.textfield.AppOutlinedTextField
import com.policyboss.customer.ui.theme.PolicyBossCustomerTheme
import com.policyboss.customer.ui.theme.border
import com.policyboss.customer.ui.theme.textPrimary
import com.policyboss.customer.ui.theme.textSecondary



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll

import androidx.compose.material3.*
import androidx.compose.runtime.*

import androidx.compose.ui.draw.clip

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.policyboss.customer.feature.login.component.AuthFloatingIcon
import com.policyboss.customer.feature.login.component.AuthHeaderPattern
import com.policyboss.customer.feature.login.model.login.LoginEvent
import com.policyboss.customer.feature.login.model.login.LoginUiState
import com.policyboss.customer.feature.login.model.verifyAccount.VerifyOtpSource
import com.policyboss.customer.feature.login.viewmodel.LoginViewModel
import com.policyboss.customer.ui.ObserveAsEvents

import com.policyboss.customer.ui.components.button.PrimaryCTAButton
import com.policyboss.customer.ui.components.textfield.AppOutlinedTextField
import com.policyboss.customer.ui.theme.textPrimary
import com.policyboss.customer.ui.theme.textSecondary


/*
com.policyboss.customer
│
├── core/
│   ├── ui/
│   │   ├── theme/
│   │   ├── components/
│   │
│   ├── navigation/
│   │   ├── AppNavGraph.kt
│   │   ├── AppNavigator.kt
│   │   ├── Dest.kt
│   │
│   ├── di/                  // Hilt Modules
│   └── utils/
│
├── features/
│   │
│   ├── onboarding/
│   │   ├── ui/
│   │   │   ├── OnboardingScreen.kt
│   │   │   └── components/
│   │   │
│   │   ├── viewmodel/
│   │   │   └── OnboardingViewModel.kt
│   │
│   ├── auth/
│   │   ├── ui/
│   │   │   ├── LoginScreen.kt
│   │   │   ├── RegisterScreen.kt
│   │   │
│   │   ├── viewmodel/
│   │   │   └── AuthViewModel.kt   ✅ HERE
│   │
│   │   ├── repository/           (optional)
│   │
│   ├── home/
│   │   ├── ui/
│   │   │   └── HomeScreen.kt
│   │   │
│   │   ├── viewmodel/
│   │   │   └── HomeViewModel.kt  ✅ HERE
│
├── data/
│   ├── remote/
│   ├── local/
│   ├── repository/   // implementations
 */


//other structure :

/*

feature/
└── home/
    ├── ui/
    │   ├── HomeScreen.kt
    │   └── HomeUiState.kt   ✅ UI state model
    │
    ├── viewmodel/
    │   └── HomeViewModel.kt
    │
    ├── data/
    │   ├── remote/
    │   │   ├── HomeApi.kt
    │   │   └── HomeResponse.kt   ✅ API model
    │   │
    │   ├── repository/
    │   │   └── HomeRepository.kt
    │   │
    │   └── mapper/
    │       └── HomeMapper.kt   ✅ optional but clean

 */

/*

Note :
backgroundImage :--->
Your background image is actually a FULL circular/radial pattern.

But because of:

contentScale = ContentScale.FillWidth

and

alignment = Alignment.TopStart

only part of the circle becomes visible.

That is why you now see:

arcs
curves
circular waves
partial rings

instead of a full circle.
 */



@Composable
fun LoginScreen(
    onNavigateToRegister: () -> Unit,
    onNavigateToVerifyOtp: (
        String,
        VerifyOtpSource
    ) -> Unit,
    viewModel: LoginViewModel =
        hiltViewModel()
) {

    // ===================================================
    // UI STATE
    // ===================================================

    val uiState by viewModel.uiState
        .collectAsStateWithLifecycle()


    // ===================================================
    // EVENTS
    // ===================================================

    ObserveAsEvents(
        flow = viewModel.event
    ){ event ->

        when(event) {

            is LoginEvent.NavigateToVerifyOtp -> {

                onNavigateToVerifyOtp(

                    event.mobileNumber,

                    event.source
                )
            }

            is LoginEvent.ShowSnackbar -> {

                // Handle snackbar here
            }
        }

    }

    // =====================================
    // CONTENT
    // =====================================

    LoginContent(

        uiState = uiState,

        onMobileChange =
            viewModel::onMobileChange,

        onSendOtpClick =
            viewModel::onSendOtpClick,

        onNavigateToRegister =
            onNavigateToRegister
    )
}



@Preview(
    showBackground = true,
    showSystemUi = true
)

@Composable
private fun LoginContentPreview() {




        LoginContent(

            uiState = LoginUiState(
                mobileNumber = "9876543210"
            ),

            onMobileChange = {},

            onSendOtpClick = {},

            onNavigateToRegister = {}
        )

}