package com.policyboss.customer.feature.tabfeatures.privilege.privilegeScreen.ui.privilegeScreen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.R
import com.policyboss.customer.feature.tabfeatures.privilege.privilegeScreen.privillageState.PrivilegeAction

import com.policyboss.customer.ui.components.button.PrimaryCTAButton
import com.policyboss.customer.ui.theme.AppColors
import com.policyboss.customer.ui.theme.AppColors.PrimaryYellow
import com.policyboss.customer.ui.theme.gradients.AppGradients
import com.policyboss.customer.ui.theme.headlineLargeExtraBold
import com.policyboss.customer.ui.theme.labelSmallBold

@Composable
 fun PrivilegeHeaderSection(onAction: (PrivilegeAction) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
            .background(PrimaryYellow)
            .padding(top = 24.dp, bottom = 24.dp, start = 24.dp, end = 24.dp)
    )
    {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        )
        {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.offset(x = 0.dp, y = 50.dp)
            ){
                Text(
                    text = "WELCOME TO PRIVILEGE MODE",
                    style = MaterialTheme.typography.labelSmallBold,
                    color = AppColors.GoldText
                    //letterSpacing = 1.sp
                )

                Spacer(modifier = Modifier.height(8.dp))
//                Icon(
//                    painter = painterResource(id = R.drawable.ic_star_oval),
//                    contentDescription = null,
//                    tint = Color.Black.copy(alpha = 0.5f),
//                    modifier = Modifier.width(15.62.dp)
//                        .height(18.35.dp)
//                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                ) {
                    // LEFT SIDE: The line needs to fade from Transparent (far left) to Color (near icon)
                    // So we use fadeToLeft
                    Box(
                        Modifier
                            .weight(1f)
                            .height(1.dp)
                            .drawBehind { drawRect(AppGradients.fadeToLeft(AppColors.GoldText)) }
                    )

                    // Icon (Center)
                    Icon(
                        painter = painterResource(id = R.drawable.ic_star_oval),
                        contentDescription = null,
                        tint = AppColors.GoldText, // Match your gold theme
                        modifier = Modifier.padding(horizontal = 12.dp).size(18.dp)
                    )

                    // RIGHT SIDE: The line needs to fade from Color (near icon) to Transparent (far right)
                    // So we use fadeToRight
                    Box(
                        Modifier
                            .weight(1f)
                            .height(1.dp)
                            .drawBehind { drawRect(AppGradients.fadeToRight(AppColors.GoldText)) }
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))


                Text(
                    text = "Earn from your insurance -\nBecome a 'privileged user'",
                    style = MaterialTheme.typography.headlineLargeExtraBold,
                    color = AppColors.GoldText,
                    textAlign = TextAlign.Center,

                )
            }


           // Spacer(modifier = Modifier.height(16.dp))

            // Placeholder for the illustration
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(274.dp),
                contentAlignment = Alignment.Center
            ) {

                // 1. Right Shield (Back)
                Image(
                    painter = painterResource(R.drawable.ic_gold_star),
                    contentDescription = null,
                    modifier = Modifier
                        .size(148.dp)
                        .align(Alignment.CenterEnd)
                        .offset(x = 16.dp, y = 20.dp)
                )

                // 2. Left Coin (Back)
                Image(
                    painter = painterResource(R.drawable.dollar_full),
                    contentDescription = null,
                    modifier = Modifier
                        .size(70.dp)
                        .align(Alignment.CenterStart)
                        .offset(x = 12.dp, y = 40.dp)
                )

                // 3. Small Coin (Back)
                Image(
                    painter = painterResource(R.drawable.dollar_full),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.CenterStart)
                        .offset(x = 0.dp, y = 0.dp)
                )

                // 4. Person (Middle Layer)
                Image(
                    painter = painterResource(R.drawable.ic_person),
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .fillMaxHeight()
                        .align(Alignment.BottomCenter)
                        .offset(x = 0.dp, y = 50.dp)
                )

                // 5. Button (Front Layer - Drawn Last)
                PrimaryCTAButton(
                    text = "Set up Privilege Account",
                    onClick = {
                        onAction(PrivilegeAction.SetupAccountClicked) // Dispatch action here
                    },
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                    // Optional: Add a slight bottom padding if you need to push it up slightly
                    // from the absolute bottom edge of the Box to match the design perfectly.
                    // .padding(bottom = 8.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true, name = "Privilege Header Preview")
@Composable
 fun PrivilegeHeaderSectionPreview() {
    // Wrapping in your DarkBackground so the yellow rounded corners are visible
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF131722))
    ) {
        _root_ide_package_.com.policyboss.customer.feature.tabfeatures.privilege.privilegeScreen.ui.privilegeScreen.component.PrivilegeHeaderSection(
            onAction = {
                // Empty lambda: We don't need actions to actually trigger in the preview
            }
        )
    }
}