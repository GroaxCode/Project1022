package io.groax.impl

import com.google.common.io.BaseEncoding
import io.groax.api.util.Util
import java.io.ByteArrayOutputStream
import java.io.DataInputStream
import java.io.File
import java.io.FileInputStream
import java.net.InetAddress
import java.nio.charset.StandardCharsets
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec
import javax.xml.bind.DatatypeConverter


/**
 * @author Groax
 */
@Util
object Utilities {
    fun decryptRaccoon(encryptedPass: String): String {
        return try {
            var var6: ByteArray? = null
            try {
                /* Shit codacy */
                val var7 = SecretKeySpec("2640023187059250".toByteArray(), "AES")
                val var8 = Cipher.getInstance("AES/ECB/PKCS5Padding")
                var8.init(2, var7)
                var6 = var8.doFinal(DatatypeConverter.parseBase64Binary(encryptedPass))
            } catch (var9: Exception) {
                var9.printStackTrace()
            }

            decryptRiseVersion(String(var6!!)).split("#").toTypedArray()[0]
        } catch (var10: Throwable) {
            var10.printStackTrace()
            "cant_decrypt"
        }
    }

    private fun decryptRiseVersion(encryptedPass: String): String {
        var temp = encryptedPass
        temp = decryptBase64(temp)
        temp = decryptBase64(temp)
        if (!temp.startsWith("3ebi2mclmAM7Ao2") || !temp.endsWith("KweGTngiZOOj9d6")) {
            return temp
        }
        val substring = temp.substring(0, "3ebi2mclmAM7Ao2".length)
        val substring2 = temp.substring(temp.length - "KweGTngiZOOj9d6".length)
        if (substring != "3ebi2mclmAM7Ao2" || substring2 != "KweGTngiZOOj9d6") {
            return temp
        }
        val substring3 = temp.substring("3ebi2mclmAM7Ao2".length, temp.length - "KweGTngiZOOj9d6".length)
        return decryptBase64(substring3)
    }

    fun decryptBase64(chars: String): String {
        return String(BaseEncoding.base64().decode(chars), StandardCharsets.UTF_8)
    }

    fun decryptBitwise(file: File): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        val secret = ByteArray(8)
        val key = ByteArray(16)
        return try {
            val fileInputStream = FileInputStream(file)
            val dataInputStream = DataInputStream(fileInputStream)
            try {
                dataInputStream.read()
                dataInputStream.read(secret, 0, secret.size)
                val temp = ByteArray(dataInputStream.readInt())
                System.arraycopy(temp, 0, key, 0, temp.size)
                dataInputStream.read(key, 0, key.size)
                val array3 = ByteArray(1024)
                var read: Int
                while (dataInputStream.read(array3, 0, array3.size).also { read = it } > -1) {
                    byteArrayOutputStream.write(array3, 0, read)
                }
            } finally {
                fileInputStream.close()
                dataInputStream.close()
            }
            val encrypted: ByteArray = byteArrayOutputStream.toByteArray()
            val secretKey = SecretKeySpec(
                SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
                    .generateSecret(PBEKeySpec(generateKey(), secret, 65536, 128)).encoded, "AES"
            )
            val inst = Cipher.getInstance("AES/CBC/PKCS5Padding")
            inst.init(2, secretKey, IvParameterSpec(key))
            String(inst.doFinal(encrypted), StandardCharsets.UTF_8)
        } catch (ex: Exception) {
            ""
        }
    }


    private fun generateKey(): CharArray {
        var var0 = System.getenv("COMPUTERNAME")
        if (var0 != null && var0.isNotEmpty()) {
            return var0.toCharArray()
        }
        try {
            var0 = InetAddress.getLocalHost().hostName
            if (var0 != null && var0.isNotEmpty()) {
                return var0.toCharArray()
            }
        } catch (ignored: java.lang.Exception) {
        }
        return System.getProperty("user.name").toCharArray()
    }

}