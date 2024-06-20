package threads4j.model

import com.google.gson.annotations.SerializedName

data class ThreadsPublishingLimit(
    @SerializedName("data")
    val data: List<Data>
) {
    data class Data(
        @SerializedName("quota_usage")
        val quotaUsage: Int,
        @SerializedName("config")
        val config: Config,
        @SerializedName("reply_quota_usage")
        val replyQuotaUsage: Int,
        @SerializedName("reply_config")
        val replyConfig: Config
    )

    data class Config(
        @SerializedName("quota_total")
        val quotaTotal: Int,
        @SerializedName("quota_duration")
        val quotaDuration: Int
    )
}
