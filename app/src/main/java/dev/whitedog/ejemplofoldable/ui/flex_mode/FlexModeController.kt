package dev.whitedog.ejemplofoldable.ui.flex_mode

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import com.whitedog.foldable_activity.enums.FoldPosture
import dev.whitedog.ejemplofoldable.R

class FlexModeController(private val context: Context, private val view: FlexModeContract.View, private val viewModel: FlexModeViewModel): FlexModeContract.Controller {

    override fun onCreate() {
        viewModel.foldPosture.observe(view.getActivity()) { posture ->
            view.updateFoldUi(posture, viewModel.foldPosition)
        }

        val listaFotos: ArrayList<Drawable> = ArrayList()

        ResourcesCompat.getDrawable(context.resources, R.drawable.foto_gato_1, null)?.let { foto ->
            listaFotos.add(foto)
        }
        ResourcesCompat.getDrawable(context.resources, R.drawable.foto_gato_2, null)?.let { foto ->
            listaFotos.add(foto)
        }

        view.inicializarInterfaz(listaFotos)
    }

    //-----------------------------------------------------------------------------------

    override fun onFoldablePostureChanged(foldPosture: FoldPosture, foldPositionFromEnd: Int) {
        viewModel.foldPosition = foldPositionFromEnd
        viewModel.foldPosture.postValue(foldPosture)
    }

    //-----------------------------------------------------------------------------------

    override fun onVolverClick() {
        view.finalizarActivity()
    }

    //-----------------------------------------------------------------------------------

    override fun onDestroy() {
    }
}