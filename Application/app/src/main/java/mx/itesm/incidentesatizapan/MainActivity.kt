package mx.itesm.incidentesatizapan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import io.grpc.ManagedChannelBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var client: IncidentServiceClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        subscribe()  //Subscribing the device onCreate
        var channel = ManagedChannelBuilder.forAddress("192.168.1.68", 50051).usePlaintext().build()
        client = IncidentServiceClient(channel)
        client.getIncidents()
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