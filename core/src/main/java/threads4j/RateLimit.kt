package threads4j

data class RateLimit(
    // X-RateLimit-Limit: Number of requests permitted per time period
    val limit: Long,
    // X-RateLimit-Remaining: Number of requests you can still make
    val remaining: Long,
    // X-RateLimit-Reset: Timestamp when your rate limit will reset
    // 2023-09-22T13:45:00.694552Z
    val reset: String
)
