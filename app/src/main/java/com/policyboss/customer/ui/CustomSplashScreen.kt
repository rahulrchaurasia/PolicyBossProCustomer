package com.policyboss.customer.ui



import android.view.LayoutInflater
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.draw.scale

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.policyboss.customer.R
import com.policyboss.customer.ui.components.card.CategoryCard

import com.policyboss.customer.ui.theme.PolicyBossCustomerTheme

import kotlinx.coroutines.delay


import androidx.constraintlayout.compose.ConstraintLayout


//🔴 Case 3 — TRUE Fullscreen Screen (No padding at all)

/*
Note :----->
constrainAs(motor)
.constrainAs(motor)

Means:

"This composable's position
will be controlled using constraints."
 */

// ===================================================
// SPLASH ICON CONFIG
// ===================================================
data class SplashIconConfig(
    val imageRes: Int,
    val scale: Float = 1f
)


// ===================================================
// SPLASH SCREEN
// ===================================================

@Composable
fun CustomSplashScreen(
    onTimeout: () -> Unit
) {




    // ===================================================
    // CENTER ICONS
    // ===================================================

    val icons = remember {

        listOf(

            SplashIconConfig(
                imageRes = R.drawable.ic_earn,
                scale = 1f
            ),

            SplashIconConfig(
                imageRes = R.drawable.ic_access,
                scale = 1.78f
            ),

            SplashIconConfig(
                imageRes = R.drawable.ic_quote,
                scale = 1.72f
            )
        )
    }

    var currentIndex by remember {
        mutableIntStateOf(0)
    }

    // ===================================================
    // ICON ANIMATION
    // ===================================================

    LaunchedEffect(Unit) {

        repeat(icons.size) {

            delay(1000)

            currentIndex = (currentIndex + 1) % icons.size
        }

        delay(700)

         onTimeout()
    }

    // ===================================================
    // UI
    // ===================================================

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
    ) {

        val (
            health,
            motor,
            life,
            center,
            airplane,
            frame,
            logo
        ) = createRefs()

        // ===================================================
        // CENTER ANIMATION
        // ===================================================

        Box(
            modifier = Modifier.constrainAs(center) {

                top.linkTo(parent.top)

                bottom.linkTo(parent.bottom)

                centerHorizontallyTo(parent)

                // slightly upper than center
                verticalBias = 0.46f
            }
        ) {

            Crossfade(
                targetState = icons[currentIndex],
                label = ""
            ) { config ->

                Box(
                    modifier = Modifier
                        .width(136.dp)
                        .height(122.dp),

                    contentAlignment = Alignment.Center
                ) {

                    Image(
                        painter = painterResource(id = config.imageRes),

                        contentDescription = null,

                        modifier = Modifier
                            .fillMaxSize()

                            // ✅ ONLY VISUAL SCALE
                            .scale(config.scale),

                        contentScale = ContentScale.Fit
                    )
                }
            }
        }

        // ===================================================
        // HEALTH
        // ===================================================

        CategoryCard(
            iconRes = R.drawable.ic_health,
            width = 88.dp,
            height = 80.dp,
            modifier = Modifier.constrainAs(health) {

                top.linkTo(parent.top, margin = 52.dp)

                centerHorizontallyTo(parent)
            }
        )

        // ===================================================
        // MOTOR
        // ===================================================

        CategoryCard(
            iconRes = R.drawable.ic_motor,
            width = 108.dp,
            height = 98.dp,
            modifier = Modifier
                .constrainAs(motor) {

                    end.linkTo(center.start)

                    bottom.linkTo(
                        center.top,
                        margin = 18.dp
                    )
                }
                .offset(x = (-12).dp)
        )

        // ===================================================
        // LIFE
        // ===================================================

        CategoryCard(
            iconRes = R.drawable.ic_life,
            width = 90.dp,
            height = 81.dp,
            modifier = Modifier
                .constrainAs(life) {

                    start.linkTo(center.end)

                    // same XML-like alignment
                    top.linkTo(motor.top)
                    bottom.linkTo(motor.top)
                }
                .offset(x = 14.dp)
        )

        // ===================================================
        // AIRPLANE
        // ===================================================

        CategoryCard(
            iconRes = R.drawable.ic_airplane,
            width = 80.dp,
            height = 73.dp,
            modifier = Modifier.constrainAs(airplane) {

                start.linkTo(
                    parent.start,
                    margin = 28.dp
                )

                top.linkTo(
                    center.bottom,
                    margin = 52.dp
                )
            }
        )

        // ===================================================
        // FRAME
        // ===================================================

        CategoryCard(
            iconRes = R.drawable.ic_frame,
            width = 64.dp,
            height = 58.dp,
            modifier = Modifier.constrainAs(frame) {

                end.linkTo(
                    parent.end,
                    margin = 28.dp
                )

                top.linkTo(airplane.top)
                bottom.linkTo(airplane.top)
            }
        )

        // ===================================================
        // LOGO
        // ===================================================

        Image(
            painter = painterResource(id = R.drawable.ic_policyboss_logo),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(logo) {

                    centerHorizontallyTo(parent)

                    bottom.linkTo(
                        parent.bottom,
                        margin = 18.dp
                    )
                }
                .navigationBarsPadding()
                .width(158.dp)
                .height(48.dp),
            contentScale = ContentScale.Fit
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CustomSplashPreview() {
    PolicyBossCustomerTheme {
        CustomSplashScreen(onTimeout = {

        })
    }
}

@Composable
fun XmlConstraintLayoutScreen() {

    AndroidView(
        factory = { context ->

            LayoutInflater.from(context)
                .inflate(R.layout.layout_test, null)
        }
    )
}

@Preview
@Composable
fun PreviewXmlScreen() {
    XmlConstraintLayoutScreen()
}
