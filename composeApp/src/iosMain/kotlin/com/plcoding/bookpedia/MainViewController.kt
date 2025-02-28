package com.plcoding.bookpedia

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.plcoding.bookpedia.app.App
import com.plcoding.bookpedia.di.initKoin
import createDataStore

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App(
    prefs = remember {
        createDataStore()
    }
) }