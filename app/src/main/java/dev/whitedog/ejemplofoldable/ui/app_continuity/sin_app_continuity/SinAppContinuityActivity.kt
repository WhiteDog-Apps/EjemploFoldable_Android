package dev.whitedog.ejemplofoldable.ui.app_continuity.sin_app_continuity

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import dev.whitedog.ejemplofoldable.R
import dev.whitedog.ejemplofoldable.ui.BaseActivity
import dev.whitedog.ejemplofoldable.utils.dialogs.DialogoPositivo

class SinAppContinuityActivity : BaseActivity() {

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

    //----------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sin_app_continuity)

        inicializarElementos()
    }

    //----------------------------------

    private fun inicializarElementos() {
        bLocalMas = findViewById(R.id.b_sinappcontinuity_local_mas)
        bLocalMenos = findViewById(R.id.b_sinappcontinuity_local_menos)

        bVisitanteMas = findViewById(R.id.b_sinappcontinuity_visitante_mas)
        bVisitanteMenos = findViewById(R.id.b_sinappcontinuity_visitante_menos)

        tvContadorLocal = findViewById(R.id.tv_sinappcontinuity_local_contador)
        tvContadorVisitante = findViewById(R.id.tv_sinappcontinuity_visitante_contador)

        bComprobar = findViewById(R.id.b_sinappcontinuity_comprobar)

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

        DialogoPositivo(this, "Resultado", mensaje).mostrar()
    }

}