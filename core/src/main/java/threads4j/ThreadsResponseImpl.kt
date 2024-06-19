package threads4j

import okhttp3.Response

class ThreadsResponseImpl<T>(
    override val value: T,
) : ThreadsResponse<T> {

    override var code: Int = -1

    override var headers: Map<String, String> = emptyMap()

    fun collectResponse(response: Response) {
        this.code = response.code
        this.headers = response.headers.toMap()
    }
}
