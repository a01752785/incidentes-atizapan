package mx.itesm.incidentesatizapan.viewmodel

import android.graphics.drawable.Drawable
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.itesm.incidentesatizapan.Climadata
import mx.itesm.incidentesatizapan.TemperatureCategory
import mx.itesm.incidentesatizapan.TemperatureCategory.TemperatureCategoryEnum
import mx.itesm.incidentesatizapan.WindSpeedCategory
import mx.itesm.incidentesatizapan.WindSpeedCategory.WindSpeedCategoryEnum
import mx.itesm.incidentesatizapan.model.WeatherAPI

/**
 * @author Adrian Bravo
 * ViewModel del fragmento main
 */
class MainViewModel : ViewModel() {

    val model = WeatherAPI()
    var climadata = MutableLiveData<Climadata>()
    var dia1 = MutableLiveData<String>()
    var icon1 = MutableLiveData<Int>()
    var windSpeedCategory = MutableLiveData<WindSpeedCategory>()
    val temperatureCategory = MutableLiveData<TemperatureCategory>()

    /**
     * Llama a la funcion getClima del model de la API del Clima
     */

    fun clima(){
        climadata.value = Climadata.newBuilder().build()
        climadata.value = model.getClima("eef42780d6mshc08bc5fad1ef6b2p146a49jsn4af0732a1828", 1)
    }

    /**
     * LLama la funcion del modelo que determina el dia de la semana a partir de un valor numerico
     */
    fun getdia(dia: Int){
        dia1.value = model.getDia(dia)
    }

    /**
     * Llama la funcion del modelo que determina que icono es apropiado en base a la descripcion del clima
     * proporcionada por la API del clima
     */
    fun getIcon(icon: String){
        icon1.value = model.getIcon(icon)
    }

    fun getWindSpeedCategory(windSpeed: Double) {
        windSpeedCategory.value = model.getWindSpeedCategory(windSpeed)
    }

    fun getWindSpeedCategoryName(windSpeedCategoryEnum: WindSpeedCategoryEnum): String {
        return model.getWindSpeedCategoryName(windSpeedCategoryEnum)
    }

    fun getTemperatureCategory(temperature: Double) {
        temperatureCategory.value = model.getTemperatureCategory(temperature)
    }

    fun getTemperatureCategoryName(temperatureCategoryEnum: TemperatureCategoryEnum): String {
        return model.getTemperatureCategoryName(temperatureCategoryEnum)
    }
}