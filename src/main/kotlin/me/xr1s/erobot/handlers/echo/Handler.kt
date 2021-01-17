package me.xr1s.erobot.handlers.echo

import me.xr1s.erobot.CommandConfigure
import me.xr1s.erobot.CommandHandler
import net.mamoe.mirai.event.events.MessageEvent
import net.mamoe.mirai.message.data.MessageChain
import net.mamoe.mirai.message.data.MessageSource
import net.mamoe.mirai.message.data.messageChainOf
import net.mamoe.mirai.message.data.toMessageChain
import net.mamoe.mirai.message.data.toPlainText

object EchoHandler : CommandHandler {
  override fun getSimpleHelp(config: CommandConfigure, name: String): MessageChain? {
    return messageChainOf("我是一只复读机".toPlainText())
  }
  override fun getDetailHelp(config: CommandConfigure, name: String): MessageChain? {
    return messageChainOf("我是一只复读机".toPlainText())
  }
  override suspend fun handle(name: String, event: MessageEvent, args: Array<String>): MessageChain? {
    return event.message.filterNot({ it is MessageSource }).toMessageChain()
  }
}
