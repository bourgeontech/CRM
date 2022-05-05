package com.git.crm.Utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object NetworkConnection {

    fun isNetworkAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var activeNetworkInfo: NetworkInfo? = null
        activeNetworkInfo = cm.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
    }
}