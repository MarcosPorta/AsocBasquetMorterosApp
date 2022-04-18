package com.marcosporta.asocbasquetmort

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class EstadisticasActivity : AppCompatActivity() {

    var tbEstadisticas:TableLayout?=null
    lateinit var spinnerEstadisticas:Spinner
    var seleccion:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estadisticas)

        //Poner boton regrasar y titulo en el Action Bar
        supportActionBar?.title = "A.B.M."
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        tbEstadisticas=findViewById(R.id.tbEstadisticas)

        //SPINNERS
        //Zonas
        spinnerEstadisticas = findViewById(R.id.sp_estadisticas)
        val listaEquipos = resources.getStringArray(R.array.equipos)

        val adaptadorEst = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,listaEquipos)
        spinnerEstadisticas.adapter = adaptadorEst

        spinnerEstadisticas.onItemSelectedListener = object:
            AdapterView.OnItemSelectedListener{
            //Cuando tengo un elemento seleccionado.
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                //println("MIRAR ACA -------->>> ${spinnerEstadisticas.selectedItemPosition}")
                seleccion = spinnerEstadisticas.selectedItem.toString()
                when (spinnerEstadisticas.selectedItemPosition) {
                    0 -> llenarTablaEstadisticas("https://marcosporta.site/morterenseapp/estadisticasgf.php","Promedio de goles convertidos")
                    1 -> llenarTablaEstadisticas("https://marcosporta.site/morterenseapp/estadisticasgc.php","Promedio de goles concedidos")
                    2 -> llenarTablaEstadisticas("https://marcosporta.site/morterenseapp/estadisticasptsl.php","Puntos obtenidos de local")
                    else -> {
                        llenarTablaEstadisticas("https://marcosporta.site/morterenseapp/estadisticasptsv.php","Puntos obtenidos de visitante")
                    }
                }
            }
            //Cuando NO tengo un elemento seleccionado.
            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
    }

    //Funcion general, al pasarle una url y un texto te completa la tabla
    fun llenarTablaEstadisticas(url : String, textoToast : String){
        tbEstadisticas?.removeAllViews()

        val queue=Volley.newRequestQueue(this)

        Toast.makeText(this,textoToast,Toast.LENGTH_SHORT).show()
        val jsonObjectRequest=JsonObjectRequest(Request.Method.GET,url,null,
            { response ->
                try {
                    val jsonArray = response.getJSONArray("data")
                    for (i in 0 until jsonArray.length()){
                        val jsonObject=jsonArray.getJSONObject(i)
                        val registro=LayoutInflater.from(this).inflate(R.layout.tabla_row_estadisticas,null,false)
                        val colEquipoEst=registro.findViewById<View>(R.id.colEquipoEst) as TextView
                        val colGFEst=registro.findViewById<View>(R.id.colGFEst) as TextView
                        val colGCEst=registro.findViewById<View>(R.id.colGCEst) as TextView
                        val colPtsLEst=registro.findViewById<View>(R.id.colPtsLEst) as TextView
                        val colPtsVEst=registro.findViewById<View>(R.id.colPtsVEst) as TextView
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
                Toast.makeText(this,"Error $error ",Toast.LENGTH_LONG).show()
            }
        )
        queue.add(jsonObjectRequest)
    }
}