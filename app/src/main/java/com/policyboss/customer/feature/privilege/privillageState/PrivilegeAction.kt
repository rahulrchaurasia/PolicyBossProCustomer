package com.policyboss.customer.feature.privilege.privillageState


// 2. What the user can do
sealed interface PrivilegeAction {

    object CloseFloatingVideo : PrivilegeAction
    object SetupAccountClicked : PrivilegeAction

    object OnVideoClick : PrivilegeAction // Add this!


}