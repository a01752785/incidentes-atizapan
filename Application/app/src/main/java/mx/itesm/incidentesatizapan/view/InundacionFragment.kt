package mx.itesm.incidentesatizapan.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.itesm.incidentesatizapan.R

/**
 * @author Sebaastian Burgos
 * Fragmento de imagen de inundacion.
 */
class InundacionFragment : Fragment() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
        }

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_inundacion, container, false)
        }

    }