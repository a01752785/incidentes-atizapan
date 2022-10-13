package mx.itesm.incidentesatizapan.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import mx.itesm.incidentesatizapan.databinding.FragmentManualBinding
/**
 * @author Sebaastian Burgos
 * fragmento donde se ve las imagenes de que hacer en caso de:
 * incendio, terremoto, inundacion.
 */

class ManualFragment : Fragment() {

    private lateinit var binding: FragmentManualBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentManualBinding.inflate(layoutInflater)
        return binding.root


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        registerEvents()
    }

    //botones para mover a los fragmentos correspondientes.
    private fun registerEvents() {
        binding.btnIncendio.setOnClickListener {
            findNavController().navigate(
                ManualFragmentDirections.actionNavigationDashboardToIncendioFragment()
            )
        }
        binding.btnInundacion.setOnClickListener {
            findNavController().navigate(
                ManualFragmentDirections.actionNavigationDashboardToInundacionFragment()
            )
        }

        binding.btnTerremoto.setOnClickListener {
            findNavController().navigate(
                ManualFragmentDirections.actionNavigationDashboardToTerremotoFragment()
            )
        }

        binding.btnInun.setOnClickListener {
            findNavController().navigate(
                ManualFragmentDirections.actionNavigationDashboardToInundacionFragment()
            )
        }

        binding.btnSimo.setOnClickListener {
            findNavController().navigate(
                ManualFragmentDirections.actionNavigationDashboardToTerremotoFragment()
            )
        }

        binding.btnince.setOnClickListener {
            findNavController().navigate(
                ManualFragmentDirections.actionNavigationDashboardToIncendioFragment()
            )
        }

    }


}