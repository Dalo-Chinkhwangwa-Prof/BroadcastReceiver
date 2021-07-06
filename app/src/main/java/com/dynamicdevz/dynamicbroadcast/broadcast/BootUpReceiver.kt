package com.dynamicdevz.dynamicbroadcast.broadcast

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.dynamicdevz.dynamicbroadcast.MainActivity

class BootUpReceiver: BroadcastReceiver(){
    override fun onReceive(p0: Context, p1: Intent?) {
        Toast.makeText(p0, "Boot up successfully completed", Toast.LENGTH_SHORT).show()

        val intent = Intent(p0, MainActivity::class.java)
        Log.d("TAG_X", "attempting to start new activity")
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        Log.d("TAG_X", "attempting to start new activity 1")
        p0.startActivity(intent)
        Log.d("TAG_X", "attempting to start new activity 2")
    }
}