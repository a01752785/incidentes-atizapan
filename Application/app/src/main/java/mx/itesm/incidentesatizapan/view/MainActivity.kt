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