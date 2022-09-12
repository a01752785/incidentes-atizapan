package mx.itesm.incidentesatizapan

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService

/**
 * @author Sebastian Mora
 * This class will handle what the app does when
 * a user clicks on a notification.
 */
class PushNotificationService : FirebaseMessagingService(){
    //TODO : Display where the incident is going on, maybe?

    //This  function prints the specific token of the device for
    //Individual testing purposes
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.wtf("PushNotification","New Token : $token")
    }
}