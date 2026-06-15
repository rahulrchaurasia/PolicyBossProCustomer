package com.policyboss.customer.feature.login.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.policyboss.customer.R

@Composable
fun AuthFloatingIcon(
    @DrawableRes iconRes: Int,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.78f)
            .offset(y = (-40).dp),

        contentAlignment = Alignment.TopCenter
    ) {

        Image(
            painter = painterResource(id = iconRes),

            contentDescription = null,

            modifier = Modifier.size(80.dp),

            contentScale = ContentScale.Fit
        )
    }
}



@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun AuthFloatingIconPreview() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF071120))
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.78f)
                .align(Alignment.BottomCenter)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(
                        topStart = 32.dp,
                        topEnd = 32.dp
                    )
                )
        )

        AuthFloatingIcon(
            modifier = Modifier.align(Alignment.BottomCenter),
            iconRes = R.drawable.ic_login
        )
    }
}