package dev.whitedog.ejemplofoldable.ui.app_continuity.view_model

import android.content.DialogInterface
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import dev.whitedog.ejemplofoldable.R
import dev.whitedog.ejemplofoldable.databinding.ActivityViewModelBinding
import dev.whitedog.ejemplofoldable.ui.BaseActivity
import dev.whitedog.ejemplofoldable.utils.dialogs.DialogoPositivo

class ViewModelActivity : BaseActivity(), ViewModelContract.View {

    private lateinit var binding: ActivityViewModelBinding
    private lateinit var controller: ViewModelContract.Controller

    //----------------------------------

    private val viewModel: ViewModelVM by viewModels()

    //----------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        controller = ViewModelController(this, viewModel)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_model)
        binding.controller = controller
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        controller.onCreate()
    }

    override fun getActivity(): BaseActivity {
        return this
    }

    override fun mostrarResultado(mensaje: String) {
        val listener: DialogInterface.OnClickListener = DialogInterface.OnClickListener { _, _ ->
            viewModel.mostrandoResultado.postValue(false)
        }

        DialogoPositivo(this, "Resultado", mensaje, listenerBotonPositivo = listener).mostrar()
    }

    override fun finalizarActivity() {
        finish()
    }

}