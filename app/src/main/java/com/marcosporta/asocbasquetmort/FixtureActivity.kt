package com.marcosporta.asocbasquetmort

import android.app.DownloadManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TableLayout
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class FixtureActivity : AppCompatActivity() {

    var tbFixture:TableLayout?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fixture)

        //Poner icono en el Action Bar
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.mipmap.ic_launcher)
        supportActionBar?.setTitle("A.B.M.")

        tbFixture=findViewById(R.id.tbFixture)
        tbFixture?.removeAllViews()
        val queue=Volley.newRequestQueue(this)
        val url="https://marcosporta.site/morterenseapp/registros.php"

        val jsonObjectRequest=JsonObjectRequest(
            Request.Method.GET,url,null,
            { response ->
                try {
                    val jsonArray=response.getJSONArray("data")
                    var contador = 0
                    var fecha = 1
                    for(i in 0 until jsonArray.length() ){
                        val jsonObject=jsonArray.getJSONObject(i)

                        //Imprime encabezados de las fechas.
                        if(contador === i && fecha <= 14){
                            val registro2 = LayoutInflater.from(this).inflate(R.layout.tabla_row_fecha, null, false)
                            val colNumeroFecha = registro2.findViewById<View>(R.id.colNumeroFecha) as TextView
                            colNumeroFecha.text = "Fecha: " + fecha
                            tbFixture?.addView(registro2)
                            fecha += 1
                            contador += 3
                        }

                        val registro=LayoutInflater.from(this).inflate(R.layout.tabla_row_fixture,null,false)
                        val colFecha=registro.findViewById<View>(R.id.colFecha) as TextView
                        val colEquipoL=registro.findViewById<View>(R.id.colEquipoL) as TextView
                        val colPtsL=registro.findViewById<View>(R.id.colPtsL) as TextView
                        val colEstado=registro.findViewById<View>(R.id.colEstado) as TextView
                        val colPtsV=registro.findViewById<View>(R.id.colPtsV) as TextView
                        val colEquipoV=registro.findViewById<View>(R.id.colEquipoV) as TextView
                        colFecha.text=jsonObject.getString("fecha")
                        colEquipoL.text=jsonObject.getString("equipol")
                        colPtsL.text=jsonObject.getString("ptsl")
                        colEstado.text=jsonObject.getString("estado")
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