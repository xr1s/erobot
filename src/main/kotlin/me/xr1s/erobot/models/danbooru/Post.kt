package me.xr1s.erobot.models.danbooru

import java.time.ZonedDateTime
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import me.xr1s.erobot.models.DateTimeSerializer

@Serializable
data class Post(
  val id: Int,
  @Serializable(with = DateTimeSerializer::class)
  @SerialName("created_at")
  val createdAt: ZonedDateTime,
  @SerialName("uploader_id")
  val uploaderId: Int,
  val score: Int,
  val source: String,
  val md5: String,
  @Serializable(with = DateTimeSerializer::class)
  @SerialName("last_comment_bumped_at")
  val lastCommentBumpedAt: ZonedDateTime?,
  val rating: Rating,
  @SerialName("image_width")
  val width: Int,
  @SerialName("image_height")
  val height: Int,
  @Serializable(with = TagsSerializer::class)
  @SerialName("tag_string")
  val tag: Set<String>,
  @SerialName("is_note_locked")
  val isNoteLocked: Boolean,
  @SerialName("fav_count")
  val favourate: Int,
  @SerialName("file_ext")
  val fileExt: FileExt,
  @Serializable(with = DateTimeSerializer::class)
  @SerialName("last_noted_at")
  val lastNotedAt: ZonedDateTime?,
  @SerialName("is_rating_locked")
  val isRatingLocked: Boolean,
  @SerialName("parent_id")
  val parentId: Int?,
  @SerialName("has_children")
  val hasChildren: Boolean,
  @SerialName("approver_id")
  val approverId: Int?,
  @SerialName("tag_count_general")
  val tagCountGeneral: Int,
  @SerialName("tag_count_artist")
  val tagCountArtist: Int,
  @SerialName("tag_count_character")
  val tagCountCharacter: Int,
  @SerialName("tag_count_copyright")
  val tagCountCopyright: Int,
  @SerialName("file_size")
  val fileSize: Int,  // TODO: FileSize
  @SerialName("is_status_locked")
  val isStatusLocked: Boolean,
  @Serializable(with = TagsSerializer::class)
  @SerialName("pool_string")
  val pool: Set<String>,
  @SerialName("up_score")
  val upScore: Int,
  @SerialName("down_score")
  val downScore: Int,
  @SerialName("is_pending")
  val isPending: Boolean,
  @SerialName("is_flagged")
  val isFlagged: Boolean,
  @SerialName("is_deleted")
  val isDeleted: Boolean,
  @SerialName("tag_count")
  val tagCount: Int,
  @Serializable(with = DateTimeSerializer::class)
  @SerialName("updated_at")
  val updatedAt: ZonedDateTime,
  @SerialName("is_banned")
  val isBanned: Boolean,
  @SerialName("pixiv_id")
  val pixivId: Int?,
  @Serializable(with = DateTimeSerializer::class)
  @SerialName("last_commented_at")
  val lastCommentAt: ZonedDateTime?,
  @SerialName("has_active_children")
  val hasActiveChildren: Boolean,
  @SerialName("bit_flags")
  val bitFlags: Long, // TODO: models
  @SerialName("tag_count_meta")
  val tagCountMeta: Int,
  @SerialName("has_large")
  val hasLarge: Boolean?,
  @SerialName("has_visible_children")
  val hasVisibleChildren: Boolean,
  @Serializable(with = TagsSerializer::class)
  @SerialName("tag_string_general")
  val tagGeneral: Set<String>,
  @Serializable(with = TagsSerializer::class)
  @SerialName("tag_string_character")
  val tagCharacter: Set<String>,
  @Serializable(with = TagsSerializer::class)
  @SerialName("tag_string_copyright")
  val tagCopyright: Set<String>,
  @Serializable(with = TagsSerializer::class)
  @SerialName("tag_string_artist")
  val tagArtist: Set<String>,
  @Serializable(with = TagsSerializer::class)
  @SerialName("tag_string_meta")
  val tagMeta: Set<String>,
  @SerialName("file_url")
  val fileUrl: String,
  @SerialName("large_file_url")
  val largeFileUrl: String,
  @SerialName("preview_file_url")
  val previewFileUrl: String,
)
