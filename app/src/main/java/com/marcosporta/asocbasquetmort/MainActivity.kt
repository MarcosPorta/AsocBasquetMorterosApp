package com.marcosporta.asocbasquetmort

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    //var btnFixture:Button?=null
    //var btnPosicione:Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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