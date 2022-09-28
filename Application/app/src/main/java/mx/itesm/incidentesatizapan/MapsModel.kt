package mx.itesm.incidentesatizapan

import android.graphics.Color
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import io.grpc.ManagedChannelBuilder

/**
 * @author: David Damian
 * The model for the maps fragment.
 */
class MapsModel {
    /**
     * Get a list of incidents relevant to the user.
     * @return Incidents, a list of incidents.
     */
    fun getIncidents(): Incidents {
        val channel = ManagedChannelBuilder
            .forAddress("192.168.1.68", 50051)
            .usePlaintext()
            .build()
        val client = IncidentServiceClient(channel)
        return client.getIncidents()
    }

    /**
     * Represent an incident title in a human-readable form.
     * @param Incident, the incident whose title will be generated.
     * @return String, the generated title.
     */
    fun parseIncidentTitle(incident: Incident): String {
        val title = when (incident.incidentType) {
            Incident.IncidentType.FIRE -> "Incendio"
            Incident.IncidentType.FLOODING -> "Inundación"
            Incident.IncidentType.CAR_ACCIDENT -> "Accidente de tránsito"
            Incident.IncidentType.GAS_LEAK -> "Fuga de gas"
            Incident.IncidentType.WATER_LEAK -> "Fuga de agua"
            Incident.IncidentType.OTHER -> "Incidente"
            null -> "Incidente cerca de"
        }

        val titlePostfix = if (incident.hasReferenceLocation()) {
            " cerca de " + incident.referenceLocation
        } else {
            ""
        }

        return title + titlePostfix
    }
}