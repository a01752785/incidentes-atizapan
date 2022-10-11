package mx.itesm.incidentesatizapan.view

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import mx.itesm.incidentesatizapan.databinding.FragmentMainBinding
import mx.itesm.incidentesatizapan.viewmodel.MainViewModel



class MainFragment : Fragment() {




    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        
        viewModel.clima()
        subscribeWeather()
    }

    private fun subscribeWeather() {
        viewModel.climadata.observe(viewLifecycleOwner){
            //TODO Cambiar alerta por actualizacion de datos en el view
            val alertDialog = AlertDialog.Builder(requireContext())
                .setTitle("Info")
                .setMessage(it.toString())
                .setPositiveButton("Aceptar") { _, _ -> }
            alertDialog.show()
        }
    }
/*
    private fun registerEvents() {
        binding.btnMapa.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToMapsFragment())
        }

        binding.btnManual.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToManualFragment())
        }

    }
*/

}

