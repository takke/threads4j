package jp.takke.threads4j.sample

import threads4j.ThreadsClient

object PublishSample {

    @JvmStatic
    fun main(args: Array<String>) {

        // local.properties から取得する
        val properties = SampleUtil.loadProperties()
        val accessToken = properties.getProperty("ACCESS_TOKEN")
        val userId = properties.getProperty("USER_ID").toLong()


        println("Publishing(1/2)...")

        val client = ThreadsClient.Builder().build()
        val result = client.publishing.postThreads(
            accessToken,
            userId.toString(),
            "TEXT",
            text = "Post from threads4j!"
        ).execute()

        val threadsMediaContainerId = result.value.id
        println("Threads Media Container ID: $threadsMediaContainerId")

//        println("headers: ${result.headers}")
//        exitProcess(0)


        println("Publishing(2/2)...")

        val result2 = client.publishing.postThreadsPublish(
            accessToken,
            userId.toString(),
            threadsMediaContainerId
        ).executeAndGetValue()

        // Threads Media ID
        val threadsMediaId = result2.id
        println("Threads Media ID: $threadsMediaId")


        println("Done.")
    }

}
