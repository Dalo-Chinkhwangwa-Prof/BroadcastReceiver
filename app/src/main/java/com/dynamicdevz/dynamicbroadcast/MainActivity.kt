package com.dynamicdevz.dynamicbroadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.dynamicdevz.dynamicbroadcast.R.*
import com.dynamicdevz.dynamicbroadcast.broadcast.NetworkReceiver
import com.dynamicdevz.dynamicbroadcast.viewmodel.GitViewModel

//android.intent.action.AIRPLANE_MODE
//android.intent.action.BOOT_COMPLETED

class MainActivity : AppCompatActivity(), NetworkReceiver.NetworkResponder {
    private lateinit var conManager: ConnectivityManager

    private val apIntent = IntentFilter("android.intent.action.AIRPLANE_MODE")

    private val netR = NetworkReceiver(this)

    private val airplaneReceiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent) {
            if (p1.action == "android.intent.action.AIRPLANE_MODE") {
                if (isAirplaneModeOn())
                    Toast.makeText(p0, "Airplane mode has been switched ON.", Toast.LENGTH_LONG)
                        .show()
                else
                    Toast.makeText(p0, "Airplane mode has been switched OFF.", Toast.LENGTH_LONG)
                        .show()
            } else if (p1.action == "97.9") {
                Toast.makeText(p0, "Our Station 101", Toast.LENGTH_LONG).show()
            }
        }
    }

    private val viewModel: GitViewModel by viewModels()

    private val adapter = GitAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        conManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        findViewById<RecyclerView>(R.id.main_recyclerview).adapter = adapter
        //conManager.activeNetwork.
//        conManager.

        viewModel.gitLiveData.observe(this, {
            adapter.list = it
        })
        viewModel.getAllRepos()

        findViewById<TextView>(id.send).setOnClickListener {
            sendBroadcast(Intent("97.9"))
        }
    }

    fun isAirplaneModeOn(): Boolean = Settings.System.getInt(
        contentResolver, Settings.Global.AIRPLANE_MODE_ON, 0
    ) != 0

    override fun onStart() {
        super.onStart()
        Log.d("TAG_X", "Receiver registered...")
        registerReceiver(netR, IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))
        registerReceiver(airplaneReceiver, IntentFilter("97.9"))
    }

    override fun onStop() {
        super.onStop()
        Log.d("TAG_X", "Receiver registered...")
        unregisterReceiver(airplaneReceiver)
        unregisterReceiver(netR)
    }

    override fun internetConnected(connected: Boolean) {
        if (connected) {
            findViewById<ConstraintLayout>(R.id.blocker).visibility = View.GONE
        } else {
            findViewById<ConstraintLayout>(R.id.blocker).visibility = View.VISIBLE
        }
    }
}





