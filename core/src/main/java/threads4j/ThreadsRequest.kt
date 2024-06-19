package threads4j

import com.google.gson.JsonParser
import okhttp3.Response

open class ThreadsRequest<T>(
    private val executor: () -> Response,
    private val mapper: (String) -> Any
) {
    interface Action1<T> {
        fun invoke(arg1: T, arg2: Any)
    }

    private var action: (json: String, value: Any) -> Unit = { _, _ -> }
    private var arrayAction: ((jsonArray: String) -> Unit)? = null

//    private var isPageable: Boolean = false

//    internal fun toPageable() = apply {
//        isPageable = true
//    }

    fun doOnJson(action: (json: String, value: Any) -> Unit) = apply {
        this.action = action
    }

    fun doOnJsonArray(arrayAction: (jsonArray: String) -> Unit) = apply {
        this.arrayAction = arrayAction
    }

    fun doOnJson(action: Action1<String>) = apply {
        this.action = { json, value -> action.invoke(json, value) }
    }

    @Suppress("UNCHECKED_CAST")
    @Throws(ThreadsException::class)
    fun execute(): ThreadsResponse<T> {
//        println("start execute")
        val response = executor()
//        println("response: $response")
        if (response.isSuccessful) {
            try {
                val body = response.body?.string() ?: throw ThreadsException(response)
                val element = JsonParser.parseString(body)

                val result = if (element.isJsonObject) {
//                    println("body: $body")
                    val v = mapper(body)
                    action(body, v)
                    v as T
                } else {
                    val list = arrayListOf<Any>()
                    element.asJsonArray.forEach {
                        val json = it.toString()

//                        println("json: $json")
//                        println("json: ----------------------------------------")
//                        json.chunked(1000).forEach { chunk ->
//                            println("json: $chunk")
//                        }

                        val v = mapper(json)
                        action(json, v)
                        list.add(v)
                    }

                    arrayAction?.invoke(body)

//                    if (isPageable) {
//                        list.toPageable(response) as T
//                    } else {
//                        list as T
//                    }
                    list as T
                }

                return ThreadsResponseImpl(result).also {
                    // collect info
                    it.collectResponse(response)
                }
            } catch (e: Exception) {
                throw ThreadsException(e)
            }
        } else {
            throw ThreadsException(response)
        }
    }

    /**
     * Execute request and return value
     *
     * If you want to get response code, headers or RateLimit, use [execute]
     */
    @Throws(ThreadsException::class)
    fun executeAndGetValue(): T {
        return execute().value
    }

}