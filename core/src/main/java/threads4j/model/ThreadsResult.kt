package threads4j.model

import com.google.gson.annotations.SerializedName

data class ThreadsResult(
    @SerializedName("data")
    val data: List<Thread>,
    @SerializedName("paging")
    val paging: Paging
)

data class Paging(
    @SerializedName("cursors")
    val cursors: Cursors
)

data class Cursors(
    @SerializedName("before")
    val before: String,
    @SerializedName("after")
    val after: String
)

data class Thread(
    @SerializedName("id")
    val id: String,
    @SerializedName("media_product_type")
    val mediaProductType: String,
    @SerializedName("media_type")
    val mediaType: String,
    @SerializedName("media_url")
    val mediaUrl: String?,
    @SerializedName("permalink")
    val permalink: String,
    @SerializedName("owner")
    val owner: Owner,
    @SerializedName("username")
    val username: String,
    @SerializedName("text")
    val text: String,
    @SerializedName("timestamp")
    val timestamp: String,
    @SerializedName("shortcode")
    val shortcode: String,
    // thumbnail_url
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String,
    @SerializedName("is_quote_post")
    val isQuotePost: Boolean,
)

data class Owner(
    @SerializedName("id")
    val id: String
)