package com.ngr.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Source(
    var id: String,
    var name: String,
) : Parcelable
