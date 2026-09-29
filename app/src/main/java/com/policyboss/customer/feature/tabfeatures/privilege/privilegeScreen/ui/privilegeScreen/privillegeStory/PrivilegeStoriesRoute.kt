package com.policyboss.customer.feature.tabfeatures.privilege.privilegeScreen.ui.privilegeScreen.privillegeStory

import androidx.compose.runtime.Composable
import com.policyboss.customer.feature.tabfeatures.privilege.privilegeScreen.privillageState.PrivilegeAction
import com.policyboss.customer.feature.tabfeatures.privilege.privilegeScreen.viewmodel.PrivilegeViewModel


@Composable
fun PrivilegeStoriesRoute(
    viewModel: PrivilegeViewModel,
    onCollapseClick: () -> Unit,
    onCloseClick: () -> Unit
) {

    _root_ide_package_.com.policyboss.customer.feature.tabfeatures.privilege.privilegeScreen.ui.privilegeScreen.privillegeStory.PrivilegeStoriesScreen(

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