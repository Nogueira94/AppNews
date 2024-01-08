package com.ngr.appnews.navigation.navtypes

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.ngr.data.model.Article
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ArticleNavType : NavType<Article>(isNullableAllowed = false) {

    override fun get(bundle: Bundle, key: String): Article? =
        bundle.getParcelable(key)

    override fun parseValue(value: String): Article {
        return Json.decodeFromString(value)
    }

    override fun put(bundle: Bundle, key: String, value: Article) =
        bundle.putParcelable(key,value)

    companion object{
        fun encodeToString(article: Article) : String{
            return Uri.encode(Json.encodeToString(article))
        }
    }

}