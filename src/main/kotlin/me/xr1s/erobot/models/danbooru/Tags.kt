package me.xr1s.erobot.models.danbooru

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object TagsSerializer : KSerializer<Set<String>> {
  override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Tags", PrimitiveKind.STRING)
  override fun serialize(encoder: Encoder, value: Set<String>) {
    encoder.encodeString(value.toTypedArray().joinToString(" "))
  }
  override fun deserialize(decoder: Decoder): Set<String> {
    val string = decoder.decodeString()
    return string.split(" ").toSet()
  }
}
