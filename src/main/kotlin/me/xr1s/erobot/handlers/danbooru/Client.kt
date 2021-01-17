package me.xr1s.erobot.handlers.danbooru

import io.ktor.client.HttpClient
import io.ktor.client.engine.ProxyBuilder
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.request.request
import io.ktor.client.features.HttpTimeout
import io.ktor.client.features.timeout
import io.ktor.http.HttpMethod
import io.ktor.http.URLProtocol
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import me.xr1s.erobot.models.danbooru.Post

// need abstraction
object Danbooru {
  public const val HOST = "danbooru.donmai.us"
  var username: String? = null
  var apiKey: String? = null
  val client = HttpClient(OkHttp) {  // TODO: 抽到 http utils 中
    engine {
      proxy = ProxyBuilder.socks("localhost", 1081)  // TODO: 全局配置读取代理配置
    } 
    install(HttpTimeout)
  }
  fun setAuth(username: String, apiKey: String) {
    this.username = username
    this.apiKey = apiKey
  }
  // 请求 post
  // 全传 null 则为完全随机
  public suspend fun posts(id: Int? = null, tags: Array<String>? = null): Post? {
    val response = client.request<String> {
      method = HttpMethod.Get
      url {
        // User, Password, null is ok
        user = this@Danbooru.username
        password = this@Danbooru.apiKey
        // Scheme
        protocol = URLProtocol.HTTPS
        // Host
        host = this@Danbooru.HOST
        // Path
        if (id == null) {
          path("posts.json")
        } else {
          path("posts", "$id.json")
        }
        // Query
        parameters.apply {
          if (tags != null && tags.size != 0) {
            append("tags", tags.drop(1).joinToString(" "))
          }
          append("random", "true")
          append("limit", "1")
        }
      }
    }
    val element = Json.parseToJsonElement(response)
    println(element)
    val post = when (element) {
      is JsonArray ->
        if (element.size == 0) null
        else element[0]
      is JsonObject -> element
      else -> null
    }
    return post?.let {
      Json.decodeFromJsonElement(Post.serializer(), it)
    }
  }
  suspend fun Post.download(): ByteArray {
    return client.request<ByteArray>(this.fileUrl) {
      method = HttpMethod.Get
      timeout {
        requestTimeoutMillis = 10000
      }
    }
  }
}
