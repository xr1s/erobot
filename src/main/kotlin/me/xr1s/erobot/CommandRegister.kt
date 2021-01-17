package me.xr1s.erobot

import net.mamoe.mirai.event.events.MessageEvent
import net.mamoe.mirai.message.data.MessageChain
import net.mamoe.mirai.message.data.MessageChainBuilder
import net.mamoe.mirai.message.data.PlainText

public interface CommandHandler {
  fun getSimpleHelp(config: CommandConfigure, name: String): MessageChain?
  fun getDetailHelp(config: CommandConfigure, name: String): MessageChain?
  suspend fun handle(name: String, event: MessageEvent, args: Array<String>): MessageChain?
}

// TODO: 作为群级别配置, 改为 data class
public object CommandConfigure {
  val prefix: String
    get() = "."
}

public object CommandRegister {
  public var handlers = HashMap<String, CommandHandler>()
  val config: CommandConfigure = CommandConfigure
  val atSelf: Boolean = false
  class NameConflictException(message: String) : Exception(message)

  fun register(name: String, handler: CommandHandler) {
    if (this.handlers.contains(name)) {
      throw NameConflictException(name + " conflict")
    }
    this.handlers[name] = handler
  }
  suspend fun handle(event: MessageEvent) {
    val args = event.message
      .filterIsInstance<PlainText>()
      .flatMap { it.toString().trim().split("\\s+".toRegex()) }
      .toTypedArray()
    if (args.size == 0) return
    val prefix = CommandConfigure.prefix
    if (!args[0].startsWith(prefix)) return
    val name = args[0].removePrefix(prefix)
    this.handlers.get(name)?.handle(name, event, args)?.let { event.subject.sendMessage(it) }
  }
  init { // 注册几个默认命令
    this.register("help", object : CommandHandler {
      override fun getSimpleHelp(config: CommandConfigure, name: String): MessageChain? {
        val builder = MessageChainBuilder()
        return builder.append(config.prefix).append(name).append(" [命令]").build()
      }
      override fun getDetailHelp(config: CommandConfigure, name: String): MessageChain? {
        val builder = MessageChainBuilder()
        return builder.append(config.prefix).append(name).append(" [命令]").build()
      }
      override suspend fun handle(
          name: String,
          event: MessageEvent,
          args: Array<String>
      ): MessageChain? {
        val prefix = CommandConfigure.prefix
        val builder = MessageChainBuilder()
        this@CommandRegister.handlers.forEach { self, handler ->
          handler.getSimpleHelp(CommandConfigure, self)?.let {
            builder.append("$prefix$self: ")
            builder.addAll(it)
            builder.appendLine()
          }
        }
        return builder.build()
      }
    })
  }
}
