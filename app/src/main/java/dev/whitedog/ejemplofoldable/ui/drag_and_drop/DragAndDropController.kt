package dev.whitedog.ejemplofoldable.ui.drag_and_drop

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.FutureTarget

class DragAndDropController(private val context: Context, private val view: DragAndDropContract.View, private val viewModel: DragAndDropViewModel): DragAndDropContract.Controller {

    override fun onCreate() {
        viewModel.bitmapFotoPerfil.observe(view.getActivity()) { bitmap ->
            view.setFotoPerfil(bitmap)
        }

        view.iniciarListenerDragAndDrop()
    }

    //-----------------------------------------------------------------------------------

    override fun tratarImagen(uri: Uri) {
        Thread {
            val futureTarget: FutureTarget<Bitmap> = Glide.with(context).asBitmap().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).load(uri).submit(800, 600)

            viewModel.bitmapFotoPerfil.postValue(futureTarget.get())
        }.start()
    }

    override fun onQuitarImagenClick() {
        viewModel.bitmapFotoPerfil.postValue(null)
    }

    override fun onVolverClick() {
        view.finalizarActivity()
    }

    //-----------------------------------------------------------------------------------

    override fun onDestroy() {
    }
}