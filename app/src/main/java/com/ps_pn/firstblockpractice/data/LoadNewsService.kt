package com.ps_pn.firstblockpractice.data

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import kotlin.concurrent.thread

private const val TIMEOUT = 1500L

class LoadNewsService : Service() {

    private val binder = LocalBinder()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        thread {
            Thread.sleep(TIMEOUT)
            StubData.getNewsEventsStubData()
            stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    inner class LocalBinder : Binder() {
        fun getService(): LoadNewsService = this@LoadNewsService
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LoadNewsService::class.java)
        }
    }
}
