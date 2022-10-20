package mx.itesm.incidentesatizapan.model

import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import mx.itesm.incidentesatizapan.R

/**
 * @author Sebastian Mora
 * This class will handle what the app does when
 * a user clicks on a notification.
 */
class PushNotificationService : FirebaseMessagingService(){
    //TODO : Display where the incident is going on, maybe?
    /**This function prints the specific token of the device for
     * individual testing purposes
     * @param token The FCM token of the device
     */
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.wtf("PushNotification","New Token : $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        val notification = NotificationCompat.Builder(this)
            .setContentTitle(message.notification?.title)
            .setContentText(message.notification?.body)
            .setSmallIcon(R.mipmap.ic_launcher)
            .build()

        val manager = NotificationManagerCompat.from(applicationContext)
        manager.notify(123, notification)
    }
}