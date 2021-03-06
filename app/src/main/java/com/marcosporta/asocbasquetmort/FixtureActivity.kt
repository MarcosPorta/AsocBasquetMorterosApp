package com.marcosporta.asocbasquetmort

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import org.json.JSONException
import java.util.*

class FixtureActivity : AppCompatActivity() {

    lateinit var mAdView : AdView
    val testId= Arrays.asList("572A1A67BA6623DBD9D945D4043174CB")
    val configuracion= RequestConfiguration.Builder().setTestDeviceIds(testId).build()

    var tbFixture:TableLayout?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fixture)

        MobileAds.setRequestConfiguration(configuracion)
        RequestConfiguration.Builder().setTestDeviceIds(testId)
        MobileAds.initialize(this) {}

        //Banner
        mAdView = findViewById(R.id.bannerFixture)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        //Poner boton regresar y titulo en el Action Bar
        supportActionBar?.title = "A.B.M."
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        tbFixture=findViewById(R.id.tbFixture)

        llenarTablaTempRegular("https://marcosporta.site/morterenseapp/registros.php")
    }

    //Click en boton Temp. Regular
    fun clickTablaTempRegular(view: View){
        llenarTablaTempRegular("https://marcosporta.site/morterenseapp/registros.php")
    }

    //Click en boton Playoffs
    fun clickTablaPlayoffs(view: View){
        tbFixture?.removeAllViews()
        val queue=Volley.newRequestQueue(this)
        val url="https://marcosporta.site/morterenseapp/registrosplayoffs.php"
        val jsonObjectRequest=JsonObjectRequest(
            Request.Method.GET,url,null,
            { response ->
                try {
                    val jsonArray=response.getJSONArray("data")

                    var contArray = 1
                    var estadoAnterior = 20
                    val arrayPlayoffs = arrayListOf<String>("Cuartos - Juego 1", "Cuartos - Juego 2", "Cuartos - Juego 3",
                        "Semifinales - Juego 1", "Semifinales - Juego 2", "Semifinales - Juego 3", "Finales")

                    val registro3 = LayoutInflater.from(this).inflate(R.layout.tabla_row_fecha, null, false)
                    val colNumeroFecha = registro3.findViewById<View>(R.id.colNumeroFecha) as TextView
                    colNumeroFecha.text = arrayPlayoffs[0]
                    tbFixture?.addView(registro3)

                    for(i in 0 until jsonArray.length() ){
                        val jsonObject=jsonArray.getJSONObject(i)

                        //Accediendo a un campo de la base de datos (fecha)
                        val pruebaFecha = jsonObject.getInt("fecha")


                        //Imprime encabezados de las fechas.
                        if(pruebaFecha !== estadoAnterior){
                            val registro2 = LayoutInflater.from(this).inflate(R.layout.tabla_row_fecha, null, false)
                            val colNumeroFecha = registro2.findViewById<View>(R.id.colNumeroFecha) as TextView
                            colNumeroFecha.text = arrayPlayoffs[contArray]
                            tbFixture?.addView(registro2)
                            contArray += 1
                            estadoAnterior += 1
                        }


                        val registro=LayoutInflater.from(this).inflate(R.layout.tabla_row_fixture,null,false)
                        val colEquipoL=registro.findViewById<View>(R.id.colEquipoL) as TextView
                        val colPtsL=registro.findViewById<View>(R.id.colPtsL) as TextView
                        val colPtsV=registro.findViewById<View>(R.id.colPtsV) as TextView
                        val colEquipoV=registro.findViewById<View>(R.id.colEquipoV) as TextView
                        colEquipoL.text=jsonObject.getString("equipol")
                        colPtsL.text=jsonObject.getString("ptsl")
                        colPtsV.text=jsonObject.getString("ptsv")
                        colEquipoV.text=jsonObject.getString("equipov")
                        tbFixture?.addView(registro)
                    }
                }catch (e: JSONException){
                    e.printStackTrace()
                }
            }, { error ->
                Toast.makeText(this,"Error $error ", Toast.LENGTH_LONG).show()
            }
        )
        queue.add(jsonObjectRequest)
    }

    //Funcion general, al pasarle una url y un texto te completa la tabla -> Temporada regular
    fun llenarTablaTempRegular(url : String){
        tbFixture?.removeAllViews()
        val queue=Volley.newRequestQueue(this)
        val jsonObjectRequest=JsonObjectRequest(
            Request.Method.GET,url,null,
            { response ->
                try {
                    val jsonArray=response.getJSONArray("data")
                    var contador = 0
                    var fecha = 1
                    var hora = ""
                    for(i in 0 until jsonArray.length() ){
                        val jsonObject=jsonArray.getJSONObject(i)

                        //Accediendo a un campo de la base de datos (fecha)
                        val fechaBD = jsonObject.getInt("fecha")
                        val horaBD = jsonObject.getString("diahora")
                        val ptslocalBD = jsonObject.getString("ptsl")
                        //println("MIRAR ACA ------------> $fechaBD y $ $fecha // $horaBD // $ptslocal")
                        println("Mirar $ptslocalBD")
                        //Imprime encabezados de las fechas.
                        if(fechaBD != contador){
                            val registro2 = LayoutInflater.from(this).inflate(R.layout.tabla_row_fecha, null, false)
                            val colNumeroFecha = registro2.findViewById<View>(R.id.colNumeroFecha) as TextView
                            colNumeroFecha.text = getString(R.string.fecha_para_temp_regular,fecha)
                            tbFixture?.addView(registro2)
                            fecha += 1
                            contador += 1
                        }
                        //Imprime la fecha calendario
                        if(horaBD != "" && horaBD != hora && ptslocalBD == " "){
                            val registro3=LayoutInflater.from(this).inflate(R.layout.tabla_row_calendario,null,false)
                            val filaCalendario=registro3.findViewById<View>(R.id.filaCalendario) as TextView
                            filaCalendario.text=jsonObject.getString("diahora")
                            println("MIRAR ACA ------------> $ptslocalBD // $horaBD y $hora")
                            tbFixture?.addView(registro3)
                            hora = horaBD
                        }
                        val registro=LayoutInflater.from(this).inflate(R.layout.tabla_row_fixture,null,false)
                        val colEquipoL=registro.findViewById<View>(R.id.colEquipoL) as TextView
                        val colPtsL=registro.findViewById<View>(R.id.colPtsL) as TextView
                        val colPtsV=registro.findViewById<View>(R.id.colPtsV) as TextView
                        val colEquipoV=registro.findViewById<View>(R.id.colEquipoV) as TextView
                        colEquipoL.text=jsonObject.getString("equipol")
                        colPtsL.text=jsonObject.getString("ptsl")
                        colPtsV.text=jsonObject.getString("ptsv")
                        colEquipoV.text=jsonObject.getString("equipov")
                        tbFixture?.addView(registro)

                    }
                }catch (e: JSONException){
                    e.printStackTrace()
                }
            }, { error ->
                Toast.makeText(this,"Error $error ", Toast.LENGTH_LONG).show()
            }
        )
        queue.add(jsonObjectRequest)
    }
}