package com.plcoding.bookpedia.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.plcoding.bookpedia.app.PermissionsViewModel
import com.plcoding.bookpedia.book.data.database.DatabaseFactory
import com.plcoding.bookpedia.book.data.database.FavoriteBookDatabase
import com.plcoding.bookpedia.book.data.network.KtorRemoteBookDataSource
import com.plcoding.bookpedia.book.data.network.RemoteBookDataSource
import com.plcoding.bookpedia.book.data.repository.DefaultBookRepository
import com.plcoding.bookpedia.book.domain.BookRepository
import com.plcoding.bookpedia.book.presentation.SelectedBookViewModel
import com.plcoding.bookpedia.book.presentation.book_detail.BookDetailViewModel
import com.plcoding.bookpedia.book.presentation.book_list.BookListViewModel
import com.plcoding.bookpedia.core.data.HttpClientFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

// expect 是在共享代码中声明的平台特定 API，而 actual 是平台特定的实现。
// platformModule 配置的是平台特定的依赖
expect val platformModule: Module

// sharedModule 主要用于跨平台的共享依赖，即这些依赖会在多个平台中使用。
val sharedModule = module {
    // 创建一个 HttpClient 实例，并将其注册为 Koin 容器中的单例依赖。
    single { HttpClientFactory.create(get()) }

    // 将 KtorRemoteBookDataSource 注册为一个单例，并将其绑定到接口 RemoteBookDataSource 上。
    // 这样，当其他地方需要 RemoteBookDataSource 类型的依赖时，Koin 就会提供 KtorRemoteBookDataSource 实例。
    singleOf(::KtorRemoteBookDataSource).bind<RemoteBookDataSource>()

    singleOf(::DefaultBookRepository).bind<BookRepository>()

    // 配置一个数据库实例
    single {
        get<DatabaseFactory>().create().setDriver(BundledSQLiteDriver())   // 设置数据库驱动
            .build()
    }

    // 配置数据库中的 DAO 实例
    single { get<FavoriteBookDatabase>().favoriteBookDao }

    // 将 ViewModel 注册为依赖
    viewModelOf(::BookListViewModel)
    viewModelOf(::BookDetailViewModel)
    viewModelOf(::SelectedBookViewModel)
    viewModelOf(::PermissionsViewModel)
}