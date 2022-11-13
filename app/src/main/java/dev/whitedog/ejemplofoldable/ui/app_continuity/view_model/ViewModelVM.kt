package dev.whitedog.ejemplofoldable.ui.app_continuity.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelVM: ViewModel(), ViewModelContract.ViewModel {

    var contadorLocal: MutableLiveData<Int> = MutableLiveData(0)
    var contadorVisitante: MutableLiveData<Int> = MutableLiveData(0)

    var mostrandoResultado: MutableLiveData<Boolean> = MutableLiveData(false)

    override fun modificarContadorLocal(num: Int) {
        contadorLocal.value?.let { value ->
            contadorLocal.postValue(value + num)
        }
    }

    override fun modificarContadorVisitante(num: Int) {
        contadorVisitante.value?.let { value ->
            contadorVisitante.postValue(value + num)
        }
    }
}