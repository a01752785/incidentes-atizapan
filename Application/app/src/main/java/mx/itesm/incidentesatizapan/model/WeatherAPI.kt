package mx.itesm.incidentesatizapan.model


import android.graphics.drawable.Drawable
import android.util.Log
import com.google.common.io.Closeables.close
import com.google.protobuf.util.JsonFormat
import com.squareup.okhttp.*
import mx.itesm.incidentesatizapan.Climadata
import mx.itesm.incidentesatizapan.R
import okhttp3.ResponseBody
import okhttp3.internal.platform.android.AndroidLogHandler.close
import okio.IOException
import org.checkerframework.checker.units.qual.C
import java.util.concurrent.CountDownLatch

/**
 * @author Adrian Bravo
 * Clase que hace llamada a API de Clima
 */

class WeatherAPI {
    /**
     * Funcion que hace la llamada al servicio de API del clima
     */
    fun getClima(api_key: String, i : Int) : Climadata {
        var clima1 = Climadata.newBuilder().build()
        val client = OkHttpClient()
        //Request con sus headers para la llamada GET
        val request = Request.Builder()
            .url("https://weatherbit-v1-mashape.p.rapidapi.com/forecast/daily?lat=19.5178211&lon=-99.3611396&units=metric&lang=es")
            .get()
            .addHeader("X-RapidAPI-Key", api_key)
            .addHeader("X-RapidAPI-Host", "weatherbit-v1-mashape.p.rapidapi.com")
            .build()
            //eef42780d6mshc08bc5fad1ef6b2p146a49jsn4af0732a1828
        //Se empieza una countdown para que espere que termine la llamada en el thread creado por
        //enqueue y retorne lo que se llamo
        val countdown = CountDownLatch(1)
        val response = client.newCall(request).enqueue(object : Callback {
            override fun onFailure(request: Request?, e: IOException?) {
                Log.d("fallo","fallo")
                countdown.countDown()
            }

            override fun onResponse(response: Response?) {
                if(response!!.code() == 429){
                    Log.d("fallo","fallo")
                    if(i == 1){
                        clima1 = getClima("a2b8dbea17msh31a94be81358e11p1d310ajsn08b09c647c65",i+1)
                    }
                    if(i == 2){
                        clima1 = getClima("5b3485ce59msh30281506cbfb394p144551jsn947ad79e1bbf",i+1)
                    }
                }
                else {
                    val climabuilder: Climadata.Builder = Climadata.newBuilder()
                    JsonFormat.parser().ignoringUnknownFields().merge(response!!.body().string(), climabuilder)
                    clima1 = climabuilder.build()
                }
                countdown.countDown()
            }
        })

        countdown.await()
        Log.d("mensaje",clima1.toString())
        return clima1


    }

    /**
     * Funcion que regresa el dia de la semana que fue solicitado en base a un entero
     */
    fun getDia(dia: Int): String{
        if(dia == 1){
            return "Domingo"
        }
        if(dia == 2){
            return "Lunes"
        }
        if(dia == 3){
            return "Martes"
        }
        if(dia == 4){
            return "Miércoles"
        }
        if(dia == 5){
            return "Jueves"
        }
        if(dia == 6){
            return "Viernes"
        }
        if(dia == 7){
            return "Sábado"
        }
        return "error"
    }

    /**
     * Funcion que regresa el icono correspondiente a un codigo de icono proporsionado por la API del clima
     */
    fun getIcon(icon: String): Int{
        var iconnew = R.drawable.sol_64
        if(icon[0] == 'c' || icon[0] == 'a'){
            if(icon == "c04d" || icon == "co2n"){
                iconnew = R.drawable.nublado_64
            }
            else if(icon == "c01d"){
                iconnew = R.drawable.sol_64
            }
            else{
                iconnew = R.drawable.parcialmentenublado_64
            }
        }
        if(icon[0] == 'u' || icon[0] == 'r' || icon[0] == 'd' || icon[0] == 's'){
            iconnew = R.drawable.lluvia_64
        }
        if(icon[0] == 't'){
            iconnew = R.drawable.rayo_64
        }
        return iconnew
    }

}