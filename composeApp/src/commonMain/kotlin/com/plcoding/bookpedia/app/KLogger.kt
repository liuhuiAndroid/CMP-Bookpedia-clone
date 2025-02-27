package com.plcoding.bookpedia.app

import co.touchlab.kermit.Logger
import co.touchlab.kermit.Severity

object KLogger {

    fun setTag(tag: String) {
        Logger.setTag(tag)
    }

    // TODO release 模式设置高
    fun setMinSeverity(severity: Severity) {
        Logger.setMinSeverity(severity)
    }

    fun i(message: String, throwable: Throwable? = null) {
        Logger.i(message, throwable)
    }

    fun i(message: String, throwable: Throwable? = null, tag: String) {
        Logger.i(message, throwable, tag)
    }
}