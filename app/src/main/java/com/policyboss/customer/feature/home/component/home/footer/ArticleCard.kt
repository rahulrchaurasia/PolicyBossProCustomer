package com.policyboss.customer.feature.home.component.home.footer



// ---------------------------- IMPORTS ----------------------------

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.policyboss.customer.feature.home.dummy.HomeDummyData
import com.policyboss.customer.feature.home.model.bossepidia.BosspediaArticle
import com.policyboss.customer.ui.theme.AppColors

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxHeight

import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment


import androidx.compose.ui.geometry.Offset

import androidx.compose.ui.graphics.Path

import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.policyboss.customer.ui.theme.buttonLinkSmall

@Composable
fun ArticleCard(
    article: BosspediaArticle,
    onClick: () -> Unit
) {

    val darkCardColor = Color(0xFF141720)

    val gradientStart = Color(0xFF2E90FA)

    val gradientEnd = Color(0xFFCBF4DF)

    Surface(

        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp),

        shape = RoundedCornerShape(20.dp),

        color = darkCardColor,

        onClick = onClick
    ) {

        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            Canvas(
                modifier = Modifier.matchParentSize()
            ) {

                val width = size.width

                val height = size.height

                val path = Path().apply {

                    moveTo(width * 0.78f, 0f)

                    cubicTo(

                        width * 0.65f,
                        height * 0.15f,

                        width * 0.92f,
                        height * 0.35f,

                        width * 0.80f,
                        height
                    )

                    lineTo(width, height)

                    lineTo(width, 0f)

                    close()
                }

                drawPath(

                    path = path,

                    brush = Brush.linearGradient(

                        colors = listOf(
                            gradientStart,
                            gradientEnd
                        ),

                        start = Offset(
                            width * 0.7f,
                            0f
                        ),

                        end = Offset(
                            width,
                            height
                        )
                    )
                )
            }

            Column(

                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(
                        start = 16.dp,
                        end = 140.dp,
                        top = 16.dp,
                        bottom = 16.dp
                    )
            ) {

                Text(

                    text = article.readTime,

                    color = AppColors.TextSecondary,

                    style = MaterialTheme.typography.labelSmall
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Text(

                    text = article.title,

                    color = Color.White,

                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),

                    maxLines = 2,

                    overflow = TextOverflow.Ellipsis
                )
            }

            Surface(

                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(
                        end = 12.dp,
                        bottom = 10.dp
                    ),

                shape = RoundedCornerShape(50),

                color = Color.White
            ) {

                Text(

                    text = "Read now",

                    color = darkCardColor,

                    style = MaterialTheme.typography.buttonLinkSmall,

                    modifier = Modifier.padding(
                        horizontal = 18.dp,
                        vertical = 10.dp
                    )
                )
            }
        }
    }
}
//@Composable
//fun ArticleCard(
//    article: BosspediaArticle,
//    onClick: () -> Unit
//) {
//    val darkCardColor = Color(0xFF141720)
//    val gradientStart = Color(0xFF2E90FA)
//    val gradientEnd = Color(0xFFCBF4DF)
//
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(100.dp)
//            .clip(RoundedCornerShape(16.dp))
//            .background(darkCardColor)
//            .clickable { onClick() }
//    ) {
//        // 1. Wavy Background
//        Canvas(modifier = Modifier.matchParentSize()) {
//            val width = size.width
//            val height = size.height
//
//            val path = Path().apply {
//                moveTo(width * 0.78f, 0f)
//                cubicTo(
//                    x1 = width * 0.68f, y1 = height * 0.3f,
//                    x2 = width * 0.88f, y2 = height * 0.7f,
//                    x3 = width * 0.78f, y3 = height
//                )
//                lineTo(width, height)
//                lineTo(width, 0f)
//                close()
//            }
//
//            drawPath(
//                path = path,
//                brush = Brush.linearGradient(
//                    colors = listOf(gradientStart, gradientEnd),
//                    start = Offset(width * 0.7f, 0f),
//                    end = Offset(width, height)
//                )
//            )
//        }
//
//        // 2. Text Column (FIXED LAYOUT)
//        Column(
//            modifier = Modifier
//                .align(Alignment.CenterStart)
//                // The crucial fix: hard padding on the right creates a safe zone for the button
//                .padding(start = 16.dp, end = 110.dp, top = 16.dp, bottom = 16.dp)
//                .fillMaxHeight(),
//            verticalArrangement = Arrangement.Center
//        )
//        {
//            Text(
//                text = article.readTime,
//                color = AppColors.TextSecondary,
//                style = MaterialTheme.typography.labelSmall,
//            )
//            Spacer(modifier = Modifier.height(4.dp))
//            Text(
//                text = article.title,
//                color = Color.White,
//                // Restored your previous typography mapping!
//                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
//                maxLines = 2,
//                overflow = TextOverflow.Ellipsis
//            )
//        }
//
//        // 3. Read Now Button
//        Surface(
//            modifier = Modifier
//                .align(Alignment.BottomEnd)
//                .padding(end = 12.dp, bottom = 10.dp),
//
//            shape = RoundedCornerShape(50),
//            color = Color.White
//        ) {
//            Text(
//                text = "Read now",
//                color = darkCardColor,
//                style =  MaterialTheme.typography.buttonLinkSmall,
//
//                modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
//            )
//        }
//    }
//}

@Preview(
    showBackground = true,
    widthDp = 900
)
@Composable
fun ArticlesRowPreview() {

    MaterialTheme {

        LazyRow(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(5) {
                ArticleCard(
                    article = HomeDummyData.bosspediaArticles[0],
                    onClick = {}
                )
            }
        }
    }
}