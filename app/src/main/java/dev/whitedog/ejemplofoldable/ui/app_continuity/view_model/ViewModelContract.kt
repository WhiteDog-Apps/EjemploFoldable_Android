package dev.whitedog.ejemplofoldable.ui.app_continuity.view_model

import dev.whitedog.ejemplofoldable.ui.BaseActivity

interface ViewModelContract {

    interface View {
        fun getActivity(): BaseActivity

        fun mostrarResultado(mensaje: String)

        fun finalizarActivity()
    }

    interface Controller {
        fun onCreate()

        fun onModificarContadorLocalClick(num: Int)
        fun onModificarContadorVisitanteClick(num: Int)

        fun onComprobarClick()

        fun onVolverClick()
    }

    interface ViewModel {
        fun modificarContadorLocal(num: Int)
        fun modificarContadorVisitante(num: Int)
    }

}