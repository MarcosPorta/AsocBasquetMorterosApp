package com.marcosporta.asocbasquetmort

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class PruebaActivity : AppCompatActivity() {
    lateinit var mAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prueba)

        val cambioColor : Button = findViewById(R.id.buttonDePrueba)

        cambioColor.setOnClickListener {
            //Cambia el color del texto luego de hacer click
            cambioColor.setTextColor(Color.parseColor("#FF6D00"))
            //Cambia el color del boton luego de hacer click
            cambioColor.setBackgroundColor(Color.parseColor("#000000"))
        }

    }
}


