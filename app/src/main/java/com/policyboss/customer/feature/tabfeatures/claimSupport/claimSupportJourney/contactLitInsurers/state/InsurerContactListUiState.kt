package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.contactLitInsurers.state

import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.contactLitInsurers.model.InsurerContactModel

data class InsurerContactListUiState(
    val isLoading: Boolean = false,
    val searchQuery: String = "",
    val insurers: List<InsurerContactModel> = emptyList(),
    val isGridView: Boolean = true // Default is 2-item grid
)



