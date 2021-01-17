package me.xr1s.erobot

import net.mamoe.mirai.alsoLogin
import net.mamoe.mirai.BotFactory
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.PlainText
import net.mamoe.mirai.event.events.MessageEvent

// handlers
import me.xr1s.erobot.handlers.echo.EchoHandler
import me.xr1s.erobot.handlers.danbooru.Danbooru
import me.xr1s.erobot.handlers.danbooru.DanbooruHandler

suspend fun dispatchMessage(event: MessageEvent) {
  for (message in event.message) {
    if (message !is PlainText) continue
    val content = message.toString().trim()
    with (content.drop(1)) {
    }
  }
}

@kotlin.ExperimentalUnsignedTypes
suspend fun main() {
  val bot = BotFactory.newBot(username, password) {
    fileBasedDeviceInfo()
  }.alsoLogin()
  run {  // init
    CommandRegister.register("echo", EchoHandler)
    CommandRegister.register("danbooru", DanbooruHandler)
  }
  // handle
  bot.eventChannel.filter { event ->
    event is MessageEvent
  }.subscribeAlways<MessageEvent> { event ->
    CommandRegister.handle(event)
  }
}
