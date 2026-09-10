package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportScreen.ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportScreen.model.ClaimSupportMenu

import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportScreen.model.state.ClaimAction


@Composable
fun ClaimSupportMenuSection(
    modifier: Modifier = Modifier,
    onAction: (ClaimAction) -> Unit
) {

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            bottom = 16.dp
        )
    ) {

        items(ClaimSupportMenu.entries) { menu ->

            ClaimSupportMenuItem(
                menu = menu,
                onClick = {
                    onAction(
                        ClaimAction.OnSupportMenuClick(menu)
                    )
                }
            )
        }
    }
}
//@Composable
//fun ClaimSupportMenuSection(
//    onAction: (ClaimAction) -> Unit
//) {
//
//    LazyColumn {
//
//        ClaimSupportMenu.entries.forEach { menu ->
//
//            ClaimSupportMenuItem(
//                menu = menu,
//                onClick = {
//                    onAction(
//                        ClaimAction.OnSupportMenuClick(menu)
//                    )
//                }
//            )
//        }
//    }
//}