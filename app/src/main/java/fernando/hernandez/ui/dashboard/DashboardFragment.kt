package fernando.hernandez.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import fernando.hernandez.R
import fernando.hernandez.databinding.FragmentDashboardBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import modelo.ClaseConexion
import java.util.UUID

class DashboardFragment : Fragment() {
    private lateinit var txtTitulo: EditText
    private lateinit var txtDescripcion: EditText
    private lateinit var txtAutor: EditText
    private lateinit var txtEmail_Autor: EditText
    private lateinit var txtEstado: EditText
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        txtTitulo = root.findViewById(R.id.txtTItulo)
        txtDescripcion = root.findViewById(R.id.txtDescripcion)
        txtAutor = root.findViewById(R.id.txtAutor)
        txtEmail_Autor = root.findViewById(R.id.txtEmail_Autor)
        txtEstado = root.findViewById(R.id.txtEstado)

        val btnAgregar = root.findViewById<Button>(R.id.btnAgregar)

        btnAgregar.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val objConexion = ClaseConexion().cadenaConexion()

                val Agregar = objConexion?.prepareStatement(
                    "INSERT INTO tbtickets (uuid, titulo, descripcion, autor, email_autor, estado) VALUES (?, ?, ?, ?, ?, ?)"
                )!!
                Agregar.setString(1, UUID.randomUUID().toString())
                Agregar.setString(2, txtTitulo.text.toString())
                Agregar.setString(3, txtDescripcion.text.toString())
                Agregar.setString(4,txtAutor.text.toString())
                Agregar.setString(5,txtEmail_Autor.text.toString())
                Agregar.setString(6, txtEstado.text.toString())
                Agregar.executeUpdate()




            }

        }


        return root
    }
    private fun LimpiarCampos(){
        txtTitulo.text.clear()
        txtDescripcion.text.clear()
        txtAutor.text.clear()
        txtEmail_Autor.text.clear()
        txtEstado.text.clear()

    }

}