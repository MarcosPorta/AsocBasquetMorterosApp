package com.marcosporta.asocbasquetmort

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

class PosicionesActivity : AppCompatActivity() {

    var tbPosiciones:TableLayout?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posiciones)
        tbPosiciones=findViewById(R.id.tbPosiciones)
        tbPosiciones?.removeAllViews()
        var queue=Volley.newRequestQueue(this)
        var url= "http://marcosporta.site/morterenseapp/registrosp.php"

        //Creacion de json object request
        var jsonObjectRequest=JsonObjectRequest(Request.Method.GET,url,null,
            {response ->
                try {
                    var jsonArray= response.getJSONArray("data")
                    for (i in 0 until jsonArray.length() ){
                        var jsonObject= jsonArray.getJSONObject(i)
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
                    //Falta imprimir error.
                }
            )
        queue.add(jsonObjectRequest)
    }

}