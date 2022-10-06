package mx.itesm.incidentesatizapan.model


import android.util.Log
import com.google.protobuf.util.JsonFormat
import com.squareup.okhttp.*
import mx.itesm.incidentesatizapan.Climadata
import okhttp3.ResponseBody
import okio.IOException

class WeatherAPI {
    var r = ResponseBody
    fun getClima(){
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://weatherbit-v1-mashape.p.rapidapi.com/forecast/daily?lat=19.5178211&lon=-99.3611396&units=metric&lang=es")
            .get()
            .addHeader("X-RapidAPI-Key", "eef42780d6mshc08bc5fad1ef6b2p146a49jsn4af0732a1828")
            .addHeader("X-RapidAPI-Host", "weatherbit-v1-mashape.p.rapidapi.com")
            .build()

        val response = client.newCall(request).enqueue(object : Callback {
            override fun onFailure(request: Request?, e: IOException?) {
                Log.d("fallo","fallo")
            }

            override fun onResponse(response: Response?) {
                //Log.d("funciono",response!!.body()!!.string())
                val climabuilder: Climadata.Builder = Climadata.newBuilder()
                JsonFormat.parser().ignoringUnknownFields().merge(response!!.body().string(),climabuilder)
                val clima1 = climabuilder.build()
                Log.d("mensaje",clima1.toString())

            }
        })

        //val response = client.newCall(request).execute().body()


    }


}