package dev.whitedog.ejemplofoldable.ui.main

import android.content.Context
import android.content.Intent
import dev.whitedog.ejemplofoldable.ui.app_continuity.saved_instance.SavedInstanceActivity
import dev.whitedog.ejemplofoldable.ui.app_continuity.sin_app_continuity.SinAppContinuityActivity
import dev.whitedog.ejemplofoldable.ui.app_continuity.view_model.ViewModelActivity
import dev.whitedog.ejemplofoldable.ui.drag_and_drop.DragAndDropActivity
import dev.whitedog.ejemplofoldable.ui.flex_mode.FlexModeActivity
import dev.whitedog.ejemplofoldable.ui.optimizacion_ui.OptimizacionUIActivity

class MainController(private val context: Context, private val view: MainContract.View, private val viewModel: MainViewModel): MainContract.Controller {

    override fun onCreate() {
    }

    //-----------------------------------------------------------------------------------

    override fun onSinAppContinuityClick() {
        val intent: Intent = Intent(context, SinAppContinuityActivity::class.java)

        mostrarIntent(intent)
    }

    override fun onSavedInstanceClick() {
        val intent: Intent = Intent(context, SavedInstanceActivity::class.java)

        mostrarIntent(intent)
    }

    override fun onViewModelClick() {
        val intent: Intent = Intent(context, ViewModelActivity::class.java)

        mostrarIntent(intent)
    }

    override fun onOptimizacionUiClick() {
        val intent: Intent = Intent(context, OptimizacionUIActivity::class.java)

        mostrarIntent(intent)
    }

    override fun onFlexModeClick() {
        val intent: Intent = Intent(context, FlexModeActivity::class.java)

        mostrarIntent(intent)
    }

    override fun onDragAndDropClick() {
        val intent: Intent = Intent(context, DragAndDropActivity::class.java)

        mostrarIntent(intent)
    }

    //-----------------------------------------------------------------------------------

    private fun mostrarIntent(intent: Intent, finalizarActual: Boolean = false) {
        view.mostrarIntent(intent)
        
        if(finalizarActual) {
            view.finalizarActivity()
        }
    }

    //-----------------------------------------------------------------------------------

    override fun onVolverClick() {
        view.finalizarActivity()
    }

    //-----------------------------------------------------------------------------------

    override fun onDestroy() {
    }
}