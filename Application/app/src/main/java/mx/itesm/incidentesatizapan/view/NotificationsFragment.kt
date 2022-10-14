package mx.itesm.incidentesatizapan.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import mx.itesm.incidentesatizapan.Notifications
import mx.itesm.incidentesatizapan.databinding.FragmentNotificationsBinding
import mx.itesm.incidentesatizapan.viewmodel.NotificationsViewModel

/**
 * @author: Marco Barbosa
 * Fragmento en donde se ve el recyclerview de las notificaciones
 */
class NotificationsFragment : Fragment() {

    private val viewModel: NotificationsViewModel by viewModels()

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
        subscribeNotifications()
        viewModel.getNotifications()
    }

    private fun subscribeNotifications() {
        viewModel.notifications.observe(viewLifecycleOwner) { notifications ->
            configurarRV(notifications)
        }
    }

    private fun configurarRV(notifications: Notifications) {
        val arrNotifications = notifications.docsList.toTypedArray()
        // Las notificaciones se muestran en orden cronologico inverso
        arrNotifications.reverse()

        val layout = LinearLayoutManager(requireContext())
        //Ya no se declara, se usa la variable de instancia
        notificationAdapter = NotificationAdapter(requireContext(), arrNotifications)
        binding.rvNotificaciones.adapter = notificationAdapter
        binding.rvNotificaciones.layoutManager = layout

        // separador
        val separador = DividerItemDecoration(requireContext(), layout.orientation)
        binding.rvNotificaciones.addItemDecoration(separador)
    }

}