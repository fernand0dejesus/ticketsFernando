package fernando.hernandez

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import modelo.ClaseConexion

class Login : AppCompatActivity() {
    private lateinit var txtCorreoLogin: EditText
    private lateinit var txtContrasenaLogin: EditText
    private lateinit var btnLogin:Button
    private lateinit var btnRegistrarLogin:Button
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        txtCorreoLogin = findViewById(R.id.txtCorreoLogin)
        txtContrasenaLogin = findViewById(R.id.txtContrasenaLogin)
        btnLogin = findViewById(R.id.btnLogin)
        btnRegistrarLogin = findViewById(R.id.btnRegistrarLogin)

        btnLogin.setOnClickListener{
            //intent para cambiar a la pantalla principal
            val pantallaPrincipal = Intent(this, MainActivity::class.java)

            GlobalScope.launch(Dispatchers.IO) {
                val objConexion = ClaseConexion().cadenaConexion()

                //prepareStatement
                val comprobarUsuario = objConexion?.prepareStatement("select * from tbusuario where correo = ? AND contrasena = ?")!!
                comprobarUsuario.setString(1, txtCorreoLogin.text.toString())
                comprobarUsuario.setString(2, txtContrasenaLogin.text.toString())

                val resultado = comprobarUsuario.executeQuery()

                //si encuentra el resultado
                if(resultado.next()){
                    startActivity(pantallaPrincipal)
                }
                else{
                    println("Usuario no encontrado")
                }
            }


        }
        btnRegistrarLogin.setOnClickListener{
            //para volver a la pantalla login
            val pantallaLogin = Intent(this, Registro::class.java)
            startActivity(pantallaLogin)
        }
    }
}