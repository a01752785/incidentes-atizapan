package mx.itesm.incidentesatizapan.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import mx.itesm.incidentesatizapan.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        subscribe()  //Subscribing the device onCreate
    }

    /**
     * @author Sebastian Mora
     * This funtion "subscribes" the device for receiving push notification
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