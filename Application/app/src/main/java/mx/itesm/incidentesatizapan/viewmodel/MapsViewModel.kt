package mx.itesm.incidentesatizapan.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.itesm.incidentesatizapan.Incident
import mx.itesm.incidentesatizapan.Incidents
import mx.itesm.incidentesatizapan.model.MapsModel

/**
 * @author: David Damian
 * The view model for the maps fragment.
 */
class MapsViewModel: ViewModel() {
    private val model = MapsModel()

    val incidents = MutableLiveData<Incidents>()

    fun getIncidents() {
        incidents.value = model.getIncidents()
    }

    fun parseIncidentTitle(incident: Incident): String {
        return model.parseIncidentTitle(incident)
    }
}