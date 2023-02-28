package com.magl.buttontoaction.util

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject

class ConnectivityUtil @Inject constructor(
    private val context: Context,
) {
    fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!
            .isConnected
    }
}
