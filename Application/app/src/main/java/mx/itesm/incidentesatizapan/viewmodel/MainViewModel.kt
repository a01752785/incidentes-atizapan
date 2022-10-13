package mx.itesm.incidentesatizapan.viewmodel

import android.graphics.drawable.Drawable
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.itesm.incidentesatizapan.Climadata
import mx.itesm.incidentesatizapan.model.WeatherAPI

/**
 * @author Adrian Bravo
 * ViewModel del fragmento main
 */
class MainViewModel : ViewModel() {

    val model = WeatherAPI()
    val climadata = MutableLiveData<Climadata>()
    var dia1 = MutableLiveData<String>()
    var icon1 = MutableLiveData<Int>()

    /**
     * Llama a la funcion getClima del model de la API del Clima
     */
    fun clima(){
        climadata.value = model.getClima()
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
}