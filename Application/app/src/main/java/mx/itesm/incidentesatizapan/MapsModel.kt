package mx.itesm.incidentesatizapan

import android.graphics.Color
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

/**
 * @author: David Damian
 * The model for the maps fragment.
 */
class MapsModel {
    // TODO: Update this function to call a client class and retrieve info from server
    /**
     * Get a list of incidents relevant to the user.
     * @return Incidents, a list of incidents.
     */
    fun getIncidents(): Incidents {
        val incidents = Incidents.newBuilder()

        incidents.addIncident(
            Incident.newBuilder()
                .setCoordinate(
                    GeoCoordinate.newBuilder()
                        .setLatitude(19.589693)
                        .setLongitude(-99.229509))
                .setReferenceLocation("Calle Mariano Matamoros")
                .setDescription("Incendio de casa. Mantenerse alejados.")
                .setIncidentType(Incident.IncidentType.FIRE)
                .setRiskRadius(100.0)
                .build())

        incidents.addIncident(
            Incident.newBuilder()
                .setCoordinate(
                    GeoCoordinate.newBuilder()
                        .setLatitude(19.569871)
                        .setLongitude(-99.246799))
                .setReferenceLocation("Colonia Atizapán Centro")
                .setDescription("Inundación por 20mm de lluvia")
                .setIncidentType(Incident.IncidentType.FLOODING)
                .setRiskRadius(500.0)
                .build())

        incidents.addIncident(
            Incident.newBuilder()
                .setCoordinate(
                    GeoCoordinate.newBuilder()
                        .setLatitude(19.570737)
                        .setLongitude(-99.220498))
                .setReferenceLocation("Av. Paseo de los Gigantes")
                .setDescription("Fuga de gas. Elementos de protección civil aproximándose.")
                .setIncidentType(Incident.IncidentType.GAS_LEAK)
                .setRiskRadius(300.0)
                .build())

        incidents.addIncident(
            Incident.newBuilder()
                .setCoordinate(
                    GeoCoordinate.newBuilder()
                        .setLatitude(19.589861)
                        .setLongitude(-99.221233))
                .setReferenceLocation("Calle Azalea")
                .setDescription("Fuga de agua. En reparación")
                .setIncidentType(Incident.IncidentType.WATER_LEAK)
                .setRiskRadius(50.0)
                .build())

        incidents.addIncident(
            Incident.newBuilder()
                .setCoordinate(
                    GeoCoordinate.newBuilder()
                        .setLatitude(19.592465)
                        .setLongitude(-99.256743))
                .setReferenceLocation("Autopista Lecheria - Chamapa")
                .setDescription("Accidente de tránsito en KM 40. Ambulancias aproximándose.")
                .setIncidentType(Incident.IncidentType.CAR_ACCIDENT)
                .setRiskRadius(50.0)
                .build())

        incidents.addIncident(
            Incident.newBuilder()
                .setCoordinate(
                    GeoCoordinate.newBuilder()
                        .setLatitude(19.573718)
                        .setLongitude(-99.238168))
                .setReferenceLocation("Calle 25 de Diciembre")
                .setDescription("Árbol caído. Elementos de protección civil en el lugar.")
                .setIncidentType(Incident.IncidentType.OTHER)
                .setRiskRadius(50.0)
                .build())

        return incidents.build()
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