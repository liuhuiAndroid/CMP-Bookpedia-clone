package com.plcoding.bookpedia.book.data.dto

import kotlinx.serialization.Serializable

// Kotlinx Serialization 更加强调灵活性和可扩展性，因此有时候需要手动定义序列化器（KSerializer）来处理复杂的序列化或反序列化逻辑
@Serializable(with = BookWorkDtoSerializer::class)
data class BookWorkDto(
    val description: String? = null,
)
