package com.example.notificationworkmanager

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.work.WorkInfo
import com.example.notificationworkmanager.util.makeStatusNotification

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.initNotificationWorker()
        viewModel.outputWorkInfo?.observe(this, workInfoObserver())

    }

    private fun workInfoObserver(): Observer<WorkInfo> {
        return Observer { workInfo ->
            if (workInfo.state.isFinished) {
                Toast.makeText(this, "Cabousse", Toast.LENGTH_SHORT).show()
            }
        }
    }
}