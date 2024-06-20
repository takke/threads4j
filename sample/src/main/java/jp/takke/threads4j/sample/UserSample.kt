package jp.takke.threads4j.sample

import threads4j.ThreadsClient

object UserSample {

    @JvmStatic
    fun main(args: Array<String>) {

        // local.properties から取得する
        val properties = SampleUtil.loadProperties()
        val accessToken = properties.getProperty("ACCESS_TOKEN")
        val userId = properties.getProperty("USER_ID").toLong()


        println("Get Quota...")

        val client = ThreadsClient.Builder().build()
        val result = client.user.getPublishingLimit(
            accessToken,
            userId.toString(),
            listOf("quota_usage", "config", "reply_quota_usage", "reply_config")
        ).doOnJson { json, _ ->
            println("json: $json")
        }.execute()

        println("result: ${result.value}")


        println("Get User...")

        val result2 = client.user.getUser(
            accessToken,
            userId.toString(),
            listOf("id", "username", "threads_profile_picture_url", "threads_biography")
        ).doOnJson { json, _ ->
            println("json: $json")
        }.execute()

        println("result: ${result2.value}")

    }

}
