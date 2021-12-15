package com.marcosporta.asocbasquetmort

import android.app.Application
import com.google.android.gms.ads.MobileAds

//Esta clase es la primera que se llama aal abrir la app.
class AsocBasquetMortapp:Application() {

    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this)
    }
}