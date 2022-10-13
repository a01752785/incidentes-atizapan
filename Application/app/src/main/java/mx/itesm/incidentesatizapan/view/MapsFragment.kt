package mx.itesm.incidentesatizapan.view

import android.graphics.Color
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import io.grpc.StatusRuntimeException
import mx.itesm.incidentesatizapan.Incident
import mx.itesm.incidentesatizapan.Incidents
import mx.itesm.incidentesatizapan.viewmodel.MapsViewModel
import mx.itesm.incidentesatizapan.R
import mx.itesm.incidentesatizapan.databinding.FragmentMapsBinding

/**
 * @author: David Damian
 * The view for the maps fragment, which will be used to display local incidents.
 */
class MapsFragment : Fragment(), GoogleMap.OnMarkerClickListener {
    private val viewModel: MapsViewModel by viewModels()
    private lateinit var binding: FragmentMapsBinding
    private lateinit var googleMap: GoogleMap

    private val callback = OnMapReadyCallback { map ->
        /**
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        val atizapan = LatLng(19.589693, -99.229509)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(atizapan, 13F))
        map.setOnMarkerClickListener(this)
        googleMap = map

        // Tries to communicate with the gRPC server to get local incidents
        try {
            viewModel.getIncidents()
        } catch (e: StatusRuntimeException) {
            println("gRPC failed: ${e.status}")
            notifyConnectionFailure()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservables()
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private fun setObservables() {
        viewModel.incidents.observe(viewLifecycleOwner) { incidents ->
            // Only add the markers if the map already exists
            if (this::googleMap.isInitialized) {
                addMarkers(incidents)
            }
        }
    }

    /**
     * This function is called when there is an error connecting to the Incident Server,
     * asking the user to try again.
     */
    private fun notifyConnectionFailure() {
        val alertDialog = AlertDialog.Builder(requireContext())
            .setTitle("No se ha podido cargar la información")
            .setMessage("Verifique su conectividad a internet e inténtelo más tarde.")
            .setPositiveButton("Aceptar") { _, _ -> }
        alertDialog.show()
    }

    /**
     * Add markers to the map in the incident locations. Uses a different icon for each type of
     * incident.
     * @param Incidents, the incidents to represent.
     */
    private fun addMarkers(incidents: Incidents) {
        for (incident: Incident in incidents.incidentList) {
            // Ignore incidents without coordinates
            if (!incident.hasCoordinate()
                || !incident.coordinate.hasLatitude()
                || !incident.coordinate.hasLongitude()) {
                continue
            }

            val title = viewModel.parseIncidentTitle(incident)
            val icon = getIncidentTypeIcon(incident.incidentType)

            // Draw a marker in the location of the incident
            val marker = googleMap.addMarker(
                MarkerOptions()
                    .position(LatLng(incident.coordinate.latitude, incident.coordinate.longitude))
                    .title(title)
                    .icon(BitmapDescriptorFactory.fromResource(icon)))

            if (marker != null) {
                marker.tag = incident
            }

            // Draw a circle indicating the risk area that people should avoid
            val rgbColor = getIncidentTypeRadiusColor(incident.incidentType)
            val strokeColor = Color.argb(255, rgbColor.first, rgbColor.second, rgbColor.third)
            val fillColor = Color.argb(50, rgbColor.first, rgbColor.second, rgbColor.third)
            if (incident.riskRadius >= 0) {
                googleMap.addCircle(
                    CircleOptions()
                        .center(LatLng(incident.coordinate.latitude, incident.coordinate.longitude))
                        .radius(incident.riskRadius)
                        .strokeColor(strokeColor)
                        .fillColor(fillColor))
            }
        }
    }

    /**
     * Get the icon for each type of incident.
     * @param Incident.IncidentType, the type of incident
     * @return Int, a reference to the incident type icon.
     */
    private fun getIncidentTypeIcon(type: Incident.IncidentType?): Int {
        return when (type) {
            Incident.IncidentType.FIRE -> R.drawable.flames
            Incident.IncidentType.FLOODING -> R.drawable.flood
            Incident.IncidentType.CAR_ACCIDENT -> R.drawable.car_collision
            Incident.IncidentType.GAS_LEAK -> R.drawable.gas
            Incident.IncidentType.WATER_LEAK -> R.drawable.water_leaking
            Incident.IncidentType.OTHER -> R.drawable.warning
            null -> R.drawable.warning
        }
    }

    private fun getIncidentTypeRadiusColor(type: Incident.IncidentType?): Triple<Int, Int, Int> {
        return when (type) {
            Incident.IncidentType.FIRE -> Triple(255, 0, 0)
            Incident.IncidentType.FLOODING -> Triple(0, 0, 255)
            Incident.IncidentType.CAR_ACCIDENT -> Triple(255, 145, 61)
            Incident.IncidentType.GAS_LEAK -> Triple(255, 255, 0)
            Incident.IncidentType.WATER_LEAK -> Triple(78, 220, 242)
            Incident.IncidentType.OTHER -> Triple(138, 149, 151)
            null -> Triple(138, 149, 151)
        }
    }

    /**
     * This function is called when the user clicks on a map marker.
     * @param marker, the marker clicked by the user.
     * @return Boolean, true if the default action (centering the marker and showing the title)
     * has been overridden and should not be performed, false otherwise.
     */
    override fun onMarkerClick(marker: Marker): Boolean {
        // Animates a camera change focusing on the incident marker, and then shows the
        // incident info.
        googleMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom(marker.position, 16F),
            object: GoogleMap.CancelableCallback {
            override fun onCancel() {

            }
            override fun onFinish() {
                val incident = marker.tag as Incident
                val alertDialog = AlertDialog.Builder(requireContext())
                    .setTitle(marker.title)
                    .setMessage(incident.description)
                    .setPositiveButton("Aceptar") { _, _ -> }
                alertDialog.show()
            }
        })

        // The alert dialog has shown, overriding the default marker behavior, thus returns true.
        return true
    }

}
