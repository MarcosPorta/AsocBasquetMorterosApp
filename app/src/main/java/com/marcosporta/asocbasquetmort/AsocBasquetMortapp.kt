package com.marcosporta.asocbasquetmort

import android.app.Application
import com.google.android.gms.ads.MobileAds

class AsocBasquetMortapp:Application() {

    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this)
    }
}