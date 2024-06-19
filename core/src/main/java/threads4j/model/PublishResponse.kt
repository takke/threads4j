package threads4j.model

import com.google.gson.annotations.SerializedName


data class PublishResponse(
    // Threads Media Container ID
    @SerializedName("id")
    var id: String = "",
)
