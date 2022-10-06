package mx.itesm.incidentesatizapan.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import mx.itesm.incidentesatizapan.model.WeatherAPI

class MainViewModel : ViewModel() {
val model = WeatherAPI()
    fun clima(){
        model.getClima()
    }


    // TODO: Implement the ViewModel
}