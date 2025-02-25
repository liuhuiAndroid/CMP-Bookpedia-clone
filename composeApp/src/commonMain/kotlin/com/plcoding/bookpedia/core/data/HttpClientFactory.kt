package com.plcoding.bookpedia.core.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object HttpClientFactory {

    fun create(engine: HttpClientEngine): HttpClient {
        return HttpClient(engine) {
            install(ContentNegotiation) {
                json(json = Json {
                    // 在解析 JSON 时，如果遇到一些未知的字段，会忽略这些字段而不会抛出异常。
                    ignoreUnknownKeys = true
                })
            }
            install(HttpTimeout) {
                socketTimeoutMillis = 30_000L       // 设置 socket 超时时间
                requestTimeoutMillis = 30_000L      // 设置请求超时时间
            }
            // 启用日志功能
            install(Logging) {
                // 默认 logger 或者你可以自定义 logger
                logger = object : Logger {
                    override fun log(message: String) {
                        println("Book log: $message")
                    }
                }
                // TODO: release 模式需要修改成 LogLevel.NONE
                level = LogLevel.ALL
            }
            // 设置所有请求的默认配置
            defaultRequest {
                contentType(ContentType.Application.Json)
            }
        }
    }
}