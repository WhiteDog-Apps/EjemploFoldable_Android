package dev.whitedog.ejemplofoldable.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import dev.whitedog.ejemplofoldable.R
import dev.whitedog.ejemplofoldable.databinding.ActivityMainBinding
import dev.whitedog.ejemplofoldable.ui.BaseActivity

class MainActivity : BaseActivity(), MainContract.View {

    private lateinit var binding: ActivityMainBinding
    private lateinit var controller: MainContract.Controller

    private val viewModel: MainViewModel by viewModels()

    //-----------------------------------------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        controller = MainController(applicationContext, this, viewModel)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.controller = controller
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        controller.onCreate()
    }

    override fun getActivity(): BaseActivity {
        return this
    }

    //-----------------------------------------------------------------------------------

    override fun mostrarIntent(intent: Intent) {
        startActivity(intent)
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