package com.marcosporta.asocbasquetmort

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.marcosporta.asocbasquetmort.EquipoImagenes.equipoImagenMap
import org.json.JSONException
import java.io.IOException
import java.io.InputStream
import java.util.*

class PosicionesActivity : AppCompatActivity() {

    lateinit var mAdView : AdView
    val testId= Arrays.asList("572A1A67BA6623DBD9D945D4043174CB")
    val configuracion=RequestConfiguration.Builder().setTestDeviceIds(testId).build()

    var tbPosiciones:TableLayout?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posiciones)

        MobileAds.setRequestConfiguration(configuracion)
        RequestConfiguration.Builder().setTestDeviceIds(testId)
        MobileAds.initialize(this) {}

        //Banner
        mAdView = findViewById(R.id.bannerPosiciones)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        //Poner boton regrasar y titulo en el Action Bar
        supportActionBar?.title = "A.B.M."
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FFA000")))

        tbPosiciones=findViewById(R.id.tbPosiciones)
        tbPosiciones?.removeAllViews()
        val queue=Volley.newRequestQueue(this)
        val url= "https://marcosporta.site/morterenseapp/posiciones.php"

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
                        val colEsc=registro.findViewById<ImageView>(R.id.imageEscudo)
                        colEquipo.text=jsonObject.getString("equipo")
                        colPj.text=jsonObject.getString("pj")
                        colPg.text=jsonObject.getString("pg")
                        colPp.text=jsonObject.getString("pp")
                        colPts.text=jsonObject.getString("pts")
                        colDif.text=jsonObject.getString("dif")

                        val equipo = jsonObject.getString("equipo")
                        val imageFileName = equipoImagenMap[equipo]

                        if(imageFileName != null){
                            val bitmap = getBitmapFromAssets(this,imageFileName)
                            colEsc.setImageBitmap(bitmap)
                        }

                        tbPosiciones?.addView(registro)
                        //Funcion para mostrar sancion
                        if(i==jsonArray.length()-1) {
                            mostrarSancion()
                        }
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

    private fun mostrarSancion() {
        val queueSancion=Volley.newRequestQueue(this)
        val urlSancion= "https://marcosporta.site/morterenseapp/sancion.php"

        //Creacion de json object request
        val jsonObjectRequest=JsonObjectRequest(Request.Method.GET,urlSancion,null,
            {response ->
                try {

                    val valorSancion = response.getInt("valores")
                    println("MIRAR ACA---> $valorSancion")
                    if(valorSancion == 1){
                        val registroSancion = LayoutInflater.from(this).inflate(R.layout.text_sancion,null,false)
                        val textoSancion=registroSancion.findViewById<View>(R.id.texto_sancion) as TextView
                        textoSancion.text=response.getString("descripcion")
                        tbPosiciones?.addView(registroSancion)
                    }
                }catch (e: JSONException){
                    e.printStackTrace()
                }
            }, { error ->
                Toast.makeText(this,"Error $error ",Toast.LENGTH_LONG).show()
            }
        )
        queueSancion.add(jsonObjectRequest)
    }

    fun getBitmapFromAssets(context: Context, fileName: String): Bitmap? {
        val assetManager = context.assets
        try {
            val inputStream: InputStream = assetManager.open(fileName)
            return BitmapFactory.decodeStream(inputStream)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }
}