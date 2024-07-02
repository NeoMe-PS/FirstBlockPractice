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
import com.psbn.news.R as newsR


const val WORKER_TITLE_KEY = "title"
const val WORKER_ID_KEY = "id"
const val WORKER_SUM_KEY = "sum"

class DonateWorker(private val context: Context, private val workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {
        val title = workerParams.inputData.getString(WORKER_TITLE_KEY) ?: ""
        val sum = workerParams.inputData.getInt(WORKER_SUM_KEY, 1)
        val text = context.getString(R.string.donate_notification_info_text, sum)
        showNotification(
            title = title,
            info = text,
            id = workerParams.inputData.getInt(WORKER_ID_KEY, 0)
        )
        return Result.success()
    }

    private fun showNotification(title: String, info: String, id: Int) {
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
        val pendingIntent = NavDeepLinkBuilder(context)
            .setGraph(newsR.navigation.news_navgraph)
            .setDestination(newsR.id.newsDetailFragment)
            .setArguments(args)
            .createPendingIntent()

        val deleteIntent = Intent(context, DonateWorker::class.java)
        val deletePendingIntent = PendingIntent.getBroadcast(
            context, 0, deleteIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(info)
            .setSmallIcon(com.psbn.news.R.drawable.accept)
            .setContentIntent(pendingIntent)
            .addAction(
                com.psbn.news.R.drawable.accept,
                context.getString(R.string.remember_later),
                deletePendingIntent
            )
            .setAutoCancel(true)
            .build()
        notificationManager.notify(inputData.getInt(WORKER_ID_KEY, 1), notification)
    }

    companion object {
        const val WORK_NAME = "donate_worker"
        private const val CHANNEL_ID = "donate_worker_channel_id"
        private const val CHANNEL_NAME = "donate information"
        fun makeRequest(title: String, sum: Int, id: Int): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<DonateWorker>()
                .setInputData(
                    workDataOf(
                        WORKER_TITLE_KEY to title,
                        WORKER_SUM_KEY to sum,
                        WORKER_ID_KEY to id
                    )
                )
                .setConstraints(Constraints.Builder().setRequiresCharging(true).build())
                .build()
        }
    }
}