package threads4j.model

import com.google.gson.annotations.SerializedName

data class ThreadsUser(
    @SerializedName("id")
    val id: String,
    @SerializedName("username")
    val username: String? = null,
    @SerializedName("threads_profile_picture_url")
    val threadsProfilePictureUrl: String? = null,
    @SerializedName("threads_biography")
    val threadsBiography: String? = null
)
