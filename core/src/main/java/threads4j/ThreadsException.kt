package threads4j

import okhttp3.HttpUrl
import okhttp3.Protocol
import okhttp3.Response

class ThreadsException : Exception {

    val requestUrl: HttpUrl?
    val protocol: Protocol?
    val headers: Map<String, String>?
//    val rateLimit: RateLimit?

    // Http Status
    val code: Int

    // Http Status Message
    val statusMessage: String?

    // Http Response Body
    val responseBody: String?

    constructor(response: Response) : super(response.message) {

        this.requestUrl = response.request.url
        this.protocol = response.protocol
        this.headers = response.headers.toMap()
//        this.rateLimit = ThreadsResponseImpl.collectRateLimit(headers)
        this.code = response.code
        this.statusMessage = response.message
        this.responseBody = response.body?.string()
    }

    constructor(e: Exception) : super(e) {
        this.requestUrl = null
        this.protocol = null
        this.headers = null
//        this.rateLimit = null
        this.code = 0
        this.statusMessage = null
        this.responseBody = null
    }

    constructor(message: String) : super(message) {
        this.requestUrl = null
        this.protocol = null
        this.headers = null
//        this.rateLimit = null
        this.code = 0
        this.statusMessage = null
        this.responseBody = null
    }

    fun isErrorResponse() = responseBody != null

    fun resourceNotFound(): Boolean {
        return code == 404
    }

    override fun toString(): String {
        val responseBody = responseBody ?: ""
        return "${responseBody}\n" + additionalErrorInfo
    }

    val additionalErrorInfo: String get() = "code=${code}, url=${requestUrl} protocol=${protocol}, message=${statusMessage}"
}
