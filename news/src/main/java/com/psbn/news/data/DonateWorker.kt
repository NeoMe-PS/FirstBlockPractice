package com.psbn.news.data

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.psbn.firstblockpractice.core.R
import java.util.concurrent.TimeUnit
import com.psbn.news.R as newsR


const val WORKER_TITLE_KEY = "title"
const val WORKER_INFO_KEY = "info"
const val WORKER_ID_KEY = "id"
const val WORKER_SUM_KEY = "sum"
const val WORKER_REMINDER_KEY = "sum"
const val DEFAULT_ID_VALUE = -1
const val DEFAULT_SUM_VALUE = -1
const val DEFAULT_DELAY_VALUE = 0L

class DonateWorker(private val context: Context, private val workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {
        val title = workerParams.inputData.getString(WORKER_TITLE_KEY) ?: ""
        val text = workerParams.inputData.getString(WORKER_INFO_KEY) ?: ""
        val id = workerParams.inputData.getInt(WORKER_ID_KEY, DEFAULT_ID_VALUE)
        val isReminder = workerParams.inputData.getBoolean(WORKER_REMINDER_KEY, false)
        showNotification(
            title = title,
            info = text,
            id = id,
            isReminder = isReminder
        )
        return Result.success()
    }

    private fun showNotification(title: String, info: String, id: Int, isReminder: Boolean) {
        val notificationManager =
            context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }

        val args = Bundle().apply {
            putInt(WORKER_ID_KEY, id)
        }

        val rememberLaterIntent = Intent(context, DonateNotificationBroadcast::class.java).apply {
            putExtra(WORKER_TITLE_KEY, title)
            putExtra(WORKER_ID_KEY, id)
        }
        val rememberPendingIntent = PendingIntent.getBroadcast(
            context, 0, rememberLaterIntent,
            0
        )

        val onClickIntent = NavDeepLinkBuilder(context)
            .setGraph(newsR.navigation.news_navgraph)
            .setDestination(newsR.id.newsDetailFragment)
            .setArguments(args)
            .createPendingIntent()

        val notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(info)
            .setStyle(NotificationCompat.BigTextStyle())
            .setSmallIcon(com.psbn.news.R.drawable.accept)
            .setContentIntent(onClickIntent)
            .setAutoCancel(true)

        if (!isReminder) {
            notificationBuilder
                .addAction(
                    com.psbn.news.R.drawable.accept,
                    context.getString(R.string.remember_later),
                    rememberPendingIntent
                )
        }

        val notification = notificationBuilder.build()
        notificationManager.notify(inputData.getInt(WORKER_ID_KEY, DEFAULT_ID_VALUE), notification)
    }

    companion object {
        const val WORK_NAME = "donate_worker"
        const val CHANNEL_ID = "donate_worker_channel_id"
        private const val CHANNEL_NAME = "donate information"
        fun makeRequest(
            title: String,
            sum: Int = DEFAULT_SUM_VALUE,
            id: Int = DEFAULT_ID_VALUE,
            delay: Long = DEFAULT_DELAY_VALUE,
            isReminder: Boolean = false,
            infoText: String
        ): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<DonateWorker>()
                .setInputData(
                    workDataOf(
                        WORKER_TITLE_KEY to title,
                        WORKER_INFO_KEY to infoText,
                        WORKER_SUM_KEY to sum,
                        WORKER_ID_KEY to id,
                        WORKER_REMINDER_KEY to isReminder
                    )
                )
                .setInitialDelay(delay, TimeUnit.MINUTES)
                .setConstraints(Constraints.Builder().setRequiresCharging(true).build())
                .build()
        }
    }
}