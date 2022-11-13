package dev.whitedog.ejemplofoldable.utils.dialogs

import android.app.Activity
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder

abstract class DialogoBase(protected var activity: Activity) {

    protected var alertDialog: AlertDialog? = null

    protected var builder: MaterialAlertDialogBuilder = MaterialAlertDialogBuilder(activity)
    protected var customView: View? = null

    //-----------------------------------------------------------------------------------

    /**
     * Devuelve el View para crear el dialogo
     */
    abstract fun loadCustomView(): View

    /**
     * Configura el dialogo con los datos obtenidos en el constructor. Inicializa los elementos vacios con valores por defecto
     */
    abstract fun setup()

    //-----------------------------------------------------------------------------------

    /**
     * Muestra el dialogo
     */
    fun mostrar() {
        customView = loadCustomView()
        setup()

        alertDialog = builder.create()

        alertDialog?.show()
    }

    fun finalizar() {
        alertDialog?.dismiss()
    }

}