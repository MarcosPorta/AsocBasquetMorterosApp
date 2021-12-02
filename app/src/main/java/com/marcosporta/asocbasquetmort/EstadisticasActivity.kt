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

class EstadisticasActivity : AppCompatActivity() {

    var tbEstadisticas:TableLayout?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estadisticas)

        //Poner icono y titulo en el Action Bar
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.mipmap.ic_launcher)
        supportActionBar?.setTitle("A.B.M.")

        tbEstadisticas=findViewById(R.id.tbEstadisticas)
        tbEstadisticas?.removeAllViews()
        val queue=Volley.newRequestQueue(this)
        val url1 ="https://marcosporta.site/morterenseapp/estadisticasgf.php"

        val jsonObjectRequest=JsonObjectRequest(Request.Method.GET,url1,null,
            { response ->
                try {
                    val jsonArray = response.getJSONArray("data")
                    for (i in 0 until jsonArray.length()){
                        val jsonObject=jsonArray.getJSONObject(i)
                        val registro=LayoutInflater.from(this).inflate(R.layout.tabla_row_estadisticas,null,false)
                        val colEquipoEst=registro.findViewById<View>(R.id.colEquipoEst) as TextView
                        var colGFEst=registro.findViewById<View>(R.id.colGFEst) as TextView
                        var colGCEst=registro.findViewById<View>(R.id.colGCEst) as TextView
                        var colPtsLEst=registro.findViewById<View>(R.id.colPtsLEst) as TextView
                        var colPtsVEst=registro.findViewById<View>(R.id.colPtsVEst) as TextView
                        colEquipoEst.text=jsonObject.getString("equipo")
                        colGFEst.text=jsonObject.getString("gf")
                        colGCEst.text=jsonObject.getString("gc")
                        colPtsLEst.text=jsonObject.getString("ptsl")
                        colPtsVEst.text=jsonObject.getString("ptsv")
                        tbEstadisticas?.addView(registro)
                    }
                }catch (e: JSONException){
                    e.printStackTrace()
                }
            }, { error ->

                }
            )
        queue.add(jsonObjectRequest)

    }
    fun clickTablaGF(view: View){
        tbEstadisticas?.removeAllViews()
        val queue=Volley.newRequestQueue(this)
        val url1 ="https://marcosporta.site/morterenseapp/estadisticasgf.php"
        Toast.makeText(this,"Mas goles convertidos",Toast.LENGTH_SHORT).show()
        val jsonObjectRequest=JsonObjectRequest(Request.Method.GET,url1,null,
            { response ->
                try {
                    val jsonArray = response.getJSONArray("data")
                    for (i in 0 until jsonArray.length()){
                        val jsonObject=jsonArray.getJSONObject(i)
                        val registro=LayoutInflater.from(this).inflate(R.layout.tabla_row_estadisticas,null,false)
                        val colEquipoEst=registro.findViewById<View>(R.id.colEquipoEst) as TextView
                        var colGFEst=registro.findViewById<View>(R.id.colGFEst) as TextView
                        var colGCEst=registro.findViewById<View>(R.id.colGCEst) as TextView
                        var colPtsLEst=registro.findViewById<View>(R.id.colPtsLEst) as TextView
                        var colPtsVEst=registro.findViewById<View>(R.id.colPtsVEst) as TextView
                        colEquipoEst.text=jsonObject.getString("equipo")
                        colGFEst.text=jsonObject.getString("gf")
                        colGCEst.text=jsonObject.getString("gc")
                        colPtsLEst.text=jsonObject.getString("ptsl")
                        colPtsVEst.text=jsonObject.getString("ptsv")
                        tbEstadisticas?.addView(registro)
                    }
                }catch (e: JSONException){
                    e.printStackTrace()
                }
            }, { error ->

            }
        )
        queue.add(jsonObjectRequest)
    }
    fun clickTablaGC(view: View){
        tbEstadisticas?.removeAllViews()
        val queue=Volley.newRequestQueue(this)
        val url2 ="https://marcosporta.site/morterenseapp/estadisticasgc.php"
        Toast.makeText(this,"Mas goles concedidos",Toast.LENGTH_SHORT).show()
        val jsonObjectRequest=JsonObjectRequest(Request.Method.GET,url2,null,
            { response ->
                try {
                    val jsonArray = response.getJSONArray("data")
                    for (i in 0 until jsonArray.length()){
                        val jsonObject=jsonArray.getJSONObject(i)
                        val registro=LayoutInflater.from(this).inflate(R.layout.tabla_row_estadisticas,null,false)
                        val colEquipoEst=registro.findViewById<View>(R.id.colEquipoEst) as TextView
                        var colGFEst=registro.findViewById<View>(R.id.colGFEst) as TextView
                        var colGCEst=registro.findViewById<View>(R.id.colGCEst) as TextView
                        var colPtsLEst=registro.findViewById<View>(R.id.colPtsLEst) as TextView
                        var colPtsVEst=registro.findViewById<View>(R.id.colPtsVEst) as TextView
                        colEquipoEst.text=jsonObject.getString("equipo")
                        colGFEst.text=jsonObject.getString("gf")
                        colGCEst.text=jsonObject.getString("gc")
                        colPtsLEst.text=jsonObject.getString("ptsl")
                        colPtsVEst.text=jsonObject.getString("ptsv")
                        tbEstadisticas?.addView(registro)
                    }
                }catch (e: JSONException){
                    e.printStackTrace()
                }
            }, { error ->

            }
        )
        queue.add(jsonObjectRequest)
    }
    fun clickTablaPTSL(view: View){
        tbEstadisticas?.removeAllViews()
        val queue=Volley.newRequestQueue(this)
        val url3 ="https://marcosporta.site/morterenseapp/estadisticasptsl.php"
        Toast.makeText(this,"Mas puntos obtenidos de local",Toast.LENGTH_SHORT).show()
        val jsonObjectRequest=JsonObjectRequest(Request.Method.GET,url3,null,
            { response ->
                try {
                    val jsonArray = response.getJSONArray("data")
                    for (i in 0 until jsonArray.length()){
                        val jsonObject=jsonArray.getJSONObject(i)
                        val registro=LayoutInflater.from(this).inflate(R.layout.tabla_row_estadisticas,null,false)
                        val colEquipoEst=registro.findViewById<View>(R.id.colEquipoEst) as TextView
                        var colGFEst=registro.findViewById<View>(R.id.colGFEst) as TextView
                        var colGCEst=registro.findViewById<View>(R.id.colGCEst) as TextView
                        var colPtsLEst=registro.findViewById<View>(R.id.colPtsLEst) as TextView
                        var colPtsVEst=registro.findViewById<View>(R.id.colPtsVEst) as TextView
                        colEquipoEst.text=jsonObject.getString("equipo")
                        colGFEst.text=jsonObject.getString("gf")
                        colGCEst.text=jsonObject.getString("gc")
                        colPtsLEst.text=jsonObject.getString("ptsl")
                        colPtsVEst.text=jsonObject.getString("ptsv")
                        tbEstadisticas?.addView(registro)
                    }
                }catch (e: JSONException){
                    e.printStackTrace()
                }
            }, { error ->

            }
        )
        queue.add(jsonObjectRequest)
    }
    fun clickTablaPTSV(view: View){
        tbEstadisticas?.removeAllViews()
        val queue=Volley.newRequestQueue(this)
        val url4 ="https://marcosporta.site/morterenseapp/estadisticasptsv.php"
        Toast.makeText(this,"Mas puntos obtenidos de visitante",Toast.LENGTH_SHORT).show()
        val jsonObjectRequest=JsonObjectRequest(Request.Method.GET,url4,null,
            { response ->
                try {
                    val jsonArray = response.getJSONArray("data")
                    for (i in 0 until jsonArray.length()){
                        val jsonObject=jsonArray.getJSONObject(i)
                        val registro=LayoutInflater.from(this).inflate(R.layout.tabla_row_estadisticas,null,false)
                        val colEquipoEst=registro.findViewById<View>(R.id.colEquipoEst) as TextView
                        var colGFEst=registro.findViewById<View>(R.id.colGFEst) as TextView
                        var colGCEst=registro.findViewById<View>(R.id.colGCEst) as TextView
                        var colPtsLEst=registro.findViewById<View>(R.id.colPtsLEst) as TextView
                        var colPtsVEst=registro.findViewById<View>(R.id.colPtsVEst) as TextView
                        colEquipoEst.text=jsonObject.getString("equipo")
                        colGFEst.text=jsonObject.getString("gf")
                        colGCEst.text=jsonObject.getString("gc")
                        colPtsLEst.text=jsonObject.getString("ptsl")
                        colPtsVEst.text=jsonObject.getString("ptsv")
                        tbEstadisticas?.addView(registro)
                    }
                }catch (e: JSONException){
                    e.printStackTrace()
                }
            }, { error ->

            }
        )
        queue.add(jsonObjectRequest)
    }
}