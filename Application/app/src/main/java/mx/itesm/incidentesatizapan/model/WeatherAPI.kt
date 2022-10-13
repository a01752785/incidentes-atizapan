package mx.itesm.incidentesatizapan.model


import android.util.Log
import com.google.common.io.Closeables.close
import com.google.protobuf.util.JsonFormat
import com.squareup.okhttp.*
import mx.itesm.incidentesatizapan.Climadata
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
    fun getClima() : Climadata {
        var clima1 = Climadata.newBuilder().build()
        val client = OkHttpClient()
        //Request con sus headers para la llamada GET
        val request = Request.Builder()
            .url("https://weatherbit-v1-mashape.p.rapidapi.com/forecast/daily?lat=19.5178211&lon=-99.3611396&units=metric&lang=es")
            .get()
            .addHeader("X-RapidAPI-Key", "eef42780d6mshc08bc5fad1ef6b2p146a49jsn4af0732a1828")
            .addHeader("X-RapidAPI-Host", "weatherbit-v1-mashape.p.rapidapi.com")
            .build()

        //Se empieza una countdown para que espere que termine la llamada en el thread creado por
        //enqueue y retorne lo que se llamo
        val countdown = CountDownLatch(1)
        val response = client.newCall(request).enqueue(object : Callback {
            override fun onFailure(request: Request?, e: IOException?) {
                Log.d("fallo","fallo")
                countdown.countDown()
            }

            override fun onResponse(response: Response?) {
                val climabuilder: Climadata.Builder = Climadata.newBuilder()
                JsonFormat.parser().ignoringUnknownFields().merge(response!!.body().string(),climabuilder)
                clima1 = climabuilder.build()
                countdown.countDown()
            }
        })

        countdown.await()
        Log.d("mensaje",clima1.toString())
        return clima1


    }
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
            return "Miercoles"
        }
        if(dia == 5){
            return "Jueves"
        }
        if(dia == 6){
            return "Viernes"
        }
        if(dia == 7){
            return "Sabado"
        }
        return "error"
    }

}