package mx.itesm.incidentesatizapan.model


import android.graphics.drawable.Drawable
import android.util.Log
import com.google.common.io.Closeables.close
import com.google.protobuf.util.JsonFormat
import com.squareup.okhttp.*
import mx.itesm.incidentesatizapan.Climadata
import mx.itesm.incidentesatizapan.R
import mx.itesm.incidentesatizapan.TemperatureCategory
import mx.itesm.incidentesatizapan.WindSpeedCategory
import mx.itesm.incidentesatizapan.WindSpeedCategory.WindSpeedCategoryEnum
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

    /**
     * Returns a category according to the wind speed.
     * @param windSpeed, the wind speed expressed in meters per second.
     * @return WindSpeedCategory, the determined category.
     */
    fun getWindSpeedCategory(windSpeed: Double): WindSpeedCategory {
        val windSpeedCategory = WindSpeedCategory.newBuilder()
        // Less than 21 km/h
        if (windSpeed < 5.84) {
            windSpeedCategory.category = WindSpeedCategoryEnum.SLOW
            windSpeedCategory.recommendationMessage = "Realice sus actividades con normalidad."
            windSpeedCategory.dangerous = false
        }
        // Less than 41 km/h
        else if (windSpeed < 11.39) {
            windSpeedCategory.category = WindSpeedCategoryEnum.MODERATE
            windSpeedCategory.recommendationMessage = "Realice sus actividades con normalidad."
            windSpeedCategory.dangerous = false
        }
        // Less than 71 km/h
        else if (windSpeed < 19.73) {
            windSpeedCategory.category = WindSpeedCategoryEnum.STRONG
            windSpeedCategory.recommendationMessage = "Resguárdese en un lugar seguro."
            windSpeedCategory.dangerous = true
        }
        // Less than 120 km/h
        else if (windSpeed < 33.4) {
            windSpeedCategory.category = WindSpeedCategoryEnum.VERY_STRONG
            windSpeedCategory.recommendationMessage = "Resguárdese en un lugar seguro."
            windSpeedCategory.dangerous = true
        }
        else {
            windSpeedCategory.category = WindSpeedCategoryEnum.HURRICANE
            windSpeedCategory.recommendationMessage = "Resguárdese en un lugar seguro."
            windSpeedCategory.dangerous = true
        }
        return windSpeedCategory.build()
    }

    /**
     * Returns a string with the name of the wind speed category.
     * @param category, the category which name has to be determined.
     * @return String, the name of the category.
     */
    fun getWindSpeedCategoryName(category: WindSpeedCategoryEnum): String {
        return when (category) {
            WindSpeedCategoryEnum.SLOW -> "LENTA"
            WindSpeedCategoryEnum.MODERATE -> "MODERADA"
            WindSpeedCategoryEnum.STRONG -> "FUERTE"
            WindSpeedCategoryEnum.VERY_STRONG -> "MUY FUERTE"
            WindSpeedCategoryEnum.HURRICANE -> "HURACANADA"
        }
    }

    /**
     * Returns a category according to the temperature.
     * @param temperature, the temperature expressed in Celsius degrees.
     * @return Temperature, the determined category.
     */
    fun getTemperatureCategory(temperature: Double): TemperatureCategory {
        val temperatureCategory = TemperatureCategory.newBuilder()
        if (temperature < 0) {
            temperatureCategory.category = TemperatureCategory.TemperatureCategoryEnum.VERY_COLD
            temperatureCategory.recommendationMessage = "Resguárdese del frío."
            temperatureCategory.dangerous = true
        }
        else if (temperature < 5) {
            temperatureCategory.category = TemperatureCategory.TemperatureCategoryEnum.COLD
            temperatureCategory.recommendationMessage = "Resguárdese del frío."
            temperatureCategory.dangerous = true
        }
        else if (temperature < 25) {
            temperatureCategory.category = TemperatureCategory.TemperatureCategoryEnum.AMBIENCE
            temperatureCategory.recommendationMessage = "Realice sus actividades con normalidad."
            temperatureCategory.dangerous = false
        }
        else if (temperature < 35) {
            temperatureCategory.category = TemperatureCategory.TemperatureCategoryEnum.HOT
            temperatureCategory.recommendationMessage = "Resguárdese en la sombra y manténgase hidratado."
            temperatureCategory.dangerous = true
        }
        else {
            temperatureCategory.category = TemperatureCategory.TemperatureCategoryEnum.VERY_HOT
            temperatureCategory.recommendationMessage = "Resguárdese en la sombra y manténgase hidratado."
            temperatureCategory.dangerous = true
        }
        return temperatureCategory.build()
    }

    /**
     * Returns a string with the name of the temperature category.
     * @param category, the category which name has to be determined.
     * @return String, the name of the category.
     */
    fun getTemperatureCategoryName(category: TemperatureCategory.TemperatureCategoryEnum): String {
        return when (category) {
            TemperatureCategory.TemperatureCategoryEnum.VERY_COLD -> "FRÍO EXTREMO"
            TemperatureCategory.TemperatureCategoryEnum.COLD -> "FRÍO"
            TemperatureCategory.TemperatureCategoryEnum.AMBIENCE -> "TEMPLADO"
            TemperatureCategory.TemperatureCategoryEnum.HOT -> "CALOR"
            TemperatureCategory.TemperatureCategoryEnum.VERY_HOT -> "CALOR EXTREMO"
        }
    }
}