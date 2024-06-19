package threads4j.method

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import threads4j.Parameter
import threads4j.ThreadsClient
import threads4j.ThreadsRequest
import threads4j.model.PublishResponse

/**
 * see more https://developers.facebook.com/docs/threads/reference/publishing
 */
class PublishingMethod(private val client: ThreadsClient) {

    // POST /{threads-user-id}/threads
    fun postThreads(
        accessToken: String,
        threadsUserId: String,
        mediaType: String,
        imageUrl: String? = null,
        videoUrl: String? = null,
        isCarouselItem: Boolean? = null,
//        children: List<String>,
        replyToId: String? = null,
        replyControl: String? = null,
        text: String? = null,
    ): ThreadsRequest<PublishResponse> {
        val parameters = Parameter().apply {
            append("access_token", accessToken)
            append("media_type", mediaType)
            imageUrl?.let { append("image_url", it) }
            videoUrl?.let { append("video_url", it) }
            isCarouselItem?.let { append("is_carousel_item", it) }
//            children.forEach { append("children", it) }
            replyToId?.let { append("reply_to_id", it) }
            replyControl?.let { append("reply_control", it) }
            text?.let { append("text", it) }
        }.build()

        return ThreadsRequest(
            {
                client.post(
                    "/v1.0/$threadsUserId/threads",
                    parameters
                        .toRequestBody("application/x-www-form-urlencoded; charset=utf-8".toMediaTypeOrNull())
                )
            },
            {
                client.getSerializer().fromJson(it, PublishResponse::class.java)
            }
        )
    }

    // POST /{threads-user-id}/threads_publish
    fun postThreadsPublish(
        accessToken: String,
        threadsUserId: String,
        creationId: String
    ): ThreadsRequest<PublishResponse> {
        val parameters = Parameter().apply {
            append("access_token", accessToken)
            append("creation_id", creationId)
        }.build()

        return ThreadsRequest(
            {
                client.post(
                    "/v1.0/$threadsUserId/threads_publish",
                    parameters
                        .toRequestBody("application/x-www-form-urlencoded; charset=utf-8".toMediaTypeOrNull())
                )
            },
            {
                client.getSerializer().fromJson(it, PublishResponse::class.java)
            }
        )
    }

}
