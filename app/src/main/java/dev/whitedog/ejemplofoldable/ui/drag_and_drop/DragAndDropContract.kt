package dev.whitedog.ejemplofoldable.ui.drag_and_drop

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import dev.whitedog.ejemplofoldable.ui.BaseActivity

interface DragAndDropContract {

    interface View {
        fun onCreate(savedInstanceState: Bundle?)

        fun getActivity(): BaseActivity

        fun iniciarListenerDragAndDrop()

        fun setFotoPerfil(bitmap: Bitmap?)

        fun finalizarActivity()
        fun onDestroy()
    }

    interface Controller {
        fun onCreate()

        fun onQuitarImagenClick()

        fun tratarImagen(uri: Uri)

        fun onVolverClick()

        fun onDestroy()
    }

    interface ViewModel {

    }

}