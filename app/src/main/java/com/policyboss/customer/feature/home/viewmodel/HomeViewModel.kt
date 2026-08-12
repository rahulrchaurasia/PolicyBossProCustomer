package com.policyboss.customer.feature.home.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.policyboss.customer.core.datastore.AppDataManager
import com.policyboss.customer.feature.dummyData.AppDummyData
import com.policyboss.customer.feature.home.model.homeState.HomeAction
import com.policyboss.customer.feature.home.model.homeState.HomeExperience
import com.policyboss.customer.feature.home.model.homeState.HomeUiEvent
import com.policyboss.customer.feature.home.model.homeState.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


/*
User Click
   ↓
HomeScreen
   ↓
onAction(HomeAction)
   ↓
HomeRoute
   ↓
ViewModel (if business logic/API required)
   ↓
UiState OR UiEvent
   ↓
HomeRoute
   ↓
Navigation / Snackbar
 */

//Mark : HomeViewModel decides which experience to show.
@HiltViewModel
class HomeViewModel @Inject constructor(
    // 🚀 1. Inject AppDataManager to get the saved user profile data
    private val appDataManager: AppDataManager
) : ViewModel() {


    //region Declaration
    // Private mutable state
    private val _uiState = MutableStateFlow(HomeUiState())
    // Public immutable state for Compose to observe
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    // The Channel for one-time navigation/snackbars
    private val _uiEvent = Channel<HomeUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    //endregion

    init {
        observeUserData()
        fetchHomeData()
    }

    // =========================================================
    // DATA OBSERVATION & FETCHING
    // =========================================================
    // 🚀 2. Automatically listen to DataStore changes for the user's name
    private fun observeUserData() {
        viewModelScope.launch {
            appDataManager.userName.collect { name ->

                val displayName = name.ifBlank { "Guest User" }

                _uiState.update { currentState ->
                    currentState.copy(
                        userName = displayName,
                        // 🚀 3. Generate initials dynamically based on the name
                        userInitials = extractInitials(displayName)
                    )
                }
            }
        }
    }

    // =========================================================
    // HELPERS
    // =========================================================

    // 🚀 5. Helper function to extract initials (e.g., "Rahul Chaurasia" -> "RC")
    private fun extractInitials(name: String): String {
        if (name.isBlank()) return "U"

        // Split the name by whitespace
        val parts = name.trim().split("\\s+".toRegex())

        return when (parts.size) {
            0 -> "U"
            // If only one name is provided (e.g., "Rahul"), return "R"
            1 -> parts[0].take(1).uppercase()
            // If two or more names (e.g., "Rahul Chaurasia"), return "RC"
            else -> "${parts[0].take(1)}${parts[1].take(1)}".uppercase()
        }
    }

    // region Determine User Category
    private fun determineExperience(): HomeExperience {

        val cameFromMicrosite = false

        val hasRecentlyBoughtPolicy = true

        return when {

            cameFromMicrosite -> {
                HomeExperience.EARN_RENEWALS
            }

            hasRecentlyBoughtPolicy -> {
                HomeExperience.ACCESS_POLICY
            }

            else -> {
                HomeExperience.EXPLORE
            }
        }
    }
    //endregion

    //region fetch data
    private fun fetchHomeData() {

        viewModelScope.launch {



            _uiState.update {

                it.copy(
                    isLoading = true
                )
            }

            delay(2000)

            _uiState.update {

                it.copy(

                    isLoading = false,



                    promoBanners =
                        AppDummyData.promoBanners,

                    quickActions =
                        AppDummyData.quickActions,

                    earningBanners =
                        AppDummyData.earningBanners,

                    curatedPolicies =
                        AppDummyData.curatedPolicies,

                    vaultPolicies =
                        AppDummyData.vaultPolicies,

                    bosspediaStories =
                        AppDummyData.bosspediaStories,

                    bosspediaArticles =
                        AppDummyData.bosspediaArticles,

                    videos =
                        AppDummyData.dummyVideos

                )
            }
        }
    }

    //endregion

    //mark :Handle for all click Event say action for HomeScreen
    //region onAction Hamdling

    fun onAction(
        action: HomeAction
    ) {

        when (action) {

            is HomeAction.OnVaultTabSelected ->
                handleVaultTab(action)

            is HomeAction.OnShowPolicyBottomSheetClick ->
                handlePolicyClick(action)

            is HomeAction.OnDismissPolicyBottomSheet ->
                handleDismiss()

            is HomeAction.OnPrivilegeBannerClick ->
                handlePrivilegeClick()

            is HomeAction.OnAssistanceClick ->
                handleOnAssistanceClick()

            is HomeAction.OnVideoClick ->
                handleVideoClick(action.videoId)

            else -> {

            }
        }
    }

    private fun handleOnAssistanceClick() {

        val mobile = _uiState.value.rmMobileNo

        if (mobile.isNotBlank()) {

            viewModelScope.launch {

                _uiEvent.send(

                    HomeUiEvent.OpenDialer(

                        phoneNumber = mobile
                    )
                )
            }
        }
    }
    private fun handleVaultTab(
        action: HomeAction.OnVaultTabSelected
    ){

        _uiState.update {

            it.copy(
                selectedVaultTab = action.index
            )
        }
    }
    private fun handlePolicyClick(action: HomeAction.OnShowPolicyBottomSheetClick) {
        _uiState.update {
            it.copy(
                //showPolicyBottomSheet = true,
                selectedVaultPolicy = action.policy
            )
        }
    }



    private fun handleDismiss() {
        _uiState.update {
            it.copy(
               // showPolicyBottomSheet = false
                selectedVaultPolicy = null
            )
        }
    }
    private fun handlePrivilegeClick() {
        handleJoinPrivilege()
    }

    private fun handleJoinPrivilege() {

        viewModelScope.launch {

            _uiState.update {

                it.copy(
                    isLoading = true
                )
            }

            delay(1500)

            val isEligible = true

            _uiState.update {

                it.copy(
                    isLoading = false
                )
            }

            if (isEligible) {

                _uiEvent.send(

                    HomeUiEvent
                        .NavigateToPrivilegeSuccess
                )
            } else {

                _uiEvent.send(

                    HomeUiEvent
                        .ShowSnackbar(
                            "Not eligible"
                        )
                )
            }
        }
    }


    private fun handleVideoClick(videoId: String){

        // 1. Find the video in your current state
        val clickedVideo = _uiState.value.videos.find { it.id == videoId }

        clickedVideo?.let { video ->
            viewModelScope.launch {
                // OPTIONAL: Add analytics tracking here later!
                // e.g., analytics.track("Watched Video", video.title)

                // 2. Send the event to the UI to open the URL
                _uiEvent.send(
                    HomeUiEvent.OpenUrl(url = video.videoUrl)
                )
            }
        }

    }

    //endregion


}