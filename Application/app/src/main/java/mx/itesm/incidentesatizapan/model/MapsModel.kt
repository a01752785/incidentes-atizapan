package mx.itesm.incidentesatizapan.model

import io.grpc.ManagedChannelBuilder
import mx.itesm.incidentesatizapan.Incident
import mx.itesm.incidentesatizapan.Incidents

/**
 * @author: David Damian
 * The model for the maps fragment.
 */
class MapsModel {
    /**
     * Get a list of incidents relevant to the user from the server.
     * @return Incidents, a list of incidents.
     */
    fun getIncidents(): Incidents {
        val channel = ManagedChannelBuilder
            .forAddress("137.184.189.169", 8003)
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