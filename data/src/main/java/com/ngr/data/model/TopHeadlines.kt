package com.ngr.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TopHeadlines (
    var status: String,
    var totalResults: Int? = null,
    var articles: List<Article> = arrayListOf()
) : Parcelable
