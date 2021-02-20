package com.tejuspicsum_app.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ImageResponse : Serializable {

    var format: String? = null
    var width: Int? = null
    var height: Int? = null
    var filename: String? = null
    var id: Int? = null
    var author: String? = null

    @SerializedName("author_url")
    var authorUrl: String? = null

    @SerializedName("post_url")
    var postUrl: String? = null
}