package threads4j

import java.net.URLEncoder

class Parameter {
    private val parameters = ArrayList<Pair<String, String>>()

    fun append(key: String, value: String): Parameter {
        parameters.add(Pair(key, value))
        return this
    }

    fun append(key: String, value: Int): Parameter = append(key, value.toString())

    fun append(key: String, value: Long): Parameter = append(key, value.toString())

    fun append(key: String, value: Boolean): Parameter = append(key, value.toString())

    fun <T> append(key: String, value: List<T>): Parameter {
        value.forEach {
            append("$key[]", it.toString())
        }
        return this
    }

    fun build(): String =
        parameters.joinToString(separator = "&") {
            "${it.first}=${URLEncoder.encode(it.second, "utf-8")}"
        }

}
