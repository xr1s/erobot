package me.xr1s.erobot.models.danbooru

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
enum class Rating {
  @SerialName("s") Safe,
  @SerialName("q") Questionable,
  @SerialName("e") Explicit,
}
