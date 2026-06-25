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

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.policyboss.customer.feature.home.dummy.HomeDummyData
import com.policyboss.customer.feature.home.model.bossepidia.BosspediaArticle
import com.policyboss.customer.feature.home.model.bossepidia.BosspediaStory
import com.policyboss.customer.ui.theme.AppColors
import com.policyboss.customer.ui.theme.PolicyBossCustomerTheme

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.res.painterResource
import com.policyboss.customer.R
import com.policyboss.customer.ui.theme.bodyMediumSemiBold
import com.policyboss.customer.ui.theme.labelMediumSemiBold


@Composable
fun BosspediaSection(
    modifier: Modifier = Modifier,
    stories: List<BosspediaStory>,    // <-- strictly require data
    articles: List<BosspediaArticle>, // <-- strictly require data
    onExploreMoreClick: () -> Unit,
    onStoryClick: (String) -> Unit,   // <-- Added story click
    onArticleClick: (String) -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        // --- HEADER ---
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "BOSSpedia",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )

            Row(

                modifier = Modifier
                    .clickable {
                        onExploreMoreClick()
                    }
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Text(

                    text = "Explore more",

                    style = MaterialTheme.typography.labelLarge,



                    color = AppColors.BluePrimary,

                    modifier = Modifier.padding(4.dp)
                )
                Spacer(

                    modifier = Modifier.width(2.dp)
                )

                Icon(

                    painter = painterResource(
                        id = R.drawable.ic_chevron_right
                    ),

                    contentDescription = null,

                    tint = AppColors.BluePrimary,

                    modifier = Modifier.size(20.dp)
                )
            }
//            Text(
//                text = "Explore more >",
//                color = AppColors.BluePrimary,
//                style = MaterialTheme.typography.labelLarge,
//
//
//                text = "View All",
//
//
//
//
//                color = AppColors.BluePrimary,
//
//
//
//                modifier = Modifier
//                    .clip(RoundedCornerShape(4.dp))
//                    .clickable { onExploreMoreClick() }
//                    .padding(4.dp)
//            )
        }

      

        Spacer(modifier = Modifier.height(16.dp))

        // --- STORIES (Horizontal Scroll) ---
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(stories, key = { it.id }) { story ->
                StoryItem(
                    story = story,
                    onClick = { onStoryClick(story.id) }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // --- ARTICLES CONTAINER (Vertical List) ---
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .background(AppColors.CardBackground)
                .padding(24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "ARTICLES",
                    style = MaterialTheme.typography.bodyMediumSemiBold,
                    color = AppColors.TextSecondary
                )


                Row(

                    modifier = Modifier
                        .clickable {
                            onExploreMoreClick()
                        }
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Text(

                        text = "View All",

                        style = MaterialTheme.typography.labelMediumSemiBold,




                        modifier = Modifier.padding(4.dp)
                    )
                    Spacer(

                        modifier = Modifier.width(2.dp)
                    )

                    Icon(

                        painter = painterResource(
                            id = R.drawable.ic_chevron_right
                        ),

                        contentDescription = null,

                        tint = AppColors.TextPrimary,

                        modifier = Modifier.size(20.dp)
                    )
                }

            }

            Spacer(modifier = Modifier.height(16.dp))

            // --- VERTICAL ARTICLES LIST ---
            // Use take(3) to ensure we only show a max of 3 on the home screen
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                articles.take(3).forEach { article ->
                    ArticleCard(
                        article = article,
                        onClick = { onArticleClick(article.id) }
                    )
                }
            }
        }
    }
}
@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun BosspediaSectionPreview() {

    PolicyBossCustomerTheme {

        Surface(
            modifier = Modifier.fillMaxSize()
        ) {

            BosspediaSection(

                modifier = Modifier.padding(24.dp),

                stories = HomeDummyData.bosspediaStories,

                articles = HomeDummyData.bosspediaArticles,

                onExploreMoreClick = {},

                onArticleClick = {},

                onStoryClick =  {}
            )
        }
    }
}