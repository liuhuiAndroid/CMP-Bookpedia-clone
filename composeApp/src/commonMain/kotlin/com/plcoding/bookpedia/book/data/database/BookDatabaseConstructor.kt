package com.plcoding.bookpedia.book.data.database

import androidx.room.RoomDatabaseConstructor

// expect 用于标记那些在不同平台上实现的功能，并且该功能的实现是不同的。然后，针对每个平台，你需要使用 actual 来提供具体的实现
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object BookDatabaseConstructor : RoomDatabaseConstructor<FavoriteBookDatabase> {
    override fun initialize(): FavoriteBookDatabase
}