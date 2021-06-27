package com.bradley.fragmentscrollretain

import android.app.Application
import timber.log.Timber

class FragmentScrollRetainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

}