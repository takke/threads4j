package jp.takke.threads4j.sample

import java.io.FileInputStream
import java.io.IOException
import java.util.Properties

object SampleUtil {

    fun loadProperties(): Properties {
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