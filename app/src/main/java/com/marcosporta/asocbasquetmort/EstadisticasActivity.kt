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
        tbEstadisticas=findViewById(R.id.tbEstadisticas)
        tbEstadisticas?.removeAllViews()
        var queue=Volley.newRequestQueue(this)
        var url1 ="http://192.168.0.81/morterense_partidos/estadisticasgf.php"

        var jsonObjectRequest=JsonObjectRequest(Request.Method.GET,url1,null,
            { response ->
                try {
                    var jsonArray = response.getJSONArray("data")
                    for (i in 0 until jsonArray.length()){
                        var jsonObject=jsonArray.getJSONObject(i)
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
        var queue=Volley.newRequestQueue(this)
        var url1 ="http://192.168.0.81/morterense_partidos/estadisticasgf.php"
        Toast.makeText(this,"Mas goles convertidos",Toast.LENGTH_SHORT).show()
        var jsonObjectRequest=JsonObjectRequest(Request.Method.GET,url1,null,
            { response ->
                try {
                    var jsonArray = response.getJSONArray("data")
                    for (i in 0 until jsonArray.length()){
                        var jsonObject=jsonArray.getJSONObject(i)
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
        var queue=Volley.newRequestQueue(this)
        var url2 ="http://192.168.0.81/morterense_partidos/estadisticasgc.php"
        Toast.makeText(this,"Mas goles concedidos",Toast.LENGTH_SHORT).show()
        var jsonObjectRequest=JsonObjectRequest(Request.Method.GET,url2,null,
            { response ->
                try {
                    var jsonArray = response.getJSONArray("data")
                    for (i in 0 until jsonArray.length()){
                        var jsonObject=jsonArray.getJSONObject(i)
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
        var queue=Volley.newRequestQueue(this)
        var url3 ="http://192.168.0.81/morterense_partidos/estadisticasptsl.php"
        Toast.makeText(this,"Mas puntos obtenidos de local",Toast.LENGTH_SHORT).show()
        var jsonObjectRequest=JsonObjectRequest(Request.Method.GET,url3,null,
            { response ->
                try {
                    var jsonArray = response.getJSONArray("data")
                    for (i in 0 until jsonArray.length()){
                        var jsonObject=jsonArray.getJSONObject(i)
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
        var queue=Volley.newRequestQueue(this)
        var url4 ="http://192.168.0.81/morterense_partidos/estadisticasptsv.php"
        Toast.makeText(this,"Mas puntos obtenidos de visitante",Toast.LENGTH_SHORT).show()
        var jsonObjectRequest=JsonObjectRequest(Request.Method.GET,url4,null,
            { response ->
                try {
                    var jsonArray = response.getJSONArray("data")
                    for (i in 0 until jsonArray.length()){
                        var jsonObject=jsonArray.getJSONObject(i)
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