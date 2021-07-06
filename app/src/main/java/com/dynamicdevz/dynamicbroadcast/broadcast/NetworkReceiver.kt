package com.dynamicdevz.dynamicbroadcast.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log

class NetworkReceiver(private val networkResponder: NetworkResponder) : BroadcastReceiver(){
    private lateinit var cMgr: ConnectivityManager
    override fun onReceive(p0: Context?, p1: Intent?) {
        cMgr = p0?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if(p1?.action == "android.net.conn.CONNECTIVITY_CHANGE"){
            Log.d("TAG_X", "Connectivity changed..")
            val netInfo = cMgr.activeNetworkInfo
            netInfo?.let {
                networkResponder.internetConnected(it.isConnectedOrConnecting)
            } ?: networkResponder.internetConnected(false)
        }
    }

    interface NetworkResponder{
        fun internetConnected(connected: Boolean)
    }
}