package threads4j

interface ThreadsResponse<T> {

    val value: T

    val code: Int

    val headers: Map<String, String>

    val rateLimit: RateLimit?
}
