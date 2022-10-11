package mx.itesm.incidentesatizapan.viewmodel

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

    /**
     * Llama a la funcion getClima del model de la API del Clima
     */
    fun clima(){
        climadata.value = model.getClima()
    }
}