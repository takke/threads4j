package threads4j.method

import threads4j.Parameter
import threads4j.ThreadsClient
import threads4j.ThreadsRequest
import threads4j.model.ThreadsPublishingLimit
import threads4j.model.ThreadsResult
import threads4j.model.ThreadsUser

/**
 * see more https://developers.facebook.com/docs/threads/reference/user
 */
class UserMethod(private val client: ThreadsClient) {

    /**
     * GET /{threads-user-id}/threads
     */
    fun getThreads(
        accessToken: String,
        threadsUserId: String,
        fields: List<String>?,
        since: String? = null,
        until: String? = null,
        // 25 to 100
        limit: Int? = null,
        before: String? = null,
        after: String? = null,
    ): ThreadsRequest<ThreadsResult> {
        val parameters = Parameter().apply {
            append("access_token", accessToken)
            fields?.let { append("fields", it.joinToString(",")) }
            since?.let { append("since", it) }
            until?.let { append("until", it) }
            limit?.let { append("limit", it) }
            before?.let { append("before", it) }
            after?.let { append("after", it) }
        }

        return ThreadsRequest(
            {
                client.get("/$threadsUserId/threads", parameters)
            },
            {
                client.getSerializer().fromJson(it, ThreadsResult::class.java)
            }
        )
    }

    /**
     * GET /{threads-user-id}/threads_publishing_limit
     */
    fun getPublishingLimit(
        accessToken: String,
        threadsUserId: String,
        fields: List<String>? = null,
    ): ThreadsRequest<ThreadsPublishingLimit> {
        val parameters = Parameter().apply {
            append("access_token", accessToken)
            fields?.let { append("fields", it.joinToString(",")) }
        }

        return ThreadsRequest(
            {
                client.get("/$threadsUserId/threads_publishing_limit", parameters)
            },
            {
                client.getSerializer().fromJson(it, ThreadsPublishingLimit::class.java)
            }
        )
    }

    /**
     * GET /{threads-user-id}
     */
    fun getUser(
        accessToken: String,
        threadsUserId: String = "me",
        fields: List<String>? = null,
    ): ThreadsRequest<ThreadsUser> {
        val parameters = Parameter().apply {
            append("access_token", accessToken)
            fields?.let { append("fields", it.joinToString(",")) }
        }

        return ThreadsRequest(
            {
                client.get("/$threadsUserId", parameters)
            },
            {
                client.getSerializer().fromJson(it, ThreadsUser::class.java)
            }
        )
    }

}
