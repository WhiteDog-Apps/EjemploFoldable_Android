package dev.whitedog.ejemplofoldable.ui.flex_mode

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintSet
import androidx.databinding.DataBindingUtil
import androidx.transition.TransitionManager
import androidx.viewpager2.widget.ViewPager2
import com.whitedog.foldable_activity.enums.FoldPosture
import dev.whitedog.ejemplofoldable.R
import dev.whitedog.ejemplofoldable.adapters.FotoAdapter
import dev.whitedog.ejemplofoldable.callbacks.OnFotoZoomChangedCallback
import dev.whitedog.ejemplofoldable.databinding.ActivityFlexModeBinding
import dev.whitedog.ejemplofoldable.ui.BaseActivity
import dev.whitedog.ejemplofoldable.utils.size_converter.SizeConverter

class FlexModeActivity : BaseActivity(), FlexModeContract.View, OnFotoZoomChangedCallback {

    private lateinit var binding: ActivityFlexModeBinding
    private lateinit var controller: FlexModeContract.Controller

    private val viewModel: FlexModeViewModel by viewModels()

    private var adapter: FotoAdapter? = null

    //-----------------------------------------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        controller = FlexModeController(applicationContext, this, viewModel)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_flex_mode)
        binding.controller = controller
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        controller.onCreate()
    }

    override fun getActivity(): BaseActivity {
        return this
    }

    //-----------------------------------------------------------------------------------

    @SuppressLint("ClickableViewAccessibility")
    override fun inicializarInterfaz(listaFotos: ArrayList<Drawable>) {
        binding.vFlexmodeGestos.setOnTouchListener { _, event ->
            binding.vpFlexmode.dispatchTouchEvent(event)
        }

        adapter = FotoAdapter(listaFotos, this)
        binding.vpFlexmode.adapter = adapter

        binding.ciFlexmode.setViewPager(binding.vpFlexmode)

        adapter?.registerAdapterDataObserver(binding.ciFlexmode.adapterDataObserver)

        binding.vpFlexmode.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                viewModel.posicionFoto = position
            }
        })

        val pos: Int = viewModel.posicionFoto
        if(pos >= 0 && pos < listaFotos.size) {
            binding.vpFlexmode.setCurrentItem(pos, false)
        }
    }
    
    //-----------------------------------------------------------------------------------

    override fun updateFoldUi(foldPosture: FoldPosture, foldPositionFromEnd: Int) {
        val constraintSet: ConstraintSet = ConstraintSet()
        constraintSet.clone(binding.clFlexmodeRoot)

        val orientacion: Int = resources.configuration.orientation

        if(foldPosture == FoldPosture.TABLE_TOP) {
            constraintSet.setGuidelineEnd(R.id.gl_flexmode_tabletop, foldPositionFromEnd)
            constraintSet.clear(R.id.ci_flexmode, ConstraintSet.BOTTOM)

            if(orientacion == Configuration.ORIENTATION_PORTRAIT) {
                //----- Z Flip -----
                constraintSet.connect(R.id.vp_flexmode, ConstraintSet.BOTTOM, R.id.gl_flexmode_tabletop, ConstraintSet.TOP, SizeConverter.dpToPx(16f, this))
                constraintSet.connect(R.id.ci_flexmode, ConstraintSet.TOP, R.id.gl_flexmode_tabletop, ConstraintSet.BOTTOM, SizeConverter.dpToPx(16f, this))
                constraintSet.connect(R.id.cv_flexmode_gestos, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, SizeConverter.dpToPx(48f, this))
            }
            else {
                //----- Z Fold -----
                constraintSet.connect(R.id.vp_flexmode, ConstraintSet.BOTTOM, R.id.gl_flexmode_tabletop, ConstraintSet.TOP, SizeConverter.dpToPx(8f, this))
                constraintSet.connect(R.id.ci_flexmode, ConstraintSet.TOP, R.id.gl_flexmode_tabletop, ConstraintSet.BOTTOM, SizeConverter.dpToPx(8f, this))
                constraintSet.connect(R.id.cv_flexmode_gestos, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, SizeConverter.dpToPx(16f, this))
            }
        }
        else {
            constraintSet.setGuidelineEnd(R.id.gl_flexmode_tabletop, 0)
            constraintSet.connect(R.id.vp_flexmode, ConstraintSet.BOTTOM, R.id.ci_flexmode, ConstraintSet.TOP, SizeConverter.dpToPx(16f, this))
            constraintSet.clear(R.id.ci_flexmode, ConstraintSet.TOP)
            constraintSet.connect(R.id.ci_flexmode, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0)
            constraintSet.clear(R.id.cv_flexmode_gestos, ConstraintSet.BOTTOM)
        }

        TransitionManager.beginDelayedTransition(binding.clFlexmodeRoot)
        constraintSet.applyTo(binding.clFlexmodeRoot)
    }

    //-----------------------------------------------------------------------------------

    override fun onFotoZoomChanged(isZoomed: Boolean) {
        binding.vpFlexmode.isUserInputEnabled = !isZoomed
    }

    //-----------------------------------------------------------------------------------

    override fun getRootView(): View {
        return binding.clFlexmodeRoot
    }

    override fun onFoldablePostureChanged(foldPosture: FoldPosture, foldPositionFromEnd: Int) {
        controller.onFoldablePostureChanged(foldPosture, foldPositionFromEnd)
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