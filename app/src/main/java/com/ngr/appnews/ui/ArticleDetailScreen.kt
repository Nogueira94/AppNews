package com.ngr.appnews.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ngr.data.model.Article
import com.ngr.data.model.Source
import com.ngr.designsystem.theme.AppNewsTheme

@Composable
fun ArticleDetailScreen(
   article: Article,
) {
   Column(
      modifier = Modifier
         .fillMaxWidth()
         .verticalScroll(rememberScrollState()),
      horizontalAlignment = Alignment.CenterHorizontally
   ) {
      AsyncImage(
         model = ImageRequest.Builder(LocalContext.current)
            .data(article.urlToImage)
            .crossfade(true)
            .build(),
         contentScale = ContentScale.FillBounds,
         contentDescription = null,
         modifier = Modifier
            .wrapContentWidth()
            .height(250.dp)
            .clip(shape = RoundedCornerShape(15.dp))
      )
      Spacer(modifier = Modifier.size(8.dp))
      Text(
         modifier = Modifier
            .fillMaxWidth(),
         text = article.title,
         style = MaterialTheme.typography.titleLarge,
         color = Color.White
      )
      Spacer(modifier = Modifier.size(16.dp))
      Text(
         modifier = Modifier
            .fillMaxWidth(),
         text = article.description,
         style = MaterialTheme.typography.bodyMedium,
         color = Color.White
      )
      Spacer(modifier = Modifier.size(16.dp))
      Text(
         modifier = Modifier
            .fillMaxWidth(),
         text = article.content,
         style = MaterialTheme.typography.labelLarge,
         color = Color.White
      )
   }
}

@Preview
@Composable
private fun ArticleDetailScreenPreview(){
   AppNewsTheme {
      ArticleDetailScreen(article = Article(
         source = Source("", ""),
         title = "Article Preview",
         author = "Author Preview",
         description = "Description Preview",
         url = "Url Preview",
         content = "Content Preview",
         publishedAt = "Date Preview",
         urlToImage = "Url Preview"
      ))
   }
}