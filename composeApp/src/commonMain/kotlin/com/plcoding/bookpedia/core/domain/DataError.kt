package com.plcoding.bookpedia.core.domain

sealed interface DataError : Error {

    // 远程错误
    enum class Remote : DataError {
        REQUEST_TIMEOUT,    // 请求超时
        TOO_MANY_REQUESTS,  // 请求过多
        NO_INTERNET,        // 没有网络连接
        SERVER,             // 服务器错误
        SERIALIZATION,      // 序列化错误
        UNKNOWN,             // 未知的远程错误
        UNAUTHORIZED,
        CONFLICT,
        PAYLOAD_TOO_LARGE
    }

    // 本地错误
    enum class Local : DataError {
        DISK_FULL,          // 磁盘已满
        UNKNOWN             // 未知的本地错误
    }
}