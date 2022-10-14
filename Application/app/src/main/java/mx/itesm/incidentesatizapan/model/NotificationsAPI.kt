package mx.itesm.incidentesatizapan.model

import android.util.Log
import com.google.protobuf.util.JsonFormat
import com.squareup.okhttp.*
import mx.itesm.incidentesatizapan.Climadata
import mx.itesm.incidentesatizapan.Notifications
import okio.IOException
import java.util.concurrent.CountDownLatch

class NotificationsAPI {
    /**
     * Funcion que hace la llamada al servicio de API del clima
     */
    fun getNotifications() : Notifications {
        var notifications = Notifications.newBuilder().build()
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://database.safeatizapan.lol/notifications")
            .get()
            .build()

        //Se empieza una countdown para que espere que termine la llamada en el thread creado por
        //enqueue y retorne lo que se llamo
        val countdown = CountDownLatch(1)
        val response = client.newCall(request).enqueue(object : Callback {
            override fun onFailure(request: Request?, e: IOException?) {
                Log.d("NotificationsAPI","Unable to get notifications")
                countdown.countDown()
            }

            override fun onResponse(response: Response?) {
                val notificationsBuilder: Notifications.Builder = Notifications.newBuilder()
                JsonFormat.parser()
                    .ignoringUnknownFields()
                    .merge(response!!.body().string(), notificationsBuilder)
                notifications = notificationsBuilder.build()
                countdown.countDown()
            }
        })

        countdown.await()
        return notifications


    }
}