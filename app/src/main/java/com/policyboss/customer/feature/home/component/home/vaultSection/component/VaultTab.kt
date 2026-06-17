package com.policyboss.customer.feature.home.component.home.vaultSection.component


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.policyboss.customer.feature.home.model.vault.VaultTabItem

@Composable
fun VaultTab(

    item: VaultTabItem,

    selected: Boolean,

    onClick: () -> Unit
) {

    Row(

        modifier = Modifier

            .padding(
                horizontal = 4.dp
            )

            .background(

                if (selected)

                    Color.White

                else

                    Color.Transparent,

                RoundedCornerShape(50.dp)
            )

            .clickable {

                onClick()
            }

            .padding(
                horizontal = 16.dp,
                vertical = 10.dp
            ),

        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(

            text = item.title,

            fontWeight =

                if (selected)

                    FontWeight.SemiBold

                else

                    FontWeight.Normal
        )

        Spacer(

            modifier = Modifier.width(6.dp)
        )

        Icon(

            painter = painterResource(
                item.iconRes
            ),

            contentDescription = null
        )
    }
}