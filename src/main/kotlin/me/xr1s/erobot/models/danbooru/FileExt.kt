package me.xr1s.erobot.models.danbooru

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

// only supports JPEG, GIF, PNG, SWF, MP4, and ZIP (Pixiv Ugoira)
// see https://github.com/danbooru/danbooru/blob/master/app/logical/media_file.rb#L25
@Serializable
enum class FileExt {
  @SerialName("jpg") JPEG,
  @SerialName("gif") GIF,
  @SerialName("png") PNG,
  @SerialName("swf") SWF,
  @SerialName("webm") WebM,
  @SerialName("mp4") MP4,
  @SerialName("zip") ZIP,
}
