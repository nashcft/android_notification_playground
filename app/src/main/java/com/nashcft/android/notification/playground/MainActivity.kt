package com.nashcft.android.notification.playground

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.nashcft.android.notification.playground.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var notificationManager: NotificationManagerCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        notificationManager = NotificationManagerCompat.from(applicationContext)
        createNotificationChannels()

        var minCounter = 0
        var lowCounter = 0
        var defaultCounter = 0
        var highCounter = 0

        binding.buttonMinNormal.setOnClickListener {
            notify(
                minCounter,
                "Minimum importance notification",
                "notification count: ${minCounter++}",
                Channel.MIN,
                false
            )
        }
        binding.buttonMinSilent.setOnClickListener {
            notify(
                minCounter,
                "Minimum importance notification",
                "notification count: ${minCounter++}",
                Channel.MIN,
                true
            )
        }

        binding.buttonLowNormal.setOnClickListener {
            notify(
                lowCounter,
                "Low importance notification",
                "notification count: ${lowCounter++}",
                Channel.LOW,
                false
            )
        }
        binding.buttonLowSilent.setOnClickListener {
            notify(
                lowCounter,
                "Low importance notification",
                "notification count: ${lowCounter++}",
                Channel.LOW,
                true
            )
        }

        binding.buttonDefaultNormal.setOnClickListener {
            notify(
                defaultCounter,
                "Default importance notification",
                "notification count: ${defaultCounter++}",
                Channel.DEFAULT,
                false
            )
        }
        binding.buttonDefaultSilent.setOnClickListener {
            notify(
                defaultCounter,
                "Default importance notification",
                "notification count: ${defaultCounter++}",
                Channel.DEFAULT,
                true
            )
        }

        binding.buttonHighNormal.setOnClickListener {
            notify(
                highCounter,
                "High importance notification",
                "notification count: ${highCounter++}",
                Channel.HIGH,
                false
            )
        }
        binding.buttonHighSilent.setOnClickListener {
            notify(
                highCounter,
                "High importance notification",
                "notification count: ${highCounter++}",
                Channel.HIGH,
                true
            )
        }
    }

    private fun createNotificationChannels() {
        notificationManager.createNotificationChannels(
            Channel.values().map { NotificationChannel(it.id, it.channelName, it.importance) }
        )
    }

    private fun notify(
        id: Int,
        title: String,
        message: String,
        channel: Channel,
        isSilent: Boolean
    ) {
        val notification = NotificationCompat.Builder(this, channel.id).apply {
            setContentTitle(title)
            setContentText(message)
            setSmallIcon(R.drawable.ic_baseline_announcement)
            setAutoCancel(true)
            // core >=1.5.0
            // setSilent(isSilent)
            if (isSilent) {
                setNotificationSilent()
            }
        }.build()
        notificationManager.notify(id, notification)
    }
}

internal enum class Channel(val id: String, val channelName: String, val importance: Int) {
    MIN("importance_min", "Importance: MIN", NotificationManager.IMPORTANCE_MIN),
    LOW("importance_low", "Importance: LOW", NotificationManager.IMPORTANCE_LOW),
    DEFAULT("importance_default", "Importance: DEFAULT", NotificationManager.IMPORTANCE_DEFAULT),
    HIGH("importance_high", "Importance: HIGH", NotificationManager.IMPORTANCE_HIGH)
}

