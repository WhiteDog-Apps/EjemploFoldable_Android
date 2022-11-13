package dev.whitedog.ejemplofoldable.ui.optimizacion_ui

import android.content.Context

class OptimizacionUIController(private val context: Context, private val view: OptimizacionUIContract.View, private val viewModel: OptimizacionUIViewModel): OptimizacionUIContract.Controller {

    override fun onCreate() {
        view.inicializarListaComentarios()
    }

    //-----------------------------------------------------------------------------------

    override fun onVolverClick() {
        view.finalizarActivity()
    }

    //-----------------------------------------------------------------------------------

    override fun onDestroy() {
    }
}