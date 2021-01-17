package me.xr1s.erobot.models

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object DateTimeSerializer : KSerializer<ZonedDateTime> {
  override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("DateTime", PrimitiveKind.STRING)
  override fun serialize(encoder: Encoder, value: ZonedDateTime) {
    encoder.encodeString(value.format(DateTimeFormatter.ISO_ZONED_DATE_TIME))
  }
  override fun deserialize(decoder: Decoder): ZonedDateTime {
    return ZonedDateTime.parse(decoder.decodeString(), DateTimeFormatter.ISO_ZONED_DATE_TIME)
  }
}
