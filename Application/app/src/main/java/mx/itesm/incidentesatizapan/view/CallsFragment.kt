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
import android.widget.Toast
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

        registerEvents()

    }
    //TODO poner los numeros correspondientes a los servicios
    //Botones para llamar a los numeros de los servicios.
    private fun registerEvents() {
        // Lleva a Fragmento Dashboard
        binding.btnLink.setOnClickListener{
            findNavController().navigate(CallsFragmentDirections.actionCallsFragmentToNavigationDashboard())
        }
        binding.imgbtnProteccionCivil.setOnClickListener{

            if (ActivityCompat.checkSelfPermission(requireContext(),Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED){
                Toast.makeText(requireContext(),"Favor de dar permiso",Toast.LENGTH_SHORT).show()
                checkPermissionPhone()
            }
            if (ActivityCompat.checkSelfPermission(requireContext(),Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){

                callPhone("12345")
        }

        }
        binding.imgbtnAngelesverdes.setOnClickListener{

            if (ActivityCompat.checkSelfPermission(requireContext(),Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED){
                Toast.makeText(requireContext(),"Favor de dar permiso",Toast.LENGTH_SHORT).show()
                checkPermissionPhone()
            }
            if (ActivityCompat.checkSelfPermission(requireContext(),Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){

                callPhone("21")
            }
        }
        binding.imgbtnBomberos.setOnClickListener{

            if (ActivityCompat.checkSelfPermission(requireContext(),Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED){
                Toast.makeText(requireContext(),"Favor de dar permiso",Toast.LENGTH_SHORT).show()
                checkPermissionPhone()
            }
            if (ActivityCompat.checkSelfPermission(requireContext(),Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){

                callPhone("5")
            }
        }
        binding.imgbtnCapufe.setOnClickListener{

            if (ActivityCompat.checkSelfPermission(requireContext(),Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED){
                Toast.makeText(requireContext(),"Favor de dar permiso",Toast.LENGTH_SHORT).show()
                checkPermissionPhone()
            }
            if (ActivityCompat.checkSelfPermission(requireContext(),Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){

                callPhone("2")
            }
        }
        binding.imgbtnEmergency.setOnClickListener{

            if (ActivityCompat.checkSelfPermission(requireContext(),Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED){
                Toast.makeText(requireContext(),"Favor de dar permiso",Toast.LENGTH_SHORT).show()
                checkPermissionPhone()
            }
            if (ActivityCompat.checkSelfPermission(requireContext(),Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){

                callPhone("65")
            }
        }
        binding.imgbtnCruzRoja.setOnClickListener{

            if (ActivityCompat.checkSelfPermission(requireContext(),Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED){
                Toast.makeText(requireContext(),"Favor de dar permiso",Toast.LENGTH_SHORT).show()
                checkPermissionPhone()
            }
            if (ActivityCompat.checkSelfPermission(requireContext(),Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){

                callPhone("12345")
            }
        }
        binding.imgbtnDenunciaAnonima.setOnClickListener{

            if (ActivityCompat.checkSelfPermission(requireContext(),Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED){
                Toast.makeText(requireContext(),"Favor de dar permiso",Toast.LENGTH_SHORT).show()
                checkPermissionPhone()
            }
            if (ActivityCompat.checkSelfPermission(requireContext(),Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){

                callPhone("6")
            }
        }
        binding.imgbtnFugaAgua.setOnClickListener{

            if (ActivityCompat.checkSelfPermission(requireContext(),Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED){
                Toast.makeText(requireContext(),"Favor de dar permiso",Toast.LENGTH_SHORT).show()
                checkPermissionPhone()
            }
            if (ActivityCompat.checkSelfPermission(requireContext(),Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){

                callPhone("3")
            }
        }
        binding.imgbtnFugaGas.setOnClickListener{

            if (ActivityCompat.checkSelfPermission(requireContext(),Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED){
                Toast.makeText(requireContext(),"Favor de dar permiso",Toast.LENGTH_SHORT).show()
                checkPermissionPhone()
            }
            if (ActivityCompat.checkSelfPermission(requireContext(),Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){

                callPhone("71")
            }
        }
        binding.imgbtnIncendioForestal.setOnClickListener{

            if (ActivityCompat.checkSelfPermission(requireContext(),Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED){
                Toast.makeText(requireContext(),"Favor de dar permiso",Toast.LENGTH_SHORT).show()
                checkPermissionPhone()
            }
            if (ActivityCompat.checkSelfPermission(requireContext(),Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){

                callPhone("23")
            }
        }
        binding.imgbtnPoliciaFederal.setOnClickListener{

            if (ActivityCompat.checkSelfPermission(requireContext(),Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED){
                Toast.makeText(requireContext(),"Favor de dar permiso",Toast.LENGTH_SHORT).show()
                checkPermissionPhone()
            }
            if (ActivityCompat.checkSelfPermission(requireContext(),Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){

                callPhone("1")
            }
        }
    }
    


    // Verifica el permiso del telefono
    private fun checkPermissionPhone() {
        if(ActivityCompat.checkSelfPermission(requireContext(),Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.CALL_PHONE),101) // Check for permission
        }
/*
        else{

            callPhone(number)
        }

 */


       }



    //Recibe el numero a marcar e inicia el proceso para llamar
    private fun callPhone(number: String) {

        val callIntent = Intent(Intent.ACTION_CALL) // Call intent
        callIntent.data = Uri.parse("tel:$number")
        startActivity(callIntent)


    }
}