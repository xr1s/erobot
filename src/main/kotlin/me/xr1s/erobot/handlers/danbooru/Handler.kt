package me.xr1s.erobot.handlers.danbooru

import me.xr1s.erobot.CommandConfigure
import me.xr1s.erobot.CommandHandler
import net.mamoe.mirai.event.events.MessageEvent
import net.mamoe.mirai.message.data.MessageChain
import net.mamoe.mirai.message.data.messageChainOf
import net.mamoe.mirai.message.data.toPlainText
import net.mamoe.mirai.utils.ExternalResource.Companion.uploadAsImage
import net.mamoe.mirai.utils.ExternalResource.Companion.toExternalResource
import me.xr1s.erobot.handlers.danbooru.Danbooru.download
import me.xr1s.erobot.models.danbooru.FileExt

object DanbooruHandler : CommandHandler {
  override fun getSimpleHelp(config: CommandConfigure, name: String): MessageChain? {
    val prefix = config.prefix
    return messageChainOf("支持图片下载和标签搜索".toPlainText())
  }
  override fun getDetailHelp(config: CommandConfigure, name: String): MessageChain? {
    val prefix = config.prefix
    return messageChainOf("$prefix$name 标签搜索".toPlainText())
  }
  override suspend fun handle(name: String, event: MessageEvent, args: Array<String>): MessageChain? {
    var danbooruId: Int? = null
    if (args.size != 1) {
      danbooruId = args[1].toIntOrNull()
    }
    var post = Danbooru.posts(id = danbooruId, tags = args)
    if (post == null) {
      return messageChainOf("没有找到图片".toPlainText())
    }
    if (post.fileExt in setOf(FileExt.MP4, FileExt.SWF, FileExt.ZIP)) {
      return messageChainOf(
        "不支持的文件格式，请重试\n".toPlainText(),
        "原图: ${post.source}".toPlainText(),
      )
    }
    try {
      post
        .download()
        .toExternalResource()
        .uploadAsImage(event.subject)
        .apply {
          return@handle messageChainOf(this)
        }
    } catch (e: Exception) {
      return messageChainOf(
        "抛异常了，一般是下载超时失败，开发没时间开发，手动重试吧\n".toPlainText(),
        "原图: ${post.source}\n".toPlainText(),
        "异常信息: ${e.message}".toPlainText(),
      )
    }
  }
}
