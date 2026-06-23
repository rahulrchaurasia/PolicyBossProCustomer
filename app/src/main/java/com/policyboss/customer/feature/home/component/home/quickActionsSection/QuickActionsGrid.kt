package com.policyboss.customer.feature.home.component.home.quickActionsSection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.policyboss.customer.feature.home.component.home.card.QuickActionCard
import com.policyboss.customer.feature.home.model.QuickAction

import androidx.compose.ui.tooling.preview.Preview
import com.policyboss.customer.feature.home.dummy.HomeDummyData


import com.policyboss.customer.ui.theme.PolicyBossCustomerTheme
@Composable
private fun QuickActionsGrid(

    actions: List<QuickAction>,

    onClick: (String) -> Unit

) {

    val rows = actions.chunked(2)

    Column {

        rows.forEachIndexed { rowIndex, rowItems ->

            val rowOffset =

                if (rowIndex == 0)

                    (-42).dp

                else

                    0.dp

            Row(

                modifier = Modifier

                    .fillMaxWidth()

                    .offset(y = rowOffset)

                    .padding(horizontal = 24.dp),

                horizontalArrangement = Arrangement.spacedBy(16.dp)

            ) {

                rowItems.forEach { action ->

                    QuickActionCard(

                        modifier = Modifier.weight(1f),

                        action = action,

                        onClick = {

                            onClick(action.id)

                        }
                    )
                }

                repeat(2 - rowItems.size) {

                    Spacer(

                        modifier = Modifier.weight(1f)

                    )
                }
            }

            if (rowIndex < rows.lastIndex) {

                Spacer(

                    modifier = Modifier.height(16.dp)

                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun QuickActionsGridPreview() {

    PolicyBossCustomerTheme {

        QuickActionsGrid(

            actions = HomeDummyData.quickActions,

            onClick = {}
        )
    }
}