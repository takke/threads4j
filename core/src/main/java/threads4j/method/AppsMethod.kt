package threads4j.method

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import threads4j.auth.AccessToken
import threads4j.Parameter
import threads4j.ThreadsRequest
import threads4j.ThreadsClient
import threads4j.Scope

/**
 * see more https://developers.facebook.com/docs/threads/get-started/get-access-tokens-and-permissions
 */
class AppsMethod(private val client: ThreadsClient) {

    // GET https://threads.net/oauth/authorize
    fun getOAuthUrl(clientId: String, scope: Scope, redirectUri: String): String {
        val endpoint = "/oauth/authorize"
        val parameters = listOf(
            "client_id=$clientId",
            "redirect_uri=$redirectUri",
            "scope=$scope",
            "response_type=code",
        ).joinToString(separator = "&")
        return "https://threads.net$endpoint?$parameters"
    }

    // POST https://graph.threads.net/oauth/access_token
    fun getAccessToken(
        clientId: String,
        clientSecret: String,
        code: String,
        redirectUri: String,
    ): ThreadsRequest<AccessToken> {
        val parameters = Parameter().apply {
            append("client_id", clientId)
            append("client_secret", clientSecret)
            append("code", code)
            append("grant_type", "authorization_code")
            append("redirect_uri", redirectUri)
        }.build()

        return ThreadsRequest(
            {
                client.post(
                    "/oauth/access_token",
                    parameters
                        .toRequestBody("application/x-www-form-urlencoded; charset=utf-8".toMediaTypeOrNull())
                )
            },
            {
                client.getSerializer().fromJson(it, AccessToken::class.java)
            }
        )
    }
}
