package com.marcosporta.asocbasquetmort

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Poner icono en el Action Bar
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.mipmap.ic_launcher)
        supportActionBar?.title = "A.B.M."
    }

    fun clickFixture(view: View){
        val intent= Intent(this,FixtureActivity::class.java)
        startActivity(intent)
    }
    fun clickPosiciones(view: View){
        val intent= Intent(this,PosicionesActivity::class.java)
        startActivity(intent)
    }
    fun clickEstadisticas(view: View){
        val intent= Intent(this,EstadisticasActivity::class.java)
        startActivity(intent)
    }

}