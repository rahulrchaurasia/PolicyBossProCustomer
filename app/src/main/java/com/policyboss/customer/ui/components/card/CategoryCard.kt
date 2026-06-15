package com.policyboss.customer.ui.components.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.policyboss.customer.ui.theme.PolicyBossCustomerTheme
import com.policyboss.customer.R



//enum class CategoryCardSize(
//    val size: Dp
//) {
//
//    SMALL(82.dp),
//
//    MEDIUM(96.dp),
//
//    LARGE(112.dp)
//}
//
//@Composable
//fun CategoryCard(
//    iconRes: Int,
//    size: CategoryCardSize,
//    modifier: Modifier = Modifier
//) {
//
//    Image(
//        painter = painterResource(id = iconRes),
//        contentDescription = null,
//        modifier = modifier.size(size.size),
//        contentScale = ContentScale.Fit
//    )
//}


@Composable
fun CategoryCard(
    iconRes: Int,
    width: Dp,
    height: Dp,
    modifier: Modifier = Modifier
) {

    Image(
        painter = painterResource(id = iconRes),
        contentDescription = null,
        modifier = modifier
            .width(width)
            .height(height),
        contentScale = ContentScale.Fit
    )
}

//@Preview(showBackground = true)
//@Composable
//fun SplashCategoryCardPreview() {
//
//    PolicyBossCustomerTheme {
//
//        CategoryCard(
//            iconRes = R.drawable.ic_health,
//            width = "200".dp,
//            height = "200".dp,
//        )
//    }
//}
