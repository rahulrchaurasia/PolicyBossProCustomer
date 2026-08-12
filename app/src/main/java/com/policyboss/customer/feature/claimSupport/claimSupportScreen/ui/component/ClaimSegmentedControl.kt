package com.policyboss.customer.feature.claimSupport.claimSupportScreen.ui.component



import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.policyboss.customer.feature.claimSupport.claimSupportScreen.model.ClaimTab
import com.policyboss.customer.ui.theme.AppColors
import com.policyboss.customer.ui.theme.PolicyBossCustomerTheme

internal val SegmentHeight = 56.dp
internal val SegmentCornerRadius = 20.dp
internal val IndicatorPadding = 5.dp


//#e1f5fd

@Composable
fun ClaimSegmentedControl(
    selectedTab: ClaimTab,
    onTabSelected: (ClaimTab) -> Unit,
    modifier: Modifier = Modifier
) {

    val tabs = ClaimTab.entries

    var containerWidthPx by remember {
        mutableIntStateOf(0)
    }

    val density = LocalDensity.current

    val tabWidth by remember(containerWidthPx, density) {
        derivedStateOf {
            with(density) {
                if (containerWidthPx == 0) {
                    0.dp
                } else {
                    containerWidthPx.toDp() / tabs.size
                }
            }
        }
    }

    Surface(
        modifier = modifier
            .animateContentSize()
            .fillMaxWidth()
            .height(SegmentHeight)
            .onSizeChanged {
                containerWidthPx = it.width
            },
        shape = RoundedCornerShape(SegmentCornerRadius),
        color = AppColors.PaleCyan,
        border = BorderStroke(
            1.dp,
            AppColors.SegmentedBorder
        ),
        tonalElevation = 0.dp
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(SegmentCornerRadius))
        ) {


            //----------------------------------------------------------
            // Sliding White Indicator
            //----------------------------------------------------------


            if (tabWidth > 0.dp) {
                SlidingIndicator(
                    selectedTab = selectedTab,
                    tabWidth = tabWidth
                )
            }

            //----------------------------------------------------------
            // Tabs
            //----------------------------------------------------------

            Row(
                modifier = Modifier.fillMaxSize()
            ) {

                tabs.forEach { tab ->

                    SegmentTab(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        title = tab.title,
                        selected = tab == selectedTab,
                        onClick = {
                            if (tab == selectedTab) return@SegmentTab
                            onTabSelected(tab)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun SlidingIndicator(
    selectedTab: ClaimTab,
    tabWidth: Dp,
    modifier: Modifier = Modifier
) {

    val selectedIndex = ClaimTab.entries.indexOf(selectedTab)

    val offsetX by animateDpAsState(
        targetValue = tabWidth * selectedIndex,
        animationSpec = tween(
            durationMillis = 280,
            easing = FastOutSlowInEasing
        ),
        label = "IndicatorOffset"
    )


    Surface(

        modifier = modifier
            .offset(x = offsetX)
            .padding(IndicatorPadding)
            .width(tabWidth - IndicatorPadding * 2)
            .fillMaxHeight(),

        shape = RoundedCornerShape(SegmentCornerRadius),

        // White selected tab
        color = AppColors.White,

        border = BorderStroke(
            1.dp,
            Color(0xFFF2F2F2)
        ),


        // Very subtle elevation like Figma
        shadowElevation = 0.dp,
        tonalElevation = 1.dp

    ) {}
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
    widthDp = 360
)
@Composable
private fun ClaimSegmentedControlMyClaimsPreview() {

    PolicyBossCustomerTheme {

        Box(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
        ) {

            ClaimSegmentedControl(
                selectedTab = ClaimTab.MY_CLAIMS,
                onTabSelected = {}
            )
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
    widthDp = 360
)
@Composable
private fun ClaimSegmentedControlSupportPreview() {

    PolicyBossCustomerTheme {

        Box(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
        ) {

            ClaimSegmentedControl(
                selectedTab = ClaimTab.CLAIM_SUPPORT,
                onTabSelected = {}
            )
        }
    }
}