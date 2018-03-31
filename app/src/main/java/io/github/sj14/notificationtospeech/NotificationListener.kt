package io.github.sj14.notificationtospeech

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.speech.tts.TextToSpeech
import android.support.annotation.RequiresApi
import android.util.Log
import android.widget.Toast

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
@SuppressLint("OverrideAbstract")
/**
 * Created by simon on 12.03.18.
 */
class NotificationListener() : NotificationListenerService(), Parcelable {
    constructor(parcel: Parcel) : this() {
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onNotificationPosted(sbn : StatusBarNotification) {
        Log.d("NotificationToSpeech", "NotificationPosted")

        val intent = Intent("NOTIFICATION")
        //intent.putExtras(sbn.notification.extras.)


        val myPackageManager = applicationContext.packageManager
        var myApplicationInfo: ApplicationInfo?
        try {
            myApplicationInfo = myPackageManager.getApplicationInfo(sbn.packageName, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            myApplicationInfo = null
        }

        val appName = (if (myApplicationInfo != null) {
            myPackageManager.getApplicationLabel(myApplicationInfo)
        } else "") as String

        intent.putExtra("AppName", appName)
        intent.putExtra("Ticker", sbn.notification.tickerText)

        sendBroadcast(intent)
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
