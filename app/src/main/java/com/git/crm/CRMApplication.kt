package com.git.crm

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication


class CRMApplication :  MultiDexApplication() {

    override fun attachBaseContext(context: Context?) {
        super.attachBaseContext(context)
        MultiDex.install(this)
    }
    override fun onCreate() {
        super.onCreate()
        Thread.setDefaultUncaughtExceptionHandler { thread, ex ->
            handleUncaughtException(
                thread,
                ex
            )
        }
    }

    fun handleUncaughtException(thread: Thread?, e: Throwable) {
        val stackTrace = Log.getStackTraceString(e)
        val message = e.message
        val intent =
            Intent(Intent.ACTION_SEND)
        intent.type = "message/rfc822"
        intent.putExtra(
            Intent.EXTRA_EMAIL,
            arrayOf("nidhinraj111@gmail.com")
        )
        intent.putExtra(Intent.EXTRA_SUBJECT, "MyApp Crash log file")
        intent.putExtra(Intent.EXTRA_TEXT, stackTrace)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK // required when starting from Application
        startActivity(intent)
    }
}


