package com.marcosporta.asocbasquetmort

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TableLayout
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

import org.json.JSONException

class PosicionesActivity : AppCompatActivity() {
    var tbPosiciones:TableLayout?=null
    lateinit var myAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posiciones)

        //Para banner
        MobileAds.initialize(this) {}
        myAdView = findViewById(R.id.banner)
        val adRequest = AdRequest.Builder().build()
        myAdView.loadAd(adRequest)

        //Poner icono en el Action Bar
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.mipmap.ic_launcher)
        supportActionBar?.title = "A.B.M."

        tbPosiciones=findViewById(R.id.tbPosiciones)
        tbPosiciones?.removeAllViews()
        val queue=Volley.newRequestQueue(this)
        val url= "https://marcosporta.site/morterenseapp/registrosp.php"

        //Creacion de json object request
        val jsonObjectRequest=JsonObjectRequest(Request.Method.GET,url,null,
            {response ->
                try {
                    val jsonArray= response.getJSONArray("data")
                    for (i in 0 until jsonArray.length() ){
                        val jsonObject= jsonArray.getJSONObject(i)
                        val registro = LayoutInflater.from(this).inflate(R.layout.tabla_row_pocisiones,null,false)
                        val colEquipo=registro.findViewById<View>(R.id.colEquipo) as TextView
                        val colPj=registro.findViewById<View>(R.id.colPj) as TextView
                        val colPg=registro.findViewById<View>(R.id.colPg) as TextView
                        val colPp=registro.findViewById<View>(R.id.colPp) as TextView
                        val colPts=registro.findViewById<View>(R.id.colPts) as TextView
                        val colDif=registro.findViewById<View>(R.id.colDif) as TextView
                        colEquipo.text=jsonObject.getString("equipo")
                        colPj.text=jsonObject.getString("pj")
                        colPg.text=jsonObject.getString("pg")
                        colPp.text=jsonObject.getString("pp")
                        colPts.text=jsonObject.getString("pts")
                        colDif.text=jsonObject.getString("dif")
                        tbPosiciones?.addView(registro)
                    }
                }catch (e: JSONException){
                    e.printStackTrace()
                }
            }, { error ->
                    Toast.makeText(this,"Error $error ",Toast.LENGTH_LONG).show()
                }
            )
        queue.add(jsonObjectRequest)
    }

}