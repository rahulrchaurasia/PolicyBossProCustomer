package com.policyboss.customer.feature.privilege.ui.privilegeScreen.privillegeStory

import androidx.compose.runtime.Composable
import com.policyboss.customer.feature.privilege.privillageState.PrivilegeAction
import com.policyboss.customer.feature.privilege.viewmodel.PrivilegeViewModel

@Composable
fun PrivilegeStoriesRoute(
    viewModel: PrivilegeViewModel,
    onCollapseClick: () -> Unit,
    onCloseClick: () -> Unit
) {

    PrivilegeStoriesScreen(

        exoPlayer = viewModel.player,

        onCollapseClick = onCollapseClick,

        onCloseClick = {

            viewModel.onAction(
                PrivilegeAction.CloseFloatingVideo
            )

            onCloseClick()
        }
    )
}