package dev.whitedog.ejemplofoldable.ui

import android.view.View
import com.whitedog.foldable_activity.enums.FoldPosture
import com.whitedog.foldable_activity.ui.FoldableActivity

abstract class BaseActivity: FoldableActivity() {

    override fun getRootView(): View {
        return View(this)
    }

    override fun onFoldablePostureChanged(foldPosture: FoldPosture, foldPositionFromEnd: Int) {

    }

}