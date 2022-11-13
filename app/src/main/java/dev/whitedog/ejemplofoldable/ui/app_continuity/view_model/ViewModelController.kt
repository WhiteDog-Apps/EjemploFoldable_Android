package dev.whitedog.ejemplofoldable.ui.app_continuity.view_model

class ViewModelController(private val view: ViewModelContract.View, private val viewModel: ViewModelVM): ViewModelContract.Controller {

    override fun onCreate() {
        viewModel.mostrandoResultado.observe(view.getActivity()) { mostrarDialogo ->
            if(mostrarDialogo) {
                mostrarResultado()
            }
        }
    }

    private fun mostrarResultado() {
        val contadorLocal: Int = viewModel.contadorLocal.value ?: 0
        val contadorVisitante: Int = viewModel.contadorVisitante.value ?: 0

        val mensaje: String = if(contadorLocal > contadorVisitante) {
            "Ha ganado el equipo local"
        }
        else if(contadorLocal == contadorVisitante) {
            "Han empatado"
        }
        else {
            "Ha ganado el equipo visitante"
        }

        view.mostrarResultado(mensaje)
    }

    override fun onModificarContadorLocalClick(num: Int) {
        viewModel.modificarContadorLocal(num)
    }

    override fun onModificarContadorVisitanteClick(num: Int) {
        viewModel.modificarContadorVisitante(num)
    }

    override fun onComprobarClick() {
        viewModel.mostrandoResultado.postValue(true)
    }

    override fun onVolverClick() {
        view.finalizarActivity()
    }

}