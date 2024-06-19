package threads4j.auth

import com.google.gson.annotations.SerializedName


data class AccessToken(
    @SerializedName("access_token")
    var accessToken: String = "",

    @SerializedName("user_id")
    var userId: Long = 0L
)
