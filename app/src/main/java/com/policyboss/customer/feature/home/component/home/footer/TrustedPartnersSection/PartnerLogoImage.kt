package com.policyboss.customer.feature.home.component.home.footer.TrustedPartnersSection

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.R

@Composable
fun PartnerLogoImage(
    logo: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = logo),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = modifier.size(
            width = 130.dp,
            height = 48.dp
        )
    )
}
@Preview(
    name = "Partner Logo",
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
private fun PartnerLogoImagePreview() {

    PartnerLogoImage(
        logo = R.drawable.ic_footer1
    )
}
