package dev.whitedog.ejemplofoldable.ui.flex_mode

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.whitedog.foldable_activity.enums.FoldPosture

class FlexModeViewModel : ViewModel(), FlexModeContract.ViewModel {

    var foldPosture: MutableLiveData<FoldPosture> = MutableLiveData(FoldPosture.UNKNOWN)
    var foldPosition: Int = 0

    var posicionFoto: Int = 0

}