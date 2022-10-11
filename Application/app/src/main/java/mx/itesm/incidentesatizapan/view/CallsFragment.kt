package mx.itesm.incidentesatizapan.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.findNavController
import mx.itesm.incidentesatizapan.databinding.FragmentCallsBinding
import mx.itesm.incidentesatizapan.databinding.FragmentManualBinding

/**
 * @author: Marco Barbosa
 * Fragmento encargado encargado de verificar el permiso para el
 * uso del telefono y hacer las llamadas a los servicios correspondientes
 */
class CallsFragment : Fragment() {
    private lateinit var binding: FragmentCallsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentCallsBinding.inflate(layoutInflater)
        return binding.root


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        checkPermissionPhone()

        registerEvents()
    }
    //TODO
    //Botones para llamar a los numeros de los servicios.
    private fun registerEvents() {
        // Lleva a Fragmento Dashboard
        binding.btnLink.setOnClickListener{
            findNavController().navigate(CallsFragmentDirections.actionCallsFragmentToNavigationDashboard())
        }
        binding.imgbtnProteccionCivil.setOnClickListener{

            callPhone("12345")
        }
        binding.imgbtnAngelesverdes.setOnClickListener{
            callPhone("12")
        }
        binding.imgbtnBomberos.setOnClickListener{
            callPhone("5")
        }
        binding.imgbtnCapufe.setOnClickListener{
            callPhone("2")
        }
        binding.imgbtnEmergency.setOnClickListener{
            callPhone("65")
        }
        binding.imgbtnCruzRoja.setOnClickListener{
            callPhone("1")
        }
        binding.imgbtnDenunciaAnonima.setOnClickListener{
            callPhone("10")
        }
        binding.imgbtnFugaAgua.setOnClickListener{
            callPhone("3")
        }
        binding.imgbtnFugaGas.setOnClickListener{
            callPhone("71")
        }
        binding.imgbtnIncendioForestal.setOnClickListener{
            callPhone("23")
        }
        binding.imgbtnPoliciaFederal.setOnClickListener{
            callPhone("19")
        }
    }
    // Verifica el permiso del telefono
    private fun checkPermissionPhone() {
        if(ActivityCompat.checkSelfPermission(requireContext(),Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.CALL_PHONE),101) // Check for permission
        }
    }
    //Recibe el numero a marcar e inicia el proceso para llamar
    private fun callPhone(number: String) {
        val callIntent = Intent(Intent.ACTION_CALL) // Call intent
        callIntent.data = Uri.parse("tel:$number")
        startActivity(callIntent)
    }
}