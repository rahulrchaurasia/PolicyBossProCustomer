package com.policyboss.customer.feature.privilege.model.privilegeState


// 2. What the user can do
sealed interface PrivilegeAction {
    object OnSetupAccountClick : PrivilegeAction
    object OnJoinPrivilegeClick : PrivilegeAction
    // ...
}