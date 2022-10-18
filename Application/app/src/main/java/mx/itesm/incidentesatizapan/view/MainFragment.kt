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
import com.google.common.reflect.Reflection.getPackageName
import mx.itesm.incidentesatizapan.Climadata
import mx.itesm.incidentesatizapan.R
import mx.itesm.incidentesatizapan.TemperatureCategory
import mx.itesm.incidentesatizapan.WindSpeedCategory
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
        subscribeWindCategory()
        subscribeTemperatureCategory()
    }

    private fun subscribeWindCategory() {
        viewModel.windSpeedCategory.observe(viewLifecycleOwner) { windSpeedCategory ->
            if (windSpeedCategory.dangerous) {
                showWindSpeedCategory(windSpeedCategory)
            }
        }
    }

    private fun subscribeTemperatureCategory() {
        viewModel.temperatureCategory.observe(viewLifecycleOwner) { temperatureCategory ->
            if (temperatureCategory.dangerous) {
                showTemperatureCategory(temperatureCategory)
            }
        }
    }

    private fun subscribeWeather() {
        viewModel.climadata.observe(viewLifecycleOwner){ climaData ->
            if(climaData == Climadata.newBuilder().build()){
                notifyConnectionFailure()
            }
            else{
                populateWeatherInfo(climaData)
                viewModel.getWindSpeedCategory(climaData.getData(0).windSpd)
                viewModel.getTemperatureCategory(climaData.getData(0).temp)
            }
        }
    }

    private fun populateWeatherInfo(it: Climadata) {
        var rightNow = Calendar.getInstance()
        val today = rightNow.get(7)
        var dia = today
        binding.hoy.setText("Hoy")
        viewModel.getdia(dia)
        //Este for loop asigna dinamicamente los datos correctos basados en la API del Clima
        for(i in 1..7){
            viewModel.getIcon(it.getData(i-1).weather.icon)
            if(i == 1){
                binding.htempMin.setText(it.getData(i-1).minTemp.toString()+"°")
                binding.htempMax.setText(it.getData(i-1).maxTemp.toString()+"°")
                binding.htempavg.setText(it.getData(i-1).maxTemp.toInt().toString()+"°")
                binding.hoy.setText(viewModel.dia1.value.toString())
                binding.tvTodayMinTempVal.setText(it.getData(i-1).minTemp.toInt().toString()+"°")
                binding.tvTodayMaxTempVal.setText(it.getData(i-1).maxTemp.toInt().toString()+"°")
                binding.tvCurrentTermicSensation.setText(it.getData(i-1).appMaxTemp.toInt().toString()+"°")
                binding.hdes.setText(it.getData(i-1).weather.description.toString())
                binding.imgWeekWeather1.setImageDrawable(getResources().getDrawable(viewModel.icon1.value!!))
                binding.imgTodayWeather.setImageDrawable(getResources().getDrawable(viewModel.icon1.value!!))
                binding.tvAirSpeed.setText(it.getData(i-1).windSpd.toString()+" m/s")
            }
            if(i == 2){
                binding.m1tempMin.setText(it.getData(i-1).minTemp.toString()+"°")
                binding.m1tempMax.setText(it.getData(i-1).maxTemp.toString()+"°")
                binding.tvWeekDayTitle2.setText(viewModel.dia1.value.toString())
                binding.imgWeekWeather2.setImageDrawable(getResources().getDrawable(viewModel.icon1.value!!))
            }
            if(i == 3){
                binding.m2tempMin.setText(it.getData(i-1).minTemp.toString()+"°")
                binding.m2tempMax.setText(it.getData(i-1).maxTemp.toString()+"°")
                binding.tvWeekDayTitle3.setText(viewModel.dia1.value.toString())
                binding.imgWeekWeather3.setImageDrawable(getResources().getDrawable(viewModel.icon1.value!!))
            }
            if(i == 4){
                binding.m3tempMin.setText(it.getData(i-1).minTemp.toString()+"°")
                binding.m3tempMax.setText(it.getData(i-1).maxTemp.toString()+"°")
                binding.tvWeekDayTitle4.setText(viewModel.dia1.value.toString())
                binding.imgWeekWeather4.setImageDrawable(getResources().getDrawable(viewModel.icon1.value!!))
            }
            if(i == 5){
                binding.m4tempMin.setText(it.getData(i-1).minTemp.toString()+"°")
                binding.m4tempMax.setText(it.getData(i-1).maxTemp.toString()+"°")
                binding.tvWeekDayTitle5.setText(viewModel.dia1.value.toString())
                binding.imgWeekWeather5.setImageDrawable(getResources().getDrawable(viewModel.icon1.value!!))
            }
            if(i == 6){
                binding.m5tempMin.setText(it.getData(i-1).minTemp.toString()+"°")
                binding.m5tempMax.setText(it.getData(i-1).maxTemp.toString()+"°")
                binding.tvWeekDayTitle6.setText(viewModel.dia1.value.toString())
                binding.imgWeekWeather6.setImageDrawable(getResources().getDrawable(viewModel.icon1.value!!))
            }
            if(i == 7){
                binding.m6tempMin.setText(it.getData(i-1).minTemp.toString()+"°")
                binding.m6tempMax.setText(it.getData(i-1).maxTemp.toString()+"°")
                binding.tvWeekDayTitle7.setText(viewModel.dia1.value.toString())
                binding.imgWeekWeather7.setImageDrawable(getResources().getDrawable(viewModel.icon1.value!!))
            }
            dia = dia + 1
            if(dia > 7){
                dia = 1
            }
            viewModel.getdia(dia)
        }
    }

    private fun notifyConnectionFailure() {
        val alertDialog = androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("No se ha podido cargar la información")
            .setMessage("Verifique su conectividad a internet e inténtelo más tarde.")
            .setPositiveButton("Aceptar") { _, _ -> }
        alertDialog.show()
    }

    private fun showWindSpeedCategory(windSpeedCategory: WindSpeedCategory) {
        val alertDialog = androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("Velocidad del viento " +
                    viewModel.getWindSpeedCategoryName(windSpeedCategory.category))
            .setMessage(windSpeedCategory.recommendationMessage)
            .setPositiveButton("Aceptar") { _, _ -> }
        alertDialog.show()
    }

    private fun showTemperatureCategory(temperatureCategory: TemperatureCategory) {
        val alertDialog = androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("Alerta de temperatura: " +
                    viewModel.getTemperatureCategoryName(temperatureCategory.category))
            .setMessage(temperatureCategory.recommendationMessage)
            .setPositiveButton("Aceptar") { _, _ -> }
        alertDialog.show()
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

