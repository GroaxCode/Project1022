package io.groax.api.webhook

import java.awt.Color


class EmbedObject {
    var title: String? = null
        private set
    var description: String? = null
        private set
    var url: String? = null
        private set
    var color: Color? = null
    var footer: Footer? = null
        private set
    var thumbnail: Thumbnail? = null
        private set
    var image: Image? = null
        private set
    var author: Author? = null
        private set
     val fields: MutableList<Field> = ArrayList()


    fun setTitle(title: String?): EmbedObject {
        this.title = title
        return this
    }

    fun setDescription(description: String?): EmbedObject {
        this.description = description
        return this
    }

    fun setUrl(url: String?): EmbedObject {
        this.url = url
        return this
    }

    fun setColor(color: Color?): EmbedObject {
        this.color = color
        return this
    }

    fun setFooter(text: String?, icon: String?): EmbedObject {
        footer = Footer(text!!, icon!!)
        return this
    }

    fun setThumbnail(url: String?): EmbedObject {
        thumbnail = Thumbnail(url!!)
        return this
    }

    fun setImage(url: String?): EmbedObject {
        image = Image(url!!)
        return this
    }

    fun setAuthor(name: String?, url: String?, icon: String?): EmbedObject {
        author = Author(name!!, url!!, icon!!)
        return this
    }

    fun addField(name: String?, value: String?, inline: Boolean): EmbedObject {
        if (value == null) {
            return this
        }
        fields.add(Field(name!!, value, inline))
        return this
    }
}