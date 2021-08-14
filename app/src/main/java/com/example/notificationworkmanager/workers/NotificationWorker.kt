package com.example.notificationworkmanager.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.notificationworkmanager.util.makeStatusNotification

class NotificationWorker(ctx: Context, params: WorkerParameters): Worker(ctx, params) {
    override fun doWork(): Result {
        return try {
            makeStatusNotification("Olá Gabi", applicationContext)
            Thread.sleep(20000)
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}