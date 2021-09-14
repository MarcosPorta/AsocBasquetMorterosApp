package com.marcosporta.asocbasquetmort

import android.app.DownloadManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TableLayout
import android.widget.TextView
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
        tbFixture=findViewById(R.id.tbFixture)
        tbFixture?.removeAllViews()
        var queue=Volley.newRequestQueue(this)
        var url="http://marcosporta.site/morterenseapp/registros.php"

        var jsonObjectRequest=JsonObjectRequest(
            Request.Method.GET,url,null,
            { response ->
                try {
                    var jsonArray=response.getJSONArray("data")
                    for(i in 0 until jsonArray.length() ){
                        var jsonObject=jsonArray.getJSONObject(i)
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

                }
            )
        queue.add(jsonObjectRequest)
        /*
        for (i in 0 until 8){
            val registro=LayoutInflater.from(this).inflate(R.layout.tabla_row_fixture,null,false)
            //Aca puedo pensar en como meter los "titulos" de cada fecha
            val colFecha=registro.findViewById<View>(R.id.colFecha) as TextView
            val colEquipoL=registro.findViewById<View>(R.id.colEquipoL) as TextView
            val colPtsL=registro.findViewById<View>(R.id.colPtsL) as TextView
            val colEstado=registro.findViewById<View>(R.id.colEstado) as TextView
            val colPtsV=registro.findViewById<View>(R.id.colPtsV) as TextView
            val colEquipoV=registro.findViewById<View>(R.id.colEquipoV) as TextView
            colFecha.text="Fecha $i"
            colEquipoL.text="EquipoL $i"
            colPtsL.text="PtsL $i"
            colEstado.text="Estado $i"
            colPtsV.text="PtsV $i"
            colEquipoV.text="EquipoV $i"
            tbFixture?.addView(registro)
        }
         */
    }
}