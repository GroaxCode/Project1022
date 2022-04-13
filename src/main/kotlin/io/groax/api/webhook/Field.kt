package io.groax.api.webhook
import lombok.*

@Getter
@Setter
class Field(
     val name: String,
     val value: String,
     val isInline: Boolean
)