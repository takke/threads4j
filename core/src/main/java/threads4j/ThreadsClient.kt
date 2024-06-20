package threads4j

import com.google.gson.Gson
import okhttp3.*
import threads4j.method.AppsMethod
import threads4j.method.PublishingMethod
import threads4j.method.UserMethod
import java.io.IOException


class ThreadsClient private constructor(
    private val client: OkHttpClient,
    private val gson: Gson
) {

    class Builder(
        private val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder(),
        private val gson: Gson = Gson()
    ) {
        private var accessToken: String? = null
        private var debug = false

        fun accessToken(accessToken: String) = apply {
            this.accessToken = accessToken
        }

        fun debug() = apply {
            this.debug = true
        }

        fun build(): ThreadsClient {
            return ThreadsClient(
//                okHttpClientBuilder.addNetworkInterceptor(AuthorizationInterceptor(accessToken)).build(),
                okHttpClientBuilder.build(),
                gson
            ).also {
                it.debug = debug
            }
        }
    }

    private var debug = false

    fun debugPrint(log: String) {
        if (debug) {
            println(log)
        }
    }

//    private class AuthorizationInterceptor(val accessToken: String? = null) : Interceptor {
//        @Throws(IOException::class)
//        override fun intercept(chain: Interceptor.Chain): Response {
//            val originalRequest = chain.request()
//            val compressedRequest = originalRequest.newBuilder()
//                .headers(originalRequest.headers)
//                .method(originalRequest.method, originalRequest.body)
//                .apply {
//                    accessToken?.let {
//                        header("Authorization", String.format("Bearer %s", it))
//                    }
//                }
//                .build()
//            return chain.proceed(compressedRequest)
//        }
//    }

    private val baseUrl = "https://graph.threads.net"

    fun getSerializer() = gson

    fun get(path: String, parameter: Parameter? = null): Response {
        try {
            val url = "$baseUrl$path"
            debugPrint(url)
            val urlWithParams = parameter?.let {
                "$url?${it.build()}"
            } ?: url
            val call = client.newCall(
                Request.Builder()
                    .url(urlWithParams)
                    .get()
                    .build()
            )
            return call.execute()
        } catch (e: IOException) {
            throw ThreadsException(e)
        }
    }

    fun post(path: String, body: RequestBody, headers: Map<String, String>? = null): Response {
        try {
            val url = "$baseUrl$path"
            debugPrint(url)
            val call = client.newCall(
                Request.Builder()
                    .url(url)
                    .post(body)
                    .apply {
                        headers?.forEach { (key, value) ->
                            header(key, value)
                        }
                    }
                    .build()
            )
            return call.execute()
        } catch (e: IllegalArgumentException) {
            throw ThreadsException(e)
        } catch (e: IOException) {
            throw ThreadsException(e)
        }
    }

//    fun put(path: String, body: RequestBody): Response {
//        try {
//            val url = "$baseUrl$path"
//            debugPrint(url)
//            val call = client.newCall(
//                Request.Builder()
//                    .url(url)
//                    .put(body)
//                    .build()
//            )
//            return call.execute()
//        } catch (e: IllegalArgumentException) {
//            throw ThreadsException(e)
//        } catch (e: IOException) {
//            throw ThreadsException(e)
//        }
//    }
//
//    fun patch(path: String, body: RequestBody): Response {
//        try {
//            val url = "$baseUrl$path"
//            debugPrint(url)
//            val call = client.newCall(
//                Request.Builder()
//                    .url(url)
//                    .patch(body)
//                    .build()
//            )
//            return call.execute()
//        } catch (e: IOException) {
//            throw ThreadsException(e)
//        }
//    }
//
//    fun delete(path: String, body: RequestBody? = null): Response {
//        try {
//            val url = "$baseUrl$path"
//            debugPrint(url)
//            val call = client.newCall(
//                Request.Builder()
//                    .url(url)
//                    .delete(body)
//                    .build()
//            )
//            return call.execute()
//        } catch (e: IOException) {
//            throw ThreadsException(e)
//        }
//    }

    //--------------------------------------------------
    // Methods
    //--------------------------------------------------

    val apps get() = AppsMethod(this)
    val publishing get() = PublishingMethod(this)
    val user get() = UserMethod(this)

}
