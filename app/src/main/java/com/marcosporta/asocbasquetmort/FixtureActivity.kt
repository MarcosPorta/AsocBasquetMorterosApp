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
import android.widget.*
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

class FixtureActivity : AppCompatActivity() {

    lateinit var mAdView : AdView
    val testId= Arrays.asList("572A1A67BA6623DBD9D945D4043174CB")
    val configuracion= RequestConfiguration.Builder().setTestDeviceIds(testId).build()

    var tbFixture:TableLayout?=null
    lateinit var spinnerFixture:Spinner
    var torneoSeleccionado:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fixture)

        MobileAds.setRequestConfiguration(configuracion)
        RequestConfiguration.Builder().setTestDeviceIds(testId)
        MobileAds.initialize(this) {}

        //SPINNERS
        //Zonas
        spinnerFixture = findViewById(R.id.sp_fixture)
        val listaZona = resources.getStringArray(R.array.torneos)

        val adaptadorFixture = ArrayAdapter(this,R.layout.style_spinner,listaZona)
        spinnerFixture.adapter = adaptadorFixture

        spinnerFixture.onItemSelectedListener = object:
            AdapterView.OnItemSelectedListener{
            //Cuando tengo un elemento seleccionado.
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                torneoSeleccionado = spinnerFixture.selectedItem.toString()

                //Funcionalidad para realizar las consultas.
                if(torneoSeleccionado != "Seleccionar torneo"){
                    consultarPartidos(torneoSeleccionado)
                }
            }
            //Cuando NO tengo un elemento seleccionado.
            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        //Banner
        mAdView = findViewById(R.id.bannerFixture)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        //Poner boton regresar y titulo en el Action Bar
        supportActionBar?.title = "A.B.M."
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FFA000")))

        tbFixture=findViewById(R.id.tbFixture)
    }

    //Funcion al elegir en el spinner
    fun consultarPartidos(torneo:String){
        tbFixture?.removeAllViews()
        val queue=Volley.newRequestQueue(this)
        val url="https://marcosporta.site/morterenseapp/partidos$torneo.php"
        val jsonObjectRequest=JsonObjectRequest(
            Request.Method.GET,url,null,
            { response ->
                try {
                    val jsonArray=response.getJSONArray("data")

                    if (jsonArray.length() == 0) {
                        Toast.makeText(this, "No hay partidos", Toast.LENGTH_LONG).show()
                    }else{
                        var contador = 0
                        var fecha = 1
                        var hora = ""
                        var titulo = "null"
                        for(i in 0 until jsonArray.length()){
                            val jsonObject=jsonArray.getJSONObject(i)

                            //Accediendo a un campo de la base de datos (fecha)
                            val fechaBD = jsonObject.getInt("fecha")
                            val horaBD = jsonObject.getString("diahora")
                            val ptslocalBD = jsonObject.getString("ptsl")
                            val tituloBD = jsonObject.getString("titulo")
                            val tipoBD = jsonObject.getString("tipo")

                            //Imprimir "titulo" si es de playoffs sino fecha
                            if(tipoBD == "playoffs" && tituloBD != titulo){
                                val registro2 = LayoutInflater.from(this).inflate(R.layout.tabla_row_fecha, null, false)
                                val colNumeroFecha = registro2.findViewById<View>(R.id.colNumeroFecha) as TextView
                                colNumeroFecha.text = tituloBD
                                tbFixture?.addView(registro2)
                                titulo = tituloBD
                            }
                            if(tipoBD == "regular" && fechaBD != contador){
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
                                tbFixture?.addView(registro3)
                                hora = horaBD
                            }

                            val registro=LayoutInflater.from(this).inflate(R.layout.tabla_row_fixture,null,false)
                            val colEquipoL=registro.findViewById<View>(R.id.colEquipoL) as TextView
                            val colPtsL=registro.findViewById<View>(R.id.colPtsL) as TextView
                            val colPtsV=registro.findViewById<View>(R.id.colPtsV) as TextView
                            val colEquipoV=registro.findViewById<View>(R.id.colEquipoV) as TextView
                            val colEscL=registro.findViewById<ImageView>(R.id.imageEscL)
                            val colEscV=registro.findViewById<ImageView>(R.id.imageEscV)
                            colEquipoL.text=jsonObject.getString("equipol")
                            colPtsL.text=jsonObject.getString("ptsl")
                            colPtsV.text=jsonObject.getString("ptsv")
                            colEquipoV.text=jsonObject.getString("equipov")

                            val equipol = jsonObject.getString("equipol")
                            val imageFileName = equipoImagenMap[equipol]
                            val equipov = jsonObject.getString("equipov")
                            val imageFileNameV = equipoImagenMap[equipov]

                            if(imageFileName != null && imageFileNameV != null){
                                val bitmap = getBitmapFromAssets(this,imageFileName)
                                val bitmapV = getBitmapFromAssets(this,imageFileNameV)
                                colEscL.setImageBitmap(bitmap)
                                colEscV.setImageBitmap(bitmapV)
                            }
                            tbFixture?.addView(registro)
                        }
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