package jp.takke.threads4j.sample

import threads4j.Scope
import threads4j.ThreadsClient

object GetAccessTokenSample {

    @JvmStatic
    fun main(args: Array<String>) {

        // local.properties から取得する
        val properties = SampleUtil.loadProperties()
        val clientId = properties.getProperty("CLIENT_ID")
        val clientSecret = properties.getProperty("CLIENT_SECRET")
        val redirectUri = properties.getProperty("REDIRECT_URI")

        println("Open the following URL in your browser:")

        val client = ThreadsClient.Builder().build()
        val oauthUrl = client.apps.getOAuthUrl(
            clientId, Scope(Scope.Name.BASIC, Scope.Name.CONTENT_PUBLISH),
            redirectUri
        )

        println("OAuth URL: $oauthUrl")

        println("Enter the code: ")
        val code = readlnOrNull() ?: return

        // コードは #以降を無視する
        val codeValue = code.substringBefore("#")

        // アクセストークンを取得する
        val accessToken = client.apps.getAccessToken(
            clientId, clientSecret,
            codeValue,
            redirectUri
        ).execute()

        println("Access Token: ${accessToken.value.accessToken}")
        println("User Id: ${accessToken.value.userId}")
    }

}
