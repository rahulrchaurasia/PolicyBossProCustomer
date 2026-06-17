package com.policyboss.customer.feature.home.component.home.vaultSection.component




import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.policyboss.customer.R

import androidx.compose.material3.MaterialTheme
@Composable
fun PolicyHeaderImage(

    carImage: Int
) {

    Box(

        contentAlignment = Alignment.Center
    ) {

        Image(

            painter = painterResource(
                R.drawable.ic_security_check
            ),

            contentDescription = null,

            modifier = Modifier

                .size(120.dp)

                .offset(
                    x = (-18).dp
                )

                .rotate(-12f)
        )

        Image(

            painter = painterResource(
                carImage
            ),

            contentDescription = null,

            modifier = Modifier

                .width(180.dp)

                .height(110.dp)
        )
    }
}

@Preview(

    showBackground = true,

    backgroundColor = 0xFFFFFFFF
)
@Composable
private fun PolicyHeaderImagePreview() {

    MaterialTheme {

        Box(

            modifier = Modifier.padding(24.dp)
        ) {

            PolicyHeaderImage(

                carImage = R.drawable.ic_car_protect
            )
        }
    }
}