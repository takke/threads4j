package threads4j

import okhttp3.Response

class ThreadsResponseImpl<T>(
    override val value: T,
) : ThreadsResponse<T> {

    override var code: Int = -1

    override var headers: Map<String, String> = emptyMap()

    override val rateLimit: RateLimit?
        get() {
            return collectRateLimit(headers)
        }

    fun collectResponse(response: Response) {
        this.code = response.code
        this.headers = response.headers.toMap()
    }

    companion object {

        fun collectRateLimit(headers: Map<String, String>): RateLimit? {
            var limit: Long? = null
            var remaining: Long? = null
            var reset: String? = null

            for (key in headers.keys) {

                when {
                    key.equals("X-RateLimit-Limit", true) -> {
                        limit = headers[key]?.toLongOrNull()
                    }

                    key.equals("X-RateLimit-Remaining", true) -> {
                        remaining = headers[key]?.toLongOrNull()
                    }

                    key.equals("X-RateLimit-Reset", true) -> {
                        // 2023-09-22T13:45:00.694552Z
                        reset = headers[key]
                    }
                }
            }

            return if (limit == null || remaining == null || reset == null) {
                null
            } else {
                RateLimit(limit, remaining, reset)
            }
        }
    }

}
