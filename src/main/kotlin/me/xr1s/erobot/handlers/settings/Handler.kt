package me.xr1s.erobot.handlers.settings

import me.xr1s.erobot.CommandConfigure
import me.xr1s.erobot.CommandHandler
import net.mamoe.mirai.event.events.MessageEvent
import net.mamoe.mirai.message.data.MessageChain
import net.mamoe.mirai.message.data.MessageSource
import net.mamoe.mirai.message.data.messageChainOf
import net.mamoe.mirai.message.data.toMessageChain
import net.mamoe.mirai.message.data.toPlainText

object SettingsHandler : CommandHandler {
  override fun getSimpleHelp(config: CommandConfigure, name: String): MessageChain? {
    return null
  }
  override fun getDetailHelp(config: CommandConfigure, name: String): MessageChain? {
    return null
  }
  override suspend fun handle(name: String, event: MessageEvent, args: Array<String>): MessageChain? {
    return null
  }
}
