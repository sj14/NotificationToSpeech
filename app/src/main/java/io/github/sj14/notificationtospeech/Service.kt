package io.github.sj14.notificationtospeech

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Notification
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import android.media.AudioManager
import android.os.Build
import android.os.IBinder
import android.os.Parcel
import android.os.Parcelable
import android.preference.PreferenceManager
import android.speech.tts.TextToSpeech
import android.support.annotation.RequiresApi
import android.util.Log
import android.widget.Toast
import java.util.*

/**
 * Created by simon on 13.03.18.
 */

class Service() : Service(), TextToSpeech.OnInitListener, Parcelable {

    protected var mReceiver: io.github.sj14.notificationtospeech.Service.MyReceiver? = MyReceiver()

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            //int result = tts.setLanguage(Locale.getDefault());
            val result = tts!!.setLanguage(Locale.ENGLISH)
        } else {
            Log.e("TTS", "Initilization Failed!")
        }
    }

    private var tts: TextToSpeech? = null

    constructor(parcel: Parcel) : this() {
    }

    override fun onCreate() {
        super.onCreate()
        tts = TextToSpeech(this, this)
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        if (mReceiver == null) mReceiver = MyReceiver()
        registerReceiver(mReceiver, IntentFilter("io.github.sj14.notificationtospeech.notification"))
        Toast.makeText(this, "Service started", Toast.LENGTH_LONG).show()
        return Service.START_STICKY
    }

    override fun onDestroy() {
        // shutdown tts
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }

    override fun onBind(p0: Intent?): IBinder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Service> {
        override fun createFromParcel(parcel: Parcel): Service {
            return Service(parcel)
        }

        override fun newArray(size: Int): Array<Service?> {
            return arrayOfNulls(size)
        }
    }

    inner class MyReceiver : BroadcastReceiver() {
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun onReceive(context: Context, intent: Intent?) {
            if (intent == null) {
                return
            }
            tts!!.speak("Hallo", TextToSpeech.QUEUE_ADD, null, "")
        }
    }
}