package io.github.sj14.notificationtospeech

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import android.service.notification.NotificationListenerService

@SuppressLint("OverrideAbstract")
/**
 * Created by simon on 12.03.18.
 */
class NotificationListener() : NotificationListenerService(), Parcelable {
    constructor(parcel: Parcel) : this() {
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
