package dev.whitedog.ejemplofoldable.utils.dialogs

import android.app.Activity
import android.content.DialogInterface
import android.view.View
import android.widget.TextView
import dev.whitedog.ejemplofoldable.R

class DialogoPositivo(activity: Activity): DialogoBase(activity) {

    private var titulo: String = ""
    private var mensaje: String = ""

    private var botonPositivo: String = activity.getString(R.string.aceptar)
    private var listenerBotonPositivo: DialogInterface.OnClickListener? = null

    //-----------------------------------------------------------------------------------

    constructor(activity: Activity, titulo: String = "", mensaje: String = "", botonPositivo: String = activity.getString(R.string.aceptar), listenerBotonPositivo: DialogInterface.OnClickListener? = null): this(activity) {
        this.titulo = titulo
        this.mensaje = mensaje
        this.botonPositivo = botonPositivo
        this.listenerBotonPositivo = listenerBotonPositivo
    }

    //-----------------------------------------------------------------------------------

    override fun loadCustomView(): View {
        val inflater = activity.layoutInflater
        return inflater.inflate(R.layout.dialogo_basico, null)
    }

    override fun setup() {
        customView?.findViewById<TextView>(R.id.tv_dialogo_basico_titulo)?.text = titulo
        customView?.findViewById<TextView>(R.id.tv_dialogo_basico_mensaje)?.text = mensaje

        builder.setView(customView)
        builder.setCancelable(false)

        listenerBotonPositivo?.let { listener ->
            builder.setPositiveButton(botonPositivo, listener)
        } ?: run {
            builder.setPositiveButton(botonPositivo) { dialogInterface, _ -> dialogInterface.cancel() }
        }
    }

}