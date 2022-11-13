package dev.whitedog.ejemplofoldable.ui.app_continuity.saved_instance

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import dev.whitedog.ejemplofoldable.R
import dev.whitedog.ejemplofoldable.ui.BaseActivity
import dev.whitedog.ejemplofoldable.utils.dialogs.DialogoPositivo

class SavedInstanceActivity : BaseActivity() {

    companion object {
        private const val KEY_CONTADOR_LOCAL: String = "savedInstance_contador_local"
        private const val KEY_CONTADOR_VISITANTE: String = "savedInstance_contador_visitante"
        private const val KEY_MOSTRANDO_DIALOGO: String = "savedInstance_mostrando_dialogo"
    }

    //----------------------------------

    private lateinit var bLocalMas: Button
    private lateinit var bLocalMenos: Button

    private lateinit var bVisitanteMas: Button
    private lateinit var bVisitanteMenos: Button

    private lateinit var tvContadorLocal: TextView
    private lateinit var tvContadorVisitante: TextView

    private lateinit var bComprobar: Button

    //----------------------------------

    private var contadorLocal: Int = 0
    private var contadorVisitante: Int = 0

    private var mostrandoResultado: Boolean = false

    //----------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_instance)

        inicializarElementos()

        savedInstanceState?.let { savedInstance ->
            recuperarEstado(savedInstance)
        }
    }

    //----------------------------------

    private fun inicializarElementos() {
        bLocalMas = findViewById(R.id.b_savedinstance_local_mas)
        bLocalMenos = findViewById(R.id.b_savedinstance_local_menos)

        bVisitanteMas = findViewById(R.id.b_savedinstance_visitante_mas)
        bVisitanteMenos = findViewById(R.id.b_savedinstance_visitante_menos)

        tvContadorLocal = findViewById(R.id.tv_savedinstance_local_contador)
        tvContadorVisitante = findViewById(R.id.tv_savedinstance_visitante_contador)

        bComprobar = findViewById(R.id.b_savedinstance_comprobar)

        //----------------------------------

        bLocalMas.setOnClickListener {
            modificarContadorLocal(1)
        }

        bLocalMenos.setOnClickListener {
            modificarContadorLocal(-1)
        }

        //----------------------------------

        bVisitanteMas.setOnClickListener {
            modificarContadorVisitante(1)
        }

        bVisitanteMenos.setOnClickListener {
            modificarContadorVisitante(-1)
        }

        bComprobar.setOnClickListener {
            mostrarResultado()
        }

        //----------------------------------

        findViewById<Toolbar>(R.id.toolbar).setNavigationOnClickListener {
            finish()
        }
    }

    private fun modificarContadorLocal(num: Int) {
        contadorLocal += num
        tvContadorLocal.text = contadorLocal.toString()
    }

    private fun modificarContadorVisitante(num: Int) {
        contadorVisitante += num
        tvContadorVisitante.text = contadorVisitante.toString()
    }

    private fun mostrarResultado() {
        val mensaje: String = if(contadorLocal > contadorVisitante) {
            "Ha ganado el equipo local"
        }
        else if(contadorLocal == contadorVisitante) {
            "Han empatado"
        }
        else {
            "Ha ganado el equipo visitante"
        }

        val listener: DialogInterface.OnClickListener = DialogInterface.OnClickListener { _, _ ->
            mostrandoResultado = false
        }

        DialogoPositivo(this, "Resultado", mensaje, listenerBotonPositivo = listener).mostrar()
        mostrandoResultado = true
    }

    //----------------------------------

    private fun recuperarEstado(savedInstance: Bundle) {
        contadorLocal = savedInstance.getInt(KEY_CONTADOR_LOCAL, 0)
        contadorVisitante = savedInstance.getInt(KEY_CONTADOR_VISITANTE, 0)
        mostrandoResultado = savedInstance.getBoolean(KEY_MOSTRANDO_DIALOGO, false)

        tvContadorLocal.text = contadorLocal.toString()
        tvContadorVisitante.text = contadorVisitante.toString()

        if(mostrandoResultado) {
            mostrarResultado()
        }
    }

    //----------------------------------
    //----- ON SAVE INSTANCE STATE -----
    //----------------------------------

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(KEY_CONTADOR_LOCAL, contadorLocal)
        outState.putInt(KEY_CONTADOR_VISITANTE, contadorVisitante)
        outState.putBoolean(KEY_MOSTRANDO_DIALOGO, mostrandoResultado)
    }

}