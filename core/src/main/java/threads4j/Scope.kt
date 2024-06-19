package threads4j

/**
 * see more https://developers.facebook.com/docs/threads/get-started/get-access-tokens-and-permissions
 */
class Scope(private vararg val scopes: Name = arrayOf(Name.ALL)) {
    enum class Name(val scopeName: String) {
        BASIC("threads_basic"),
        CONTENT_PUBLISH("threads_content_publish"),
        READ_REPLIES("threads_read_replies"),
        MANAGE_REPLIES("threads_manage_replies"),
        MANAGE_INSIGHTS("threads_manage_insights"),
        ALL(Scope(BASIC, CONTENT_PUBLISH, READ_REPLIES, MANAGE_REPLIES, MANAGE_INSIGHTS).toString())
    }

    fun validate() {
        if (scopes.size != scopes.distinct().size) {
            throw IllegalArgumentException("There is a duplicate scope. : $this")
        }
    }

    override fun toString(): String {
        return scopes.joinToString(
            // "+" is url encoded " ".
            separator = ",",
            transform = { it.scopeName }
        )
    }
}
