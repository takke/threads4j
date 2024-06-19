package threads4j.method

import threads4j.ThreadsClient
import threads4j.Scope

/**
 * see more https://developers.facebook.com/docs/threads/get-started/get-access-tokens-and-permissions
 */
class AppsMethod(private val client: ThreadsClient) {

    fun getOAuthUrl(clientId: String, scope: Scope, redirectUri: String = "urn:ietf:wg:oauth:2.0:oob"): String {
        val endpoint = "/oauth/authorize"
        val parameters = listOf(
            "client_id=$clientId",
            "redirect_uri=$redirectUri",
            "scope=$scope",
            "response_type=code",
        ).joinToString(separator = "&")
        return "${client.baseUrl}$endpoint?$parameters"
    }

//    // POST /oauth/token
//    @JvmOverloads
//    fun getAccessToken(
//        clientId: String,
//        clientSecret: String,
//        redirectUri: String = "urn:ietf:wg:oauth:2.0:oob",
//        code: String,
//        grantType: String = "authorization_code",
//        scope: Scope = Scope(Scope.Name.ALL)
//    ): MastodonRequest<AccessToken> {
//        val parameters = Parameter().apply {
//            append("client_id", clientId)
//            append("client_secret", clientSecret)
//            append("scope", scope.toString())
//            append("redirect_uri", redirectUri)
//            append("code", code)
//            append("grant_type", grantType)
//        }.build()
//
//        return MastodonRequest(
//            {
//                client.post(
//                    "/oauth/token",
//                    parameters
//                        .toRequestBody("application/x-www-form-urlencoded; charset=utf-8".toMediaTypeOrNull())
//                )
//            },
//            {
//                client.getSerializer().fromJson(it, AccessToken::class.java)
//            }
//        )
//    }

//    // POST /oauth/token
//    fun postUserNameAndPassword(
//        clientId: String,
//        clientSecret: String,
//        scope: Scope,
//        userName: String,
//        password: String
//    ): MastodonRequest<AccessToken> {
//        val parameters = Parameter().apply {
//            append("client_id", clientId)
//            append("client_secret", clientSecret)
//            append("scope", scope.toString())
//            append("username", userName)
//            append("password", password)
//            append("grant_type", "password")
//        }.build()
//
//        return MastodonRequest(
//            {
//                client.post(
//                    "/oauth/token",
//                    parameters
//                        .toRequestBody("application/x-www-form-urlencoded; charset=utf-8".toMediaTypeOrNull())
//                )
//            },
//            {
//                client.getSerializer().fromJson(it, AccessToken::class.java)
//            }
//        )
//    }
}
