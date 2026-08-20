package com.policyboss.customer.feature.claimSupport.claimSupportJourney.claimDetails.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.policyboss.customer.R
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.claimDetails.model.claimDetailState.AccidentDetailsAction
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.claimDetails.model.claimDetailState.AccidentDetailsUiEvent
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.claimDetails.model.claimDetailState.AccidentDetailsUiState
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.claimDetails.viewmodel.AccidentDetailsViewModel
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.fileClaim.ui.component.BottomFooter
import com.policyboss.customer.feature.claimSupport.claimSupportScreen.viewmodel.LookupType
import com.policyboss.customer.ui.components.toolbarHeader.AppTopBar
import com.policyboss.customer.ui.theme.AppColors

@Composable
fun AccidentDetailsRoute(
    viewModel: AccidentDetailsViewModel,
    onNavigateBack: () -> Unit,
    onNavigateNext: (AccidentDetailsUiState) -> Unit, // Pass state up to save in Journey ViewModel
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is AccidentDetailsUiEvent.NavigateNext -> onNavigateNext(uiState)
                is AccidentDetailsUiEvent.ShowError -> { /* Show Snackbar */ }
            }
        }
    }

    AccidentDetailsScreen(
        uiState = uiState,
        onAction = viewModel::onAction,
        onBackClick = onNavigateBack,
        modifier = modifier
    )
}
