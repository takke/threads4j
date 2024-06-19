package jp.takke.threads4j.sample

import threads4j.Scope
import threads4j.ThreadsClient
import java.io.FileInputStream
import java.io.IOException
import java.util.Properties

object Threads4jSample {

    @JvmStatic
    fun main(args: Array<String>) {

        // local.properties から取得する
        val properties = loadProperties()
        val clientId = properties.getProperty("CLIENT_ID")
        val clientSecret = properties.getProperty("CLIENT_SECRET")
        val redirectUri = properties.getProperty("REDIRECT_URI")

        println("Open the following URL in your browser:")

        val client = ThreadsClient.Builder().build()
        val oauthUrl = client.apps.getOAuthUrl(
            clientId, Scope(Scope.Name.BASIC),
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

        println("Access Token: ${accessToken.value}")
    }

    private fun loadProperties(): Properties {
        val propertiesFilePath = "local.properties"
        val properties = Properties()
        try {
            FileInputStream(propertiesFilePath).use { inputStream ->
                properties.load(inputStream)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return properties
    }

}
