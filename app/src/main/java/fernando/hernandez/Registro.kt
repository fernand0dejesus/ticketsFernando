package fernando.hernandez

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import modelo.ClaseConexion
import java.util.UUID

class Registro : AppCompatActivity() {
    private lateinit var txtCorreo:EditText
    private lateinit var txtContrasena:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //mando a llamar
         txtCorreo = findViewById(R.id.txtCorreo)
         txtContrasena = findViewById(R.id.txtContrasena)
        val btnRegistro = findViewById<Button>(R.id.btnRegistrar)
        val btnIniciar = findViewById<Button>(R.id.btnIniciar)

        //programos botones
        btnRegistro.setOnClickListener{
            GlobalScope.launch (Dispatchers.IO) {
                val objConexion = ClaseConexion().cadenaConexion()
                //prepare statement
                val crearUsuario = objConexion?.prepareStatement("INSERT INTO tbusuario(UUID_Usuario, correo, contrasena) VALUES(?, ?, ?)")!!
                crearUsuario.setString(1, UUID.randomUUID().toString())
                crearUsuario.setString(2, txtCorreo.text.toString())
                crearUsuario.setString(3,txtContrasena.text.toString())
                    crearUsuario.executeUpdate()

            }
            LimpiarCampos()
        }
    }
    private fun LimpiarCampos(){
        txtCorreo.text.clear()
        txtContrasena.text.clear()
    }
}