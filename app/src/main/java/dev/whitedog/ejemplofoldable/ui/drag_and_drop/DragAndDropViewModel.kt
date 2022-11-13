package dev.whitedog.ejemplofoldable.ui.drag_and_drop

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DragAndDropViewModel : ViewModel(), DragAndDropContract.ViewModel {

    var bitmapFotoPerfil: MutableLiveData<Bitmap> = MutableLiveData(null)

}