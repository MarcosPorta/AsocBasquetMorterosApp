package com.marcosporta.asocbasquetmort

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView

class MainActivity : AppCompatActivity() {

    lateinit var myAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Para banner
        myAdView = findViewById(R.id.banner)
        val adRequest = AdRequest.Builder().build()
        myAdView.loadAd(adRequest)

        //Poner icono en el Action Bar
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.mipmap.ic_launcher)
        supportActionBar?.title = "A.B.M."

    }

    fun clickFixture(view: View){
        var intent= Intent(this,FixtureActivity::class.java)
        startActivity(intent)
        //toast
        Toast.makeText(this,"Fixture",Toast.LENGTH_LONG).show()
    }
    fun clickPosiciones(view: View){
        var intent= Intent(this,PosicionesActivity::class.java)
        startActivity(intent)
        //toast
        Toast.makeText(this,"Posiciones",Toast.LENGTH_LONG).show()
    }
    fun clickEstadisticas(view: View){
        var intent= Intent(this,EstadisticasActivity::class.java)
        startActivity(intent)
        //toast
        Toast.makeText(this,"Estadisticas",Toast.LENGTH_LONG).show()
    }

}