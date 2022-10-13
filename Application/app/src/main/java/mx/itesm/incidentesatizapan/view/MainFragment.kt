package mx.itesm.incidentesatizapan.view

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import mx.itesm.incidentesatizapan.databinding.FragmentMainBinding
import mx.itesm.incidentesatizapan.viewmodel.MainViewModel
import java.util.*


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
            var rightNow = Calendar.getInstance()
            val today = rightNow.get(7)
            var dia = today
            var dias = MutableLiveData<String>()
            binding.hoy.setText("Hoy")
            /*
            val alertDialog = AlertDialog.Builder(requireContext())
                .setTitle("Info")
                .setMessage(today)
                .setPositiveButton("Aceptar") { _, _ -> }
            alertDialog.show()

             */
            viewModel.getdia(dia)
            for(i in 1..7){
                if(i == 1){
                    binding.htempMin.setText(it.getData(i-1).minTemp.toString()+"°")
                    binding.htempMax.setText(it.getData(i-1).maxTemp.toString()+"°")
                    binding.htempavg.setText(it.getData(i-1).temp.toString()+"°")
                    binding.hoy.setText(viewModel.dia1.value.toString())
                    binding.tvTodayMinTempVal.setText(it.getData(i-1).minTemp.toString()+"°")
                    binding.tvTodayMaxTempVal.setText(it.getData(i-1).maxTemp.toString()+"°")
                    binding.tvCurrentTermicSensation.setText(it.getData(i-1).appMaxTemp.toString()+"°")
                }
                if(i == 2){
                    binding.m1tempMin.setText(it.getData(i-1).minTemp.toString()+"°")
                    binding.m1tempMax.setText(it.getData(i-1).maxTemp.toString()+"°")
                    binding.tvWeekDayTitle2.setText(viewModel.dia1.value.toString())
                }
                if(i == 3){
                    binding.m2tempMin.setText(it.getData(i-1).minTemp.toString()+"°")
                    binding.m2tempMax.setText(it.getData(i-1).maxTemp.toString()+"°")
                    binding.tvWeekDayTitle3.setText(viewModel.dia1.value.toString())
                }
                if(i == 4){
                    binding.m3tempMin.setText(it.getData(i-1).minTemp.toString()+"°")
                    binding.m3tempMax.setText(it.getData(i-1).maxTemp.toString()+"°")
                    binding.tvWeekDayTitle4.setText(viewModel.dia1.value.toString())
                }
                if(i == 5){
                    binding.m4tempMin.setText(it.getData(i-1).minTemp.toString()+"°")
                    binding.m4tempMax.setText(it.getData(i-1).maxTemp.toString()+"°")
                    binding.tvWeekDayTitle5.setText(viewModel.dia1.value.toString())
                }
                if(i == 6){
                    binding.m5tempMin.setText(it.getData(i-1).minTemp.toString()+"°")
                    binding.m5tempMax.setText(it.getData(i-1).maxTemp.toString()+"°")
                    binding.tvWeekDayTitle6.setText(viewModel.dia1.value.toString())
                }
                if(i == 7){
                    binding.m6tempMin.setText(it.getData(i-1).minTemp.toString()+"°")
                    binding.m6tempMax.setText(it.getData(i-1).maxTemp.toString()+"°")
                    binding.tvWeekDayTitle7.setText(viewModel.dia1.value.toString())
                }
                dia = dia + 1
                if(dia > 7){
                    dia = 1
                }
                viewModel.getdia(dia)
            }
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

