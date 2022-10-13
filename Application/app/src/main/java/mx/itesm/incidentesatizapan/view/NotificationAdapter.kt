package mx.itesm.incidentesatizapan.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.itesm.incidentesatizapan.Incident
import mx.itesm.incidentesatizapan.Notification
import mx.itesm.incidentesatizapan.R

class NotificationAdapter (private val contexto : Context, var notificationArray : Array<Notification>) : RecyclerView.Adapter<NotificationAdapter.RenglonNotification>()
{
    // Se llama cada vez que hay que poblar un renglon
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RenglonNotification {
        val view =LayoutInflater.from(contexto).inflate(R.layout.renglon_noti,parent,false)
        return RenglonNotification(view)
    }
    // Para poblar un renglon  (Para poner los datos en el renglon 'position'
    override fun onBindViewHolder(holder: RenglonNotification, position: Int) {
        val courrentNotification = notificationArray[position]
        holder.set(courrentNotification)
    }
    //Numero de renglones que tendra el recylerview
    override fun getItemCount(): Int {
        return notificationArray.size
    }

    // Funcion que relaciona los items con los elementos del renglon
    class RenglonNotification(var renglonNotificacion : View)  :RecyclerView.ViewHolder(renglonNotificacion)
    {
        fun set(currentNotification : Notification){
            renglonNotificacion.findViewById<TextView>(R.id.tvTitle).text = currentNotification.title
            renglonNotificacion.findViewById<TextView>(R.id.tvDescription).text = currentNotification.body
            renglonNotificacion.findViewById<TextView>(R.id.tvTimestamp).text = currentNotification.timestamp
            renglonNotificacion.findViewById<ImageView>(R.id.imgNotification).setImageResource(
                getNotificationTypeIcon(currentNotification.incidentType))
        }

        private fun getNotificationTypeIcon(type: Notification.NotificationType?): Int {
            return when (type) {
                Notification.NotificationType.FIRE -> R.drawable.noti_incedio_64
                Notification.NotificationType.FLOODING -> R.drawable.noti_inundacion_64
                Notification.NotificationType.CAR_ACCIDENT -> R.drawable.noti_car_collision_64
                Notification.NotificationType.GAS_LEAK -> R.drawable.noti_gas_64
                Notification.NotificationType.WATER_LEAK -> R.drawable.noti_water_leak_64
                Notification.NotificationType.OTHER -> R.drawable.noti_bell_64
                null -> R.drawable.warning
            }
        }

    }



}