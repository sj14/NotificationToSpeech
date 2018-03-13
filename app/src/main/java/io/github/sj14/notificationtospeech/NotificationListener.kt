package io.github.sj14.notificationtospeech

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.Parcel
import android.os.Parcelable
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Toast

@SuppressLint("OverrideAbstract")
/**
 * Created by simon on 12.03.18.
 */
class NotificationListener() : NotificationListenerService(), Parcelable {
    constructor(parcel: Parcel) : this() {
    }

    override fun onNotificationPosted(sbn : StatusBarNotification) {
        // TODO: as const
        val intent = Intent("io.github.sj14.notificationtospeech.notification")
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NotificationListener> {
        override fun createFromParcel(parcel: Parcel): NotificationListener {
            return NotificationListener(parcel)
        }

        override fun newArray(size: Int): Array<NotificationListener?> {
            return arrayOfNulls(size)
        }
    }
}
