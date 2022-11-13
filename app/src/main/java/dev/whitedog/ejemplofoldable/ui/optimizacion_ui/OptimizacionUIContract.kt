package dev.whitedog.ejemplofoldable.ui.optimizacion_ui

import android.os.Bundle
import dev.whitedog.ejemplofoldable.ui.BaseActivity

interface OptimizacionUIContract {

    interface View {
        fun onCreate(savedInstanceState: Bundle?)

        fun getActivity(): BaseActivity

        fun inicializarListaComentarios()

        fun finalizarActivity()
        fun onDestroy()
    }

    interface Controller {
        fun onCreate()

        fun onVolverClick()

        fun onDestroy()
    }

    interface ViewModel {

    }

}