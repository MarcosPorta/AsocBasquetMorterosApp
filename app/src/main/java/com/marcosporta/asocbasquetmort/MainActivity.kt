package com.marcosporta.asocbasquetmort

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.marcosporta.asocbasquetmort.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Poner icono en el Action Bar
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.mipmap.ic_launcher)
        supportActionBar?.title = "A.B.M."
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FFA000")))

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFixture.setOnClickListener {
            val intent=Intent(this,FixtureActivity::class.java)
            startActivity(intent)
        }
        binding.btnPosiciones.setOnClickListener {
            val intent=Intent(this,PosicionesActivity::class.java)
            startActivity(intent)
        }
        binding.btnEstadisticas.setOnClickListener {
            val intent=Intent(this,EstadisticasActivity::class.java)
            startActivity(intent)
        }
    }
}