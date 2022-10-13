package mx.itesm.incidentesatizapan.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.itesm.incidentesatizapan.Notifications
import mx.itesm.incidentesatizapan.model.NotificationsAPI

/**
kk */
class NotificationsViewModel : ViewModel() {
    private val model = NotificationsAPI()

    val notifications = MutableLiveData<Notifications>()

    fun getNotifications() {
        notifications.value = model.getNotifications()
    }
}