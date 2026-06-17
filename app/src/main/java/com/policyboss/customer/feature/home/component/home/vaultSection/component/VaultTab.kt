package com.policyboss.customer.feature.home.component.home.vaultSection.component



import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.policyboss.customer.feature.home.dummy.HomeDummyData
import com.policyboss.customer.feature.home.model.vault.VaultTabItem

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image



@Composable
fun VaultTab(

    item: VaultTabItem,

    selected: Boolean,

    onClick: () -> Unit
) {

    val contentColor =

        if (selected)

            Color(0xFF111827)

        else

            Color(0xFF6B7280)

    val backgroundColor =

        if (selected)

            Color.White

        else

            Color.Transparent

    val shadowElevation =

        if (selected)

            2.dp

        else

            0.dp

    Row(

        modifier = Modifier

            .padding(horizontal = 4.dp)

            .shadow(

                elevation = shadowElevation,

                shape = RoundedCornerShape(50.dp),

                clip = false
            )

            .background(

                color = backgroundColor,

                shape = RoundedCornerShape(50.dp)
            )

            .clip(
                RoundedCornerShape(50.dp)
            )

            .clickable {

                onClick()
            }

            .padding(

                horizontal = 14.dp,

                vertical = 8.dp
            ),

        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(

            text = item.title,

            style = MaterialTheme.typography.labelMedium,

            color = contentColor,

            fontWeight =

                if (selected)

                    FontWeight.SemiBold

                else

                    FontWeight.Medium
        )

        Spacer(

            modifier = Modifier.width(6.dp)
        )

        Image(

            painter = painterResource(
                id = item.iconRes
            ),

            contentDescription = item.title,

            modifier = Modifier.size(16.dp)
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFF3F4F6
)
@Composable
private fun VaultTabStatesPreview() {

    Row(

        modifier = Modifier.padding(16.dp)
    ) {

        VaultTab(

            item = HomeDummyData.vaultTabs[0],

            selected = true,

            onClick = {}
        )

        VaultTab(

            item = HomeDummyData.vaultTabs[1],

            selected = false,

            onClick = {}
        )
    }
}