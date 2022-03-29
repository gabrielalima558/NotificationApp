package com.example.notificationworkmanager

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.work.*
import com.example.notificationworkmanager.workers.NotificationWorker
import java.util.concurrent.TimeUnit

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val workManager = WorkManager.getInstance(application)
    internal var outputWorkInfo: LiveData<WorkInfo>? = null

//    var inputData: Data = AlertDialog.Builder().putInt(DBEventIDTag, DBEventID).build()


    fun oneTimeNotificationWorker() {
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

    //quando quero que só notifique se tiver bateria
    val constraints = Constraints.Builder()
        .setRequiresCharging(true)
        .build()

    fun periodicNotificationWorker() {
        val periodicWorkRequest = PeriodicWorkRequest.Builder(NotificationWorker::class.java, 15, TimeUnit.MINUTES)
            .build()
        workManager.enqueue(periodicWorkRequest)
    }


}