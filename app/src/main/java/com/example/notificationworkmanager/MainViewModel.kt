package com.example.notificationworkmanager

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.notificationworkmanager.workers.NotificationWorker

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val workManager = WorkManager.getInstance(application)
    internal var outputWorkInfo: LiveData<WorkInfo>? = null

//    var inputData: Data = AlertDialog.Builder().putInt(DBEventIDTag, DBEventID).build()

    fun initNotificationWorker() {
        val notificationRequest = OneTimeWorkRequestBuilder<NotificationWorker>()
//                .setInputData(createInputDataForUri())
//            .setInitialDelay(
//                calculateDelay(event.getDate()),
//                java.util.concurrent.TimeUnit.MILLISECONDS
//            )
//            .setInputData(inputData)
            .build()
        outputWorkInfo = workManager.getWorkInfoByIdLiveData(notificationRequest.id)
        workManager.enqueue(notificationRequest)
    }
}