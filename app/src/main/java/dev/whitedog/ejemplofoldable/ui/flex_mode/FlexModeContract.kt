package dev.whitedog.ejemplofoldable.ui.flex_mode

import android.graphics.drawable.Drawable
import android.os.Bundle
import com.whitedog.foldable_activity.enums.FoldPosture
import dev.whitedog.ejemplofoldable.ui.BaseActivity

interface FlexModeContract {

    interface View {
        fun onCreate(savedInstanceState: Bundle?)

        fun getActivity(): BaseActivity

        fun updateFoldUi(foldPosture: FoldPosture, foldPositionFromEnd: Int)

        fun inicializarInterfaz(listaFotos: ArrayList<Drawable>)

        fun finalizarActivity()
        fun onDestroy()
    }

    interface Controller {
        fun onCreate()

        fun onFoldablePostureChanged(foldPosture: FoldPosture, foldPositionFromEnd: Int)

        fun onVolverClick()

        fun onDestroy()
    }

    interface ViewModel {

    }

}