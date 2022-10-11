package mx.itesm.incidentesatizapan.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.itesm.incidentesatizapan.R
import mx.itesm.incidentesatizapan.model.Notification

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
        fun set(courrentNotification : Notification){
            renglonNotificacion.findViewById<TextView>(R.id.tvTitle).text = courrentNotification.title
            renglonNotificacion.findViewById<TextView>(R.id.tvDescription).text = courrentNotification.description
            renglonNotificacion.findViewById<ImageView>(R.id.imgNotification).setImageResource(R.drawable.noti_inundacion_64)
        }

    }



}