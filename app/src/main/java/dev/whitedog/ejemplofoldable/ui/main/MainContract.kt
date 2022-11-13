package dev.whitedog.ejemplofoldable.ui.main

import android.content.Intent
import android.os.Bundle
import dev.whitedog.ejemplofoldable.ui.BaseActivity

interface MainContract {

    interface View {
        fun onCreate(savedInstanceState: Bundle?)

        fun getActivity(): BaseActivity

        fun mostrarIntent(intent: Intent)

        fun finalizarActivity()
        fun onDestroy()
    }

    interface Controller {
        fun onCreate()

        fun onSinAppContinuityClick()
        fun onSavedInstanceClick()
        fun onViewModelClick()
        fun onOptimizacionUiClick()
        fun onFlexModeClick()
        fun onDragAndDropClick()

        fun onVolverClick()

        fun onDestroy()
    }

    interface ViewModel {

    }

}