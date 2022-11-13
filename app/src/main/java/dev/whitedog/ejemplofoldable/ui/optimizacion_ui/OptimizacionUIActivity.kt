package dev.whitedog.ejemplofoldable.ui.optimizacion_ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import dev.whitedog.ejemplofoldable.R
import dev.whitedog.ejemplofoldable.adapters.ElementoAdapter
import dev.whitedog.ejemplofoldable.databinding.ActivityOptimizacionUiBinding
import dev.whitedog.ejemplofoldable.ui.BaseActivity

class OptimizacionUIActivity : BaseActivity(), OptimizacionUIContract.View {

    private lateinit var binding: ActivityOptimizacionUiBinding
    private lateinit var controller: OptimizacionUIContract.Controller

    private val viewModel: OptimizacionUIViewModel by viewModels()

    //-----------------------------------------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        controller = OptimizacionUIController(applicationContext, this, viewModel)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_optimizacion_ui)
        binding.controller = controller
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        controller.onCreate()
    }

    override fun getActivity(): BaseActivity {
        return this
    }

    //-----------------------------------------------------------------------------------

    override fun inicializarListaComentarios() {
        binding.rvOptimizacionui.adapter = ElementoAdapter()
    }

    //-----------------------------------------------------------------------------------

    override fun finalizarActivity() {
        finish()
    }

    override fun onDestroy() {
        controller.onDestroy()

        super.onDestroy()
    }

}