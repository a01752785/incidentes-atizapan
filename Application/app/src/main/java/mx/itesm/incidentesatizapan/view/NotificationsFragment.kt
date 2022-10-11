package mx.itesm.incidentesatizapan.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import mx.itesm.incidentesatizapan.databinding.FragmentNotificationsBinding
import mx.itesm.incidentesatizapan.model.Notification
import mx.itesm.incidentesatizapan.viewmodel.NotificationsViewModel

/**
 * @author: Marco Barbosa
 * Fragmento en donde se ve el recyclerview de las notificaciones
 */
class NotificationsFragment : Fragment() {

    //binding
    private lateinit var binding : FragmentNotificationsBinding

    // Fuente de datos
    private lateinit var notificationAdapter : NotificationAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentNotificationsBinding.inflate(layoutInflater)
        val root: View = binding.root

        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configurarRV()
        //configurarObservables()
    }

    private fun configurarRV() {
        val arrNoticias = arrayOf(Notification("Inundacion","texto",0),
            Notification("Inundacion 2","texto 2",0))
        val layout = LinearLayoutManager(requireContext())
        //Ya no se declara, se usa la variable de instancia
        notificationAdapter = NotificationAdapter(requireContext(), arrNoticias)
        binding.rvNotificaciones.adapter = notificationAdapter
        binding.rvNotificaciones.layoutManager = layout

        // separador
        val separador = DividerItemDecoration(requireContext(), layout.orientation)
        binding.rvNotificaciones.addItemDecoration(separador)

    }

}