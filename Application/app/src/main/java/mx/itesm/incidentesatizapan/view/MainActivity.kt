package mx.itesm.incidentesatizapan.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import com.squareup.okhttp.Callback
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import com.squareup.okhttp.Response
import mx.itesm.incidentesatizapan.R
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        subscribe()  //Subscribing the device onCreate
        getWeatherAPi()
    }

    private fun getWeatherAPi() {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://weatherbit-v1-mashape.p.rapidapi.com/forecast/daily?lat=19.5178211&lon=-99.3611396&units=metric&lang=es")
            .get()
            .addHeader("X-RapidAPI-Key", "eef42780d6mshc08bc5fad1ef6b2p146a49jsn4af0732a1828")
            .addHeader("X-RapidAPI-Host", "weatherbit-v1-mashape.p.rapidapi.com")
            .build()


        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(request: Request?, e: IOException?) {
                Log.d("fallo","fallo")
            }

            override fun onResponse(response: Response?) {
                Log.d("funciono",response!!.body()!!.string())

            }
        })

    }

    /**
     * @author Sebastian Mora
     * This funtion "subscribes" the device for receiving push notification
     * TODO : This function is going to be in the model section
     */
    private fun subscribe(){
        FirebaseMessaging.getInstance().subscribeToTopic("all")
                .addOnCompleteListener{task ->
                    if (!task.isSuccessful){
                        Log.wtf("PushNotification","Subscription failed")
                    }
                    Log.d("PushNotification","Subscribed")
                }
    }
}