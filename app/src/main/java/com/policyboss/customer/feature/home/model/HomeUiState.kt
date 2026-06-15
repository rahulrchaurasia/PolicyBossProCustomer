package com.policyboss.customer.feature.home.model


/*
Here is how you separate the flow of data:

UiState (StateFlow): Represents what the UI is (Loading, Success, Error).

UiAction (Function): What the user does (Clicks a button).
 Flow flows UP from UI to ViewModel.

UiEvent (Channel): What the ViewModel wants the UI to do
 once (Navigate, Show a Snackbar). Data flows DOWN from ViewModel to UI.
 */

//data class HomeUiState(
//
//
//
//    val userName: String = "Rahul",
//    val userInitials: String = "DD",
//    val isLoading: Boolean = false,
//    val curatedPolicies: List<String> = listOf("Car", "Bike", "CV", "Health", "Life", "Travel"),
//    // Add other dynamic data here (e.g., active policies, earnings)
//
//
//    val quickActions: List<QuickAction> = listOf(
//        QuickAction("renew", "Renew & Earn", "Become a 'Privileged user'", isPro = true),
//        QuickAction("vault", "Policy Vault", "All your policies in one place"),
//        QuickAction("claim", "Claim Support", "Guided claim filing & assistance"),
//        QuickAction("bosspedia", "BOSSPedia", "Daily insights about insurance")
//    )
//
//
//
//    val banners: List<BannerUi> = emptyList(),
//
//    val quickActions: List<QuickActionUi> = emptyList(),
//
//    val policies: List<PolicyUi> = emptyList(),
//
//    val vaultPolicies: List<PolicyUi> = emptyList(),
//
//    val articles: List<ArticleUi> = emptyList()
//)