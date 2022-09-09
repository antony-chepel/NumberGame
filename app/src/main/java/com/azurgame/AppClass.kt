package com.azurgame

import android.app.Application
import android.content.Context
import com.azurgame.blck.Adv
import com.azurgame.blck.STAN.MAIN_ID
import com.azurgame.blck.STAN.ONESIGNAL_APP_ID
import com.onesignal.OneSignal
import com.orhanobut.hawk.Hawk
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@HiltAndroidApp
class AppClass: Application() {
    override fun onCreate() {
        super.onCreate()
        Hawk.init(this).build()
        GlobalScope.launch(Dispatchers.IO) {
            applyDeviceId(context = applicationContext)
        }
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        // OneSignal Initialization
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

    private suspend fun applyDeviceId(context: Context) {
        val advertisingInfo = Adv(context)
        val idInfo = advertisingInfo.getAdvertisingId()
        Hawk.put(MAIN_ID, idInfo)
    }
}