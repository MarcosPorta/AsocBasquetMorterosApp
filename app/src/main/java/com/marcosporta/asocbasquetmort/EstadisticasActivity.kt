package com.marcosporta.asocbasquetmort

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
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FFA000")))

        tbEstadisticas=findViewById(R.id.tbEstadisticas)

        //SPINNERS
        //Zonas
        spinnerEstadisticas = findViewById(R.id.sp_estadisticas)
        val listaEquipos = resources.getStringArray(R.array.equipos)

        val adaptadorEst = ArrayAdapter(this,R.layout.style_spinner,listaEquipos)
        spinnerEstadisticas.adapter = adaptadorEst

        spinnerEstadisticas.onItemSelectedListener = object:
            AdapterView.OnItemSelectedListener{
            //Cuando tengo un elemento seleccionado.
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                //println("MIRAR ACA -------->>> ${spinnerEstadisticas.selectedItemPosition}")
                seleccion = spinnerEstadisticas.selectedItem.toString()
                when (spinnerEstadisticas.selectedItemPosition) {
                    0 -> llenarTablaEstadisticas("https://marcosporta.site/morterenseapp/estadisticasgf2.php","Promedio de goles convertidos")
                    1 -> llenarTablaEstadisticas("https://marcosporta.site/morterenseapp/estadisticasgc2.php","Promedio de goles concedidos")
                    2 -> llenarTablaEstadisticas("https://marcosporta.site/morterenseapp/estadisticasptsl2.php","Puntos obtenidos de local")
                    else -> {
                        llenarTablaEstadisticas("https://marcosporta.site/morterenseapp/estadisticasptsv2.php","Puntos obtenidos de visitante")
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
                        val colEst=registro.findViewById<View>(R.id.colEstadistica) as TextView
                        colEquipoEst.text=jsonObject.getString("equipo")
                        colEst.text=jsonObject.getString("result")
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