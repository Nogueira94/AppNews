package com.ngr.designsystem.component

import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ngr.common.utils.getFormattedDate
import com.ngr.data.model.Article
import com.ngr.data.model.Source
import com.ngr.designsystem.R
import com.ngr.designsystem.theme.AppNewsTheme

@Composable
fun TopHeadlinesItem(
    modifier: Modifier = Modifier,
    article: Article
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(15.dp))
            .background(color = Color.White),
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(article.urlToImage)
                .crossfade(true)
                .build(),
            contentScale = ContentScale.Crop,
            contentDescription = "Example image",
            modifier = Modifier
                .padding(16.dp)
                .width(100.dp)
                .height(100.dp)
                .align(Alignment.CenterVertically)
                .clip(shape = RoundedCornerShape(15.dp))

        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically)
                .padding(end = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = article.title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Spacer(modifier = Modifier.size(6.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Text(
                    text = article.author,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = getFormattedDate(article.publishedAt),
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}

@Preview
@Composable
internal fun TopHeadlinesItemPreview() {
    AppNewsTheme {
        TopHeadlinesItem(
            article = Article(
                source = Source(
                    "",
                    "9to5google.com"
                ),
                author = "Ben Schoon",
                title = "Galaxy S24 launch date confirmed in social post - 9to5Google",
                description = "An official Samsung account has just confirmed the launch date of the Galaxy S24 series and the event’s very heavy...",
                url = "https://9to5google.com/2024/01/02/samsung-galaxy-s24-launch-date-post/",
                urlToImage = "https://i0.wp.com/9to5google.com/wp-content/uploads/sites/4/2023/09/galaxy-s24-leak-2.jpg?resize=1200%2C628&quality=82&strip=all&ssl=1",
                publishedAt = "2024-01-02T19:31:00Z",
                content = "An official Samsung account has just confirmed the launch date of the Galaxy S24 series and the event’s very heavy focus on “Galaxy AI.”\r\nSamsung’s Twitter/X account in Australia has posted a brief v… [+811 chars]"
            )
        )
    }
}