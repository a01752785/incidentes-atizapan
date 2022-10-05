package mx.itesm.incidentesatizapan.model

import com.squareup.okhttp.Callback
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import com.squareup.okhttp.Response
import java.io.IOException

class WeatherAPI {
    val client = OkHttpClient()

    val request = Request.Builder()
        .url("https://weatherbit-v1-mashape.p.rapidapi.com/forecast/daily?lat=19.5178211&lon=-99.3611396&units=metric&lang=es")
        .get()
        .addHeader("X-RapidAPI-Key", "eef42780d6mshc08bc5fad1ef6b2p146a49jsn4af0732a1828")
        .addHeader("X-RapidAPI-Host", "weatherbit-v1-mashape.p.rapidapi.com")
        .build()


    val response = client.newCall(request).enqueue(object : Callback{
        override fun onFailure(request: Request?, e: IOException?) {
            println("fallo")
        }

        override fun onResponse(response: Response?) {
            println("funciono")

        }
    })

}