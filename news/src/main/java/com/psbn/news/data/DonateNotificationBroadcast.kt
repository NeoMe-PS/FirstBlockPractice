package com.psbn.news.data

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.psbn.firstblockpractice.core.R
import com.psbn.news.data.DonateWorker.Companion.CHANNEL_ID

private const val DELAY = 1L

class DonateNotificationBroadcast : BroadcastReceiver() {


    override fun onReceive(context: Context, intent: Intent?) {
        val id = intent?.getIntExtra(WORKER_ID_KEY, DEFAULT_ID_VALUE) ?: DEFAULT_ID_VALUE
        val title = intent?.getStringExtra(WORKER_TITLE_KEY) ?: "INVALID VALUE"
        val workManager = WorkManager.getInstance(context.applicationContext)
        workManager.enqueueUniqueWork(
            DonateWorker.WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            DonateWorker.makeRequest(
                title = title,
                infoText = context.getString(R.string.donate_notification_info_text_remember),
                id = id,
                delay = DELAY,
                isReminder = true
            )
        )
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.deleteNotificationChannel(CHANNEL_ID)
    }

}