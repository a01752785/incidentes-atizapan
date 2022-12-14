package mx.itesm.incidentesatizapan.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import mx.itesm.incidentesatizapan.Menu
import mx.itesm.incidentesatizapan.R

/**
 * @author Sebastian Burgos Alanis A01746459
 * Se crea la clase SplashScreen para crear el splash
 */
@Suppress("DEPRETATION")
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        //ESCONDER LA BARRA MORADA
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        //DETERMINADO TIEMPO
        Handler().postDelayed({
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
            finish()
        }, 900) // 3000 is the delayed time in milliseconds.
    }
}