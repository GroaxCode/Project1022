package io.groax.api.webhook

import java.lang.reflect.Array.getLength


internal class JSONObject {
    private val map = HashMap<String, Any>()
    fun put(key: String, value: Any?) {
        if (value != null) {
            map[key] = value
        }
    }

    override fun toString(): String {
        val builder = StringBuilder()
        val entrySet: Set<Map.Entry<String, Any>> = map.entries
        builder.append("{")
        var i = 0
        for (entry: Map.Entry<String?, Any> in entrySet) {
            val key = entry.key
            val val0 = entry.value
            builder.append(quote(key!!)).append(":")
            if (val0 is String) {
                builder.append(quote(val0.toString()))
            } else if (val0 is Int) {
                builder.append(Integer.valueOf(val0.toString()))
            } else if (val0 is Boolean) {
                builder.append(val0)
            } else if (val0 is JSONObject) {
                builder.append(val0.toString())
            } else if (val0.javaClass.isArray) {
                builder.append("[")
                val len: Int = getLength(val0)
                for (j in 0 until len) {
                    builder.append(java.lang.reflect.Array.get(val0, j).toString()).append(if (j != len - 1) "," else "")
                }
                builder.append("]")
            }
            builder.append(if (++i == entrySet.size) "}" else ",")
        }

        return builder.toString()
    }

    private fun quote(string: String): String {
        return "\"" + string + "\""
    }
}