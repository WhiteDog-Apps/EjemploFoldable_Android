package dev.whitedog.ejemplofoldable.adapters

import android.graphics.drawable.Drawable
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ortiz.touchview.OnTouchImageViewListener
import dev.whitedog.ejemplofoldable.R
import dev.whitedog.ejemplofoldable.callbacks.OnFotoZoomChangedCallback
import dev.whitedog.ejemplofoldable.databinding.AdapterFotoBinding

class FotoAdapter(private val fotos: ArrayList<Drawable>, private val callback: OnFotoZoomChangedCallback): RecyclerView.Adapter<FotoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterFotoBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val foto: Drawable = fotos[position]

        holder.bind(foto)
    }

    override fun getItemCount(): Int {
        return fotos.size
    }

    inner class ViewHolder(private val binding: AdapterFotoBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(foto: Drawable) {
            mostrarFoto(foto)

            binding.executePendingBindings()
        }

        //-----------------------------------------------------------------------------------

        private fun mostrarFoto(foto: Drawable) {
            Glide.with(itemView.context)
                .load(foto)
                .placeholder(ContextCompat.getDrawable(itemView.context, R.drawable.ic_foto_48))
                .into(binding.ivFotoPublicacionFoto)

            binding.ivFotoPublicacionFoto.setOnTouchImageViewListener(object: OnTouchImageViewListener {
                override fun onMove() {
                    callback.onFotoZoomChanged(binding.ivFotoPublicacionFoto.isZoomed)
                }
            })

            binding.ivFotoPublicacionFoto.setOnDoubleTapListener(object: GestureDetector.OnDoubleTapListener {
                override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
                    callback.onFotoZoomChanged(binding.ivFotoPublicacionFoto.isZoomed)
                    return true
                }

                override fun onDoubleTap(e: MotionEvent?): Boolean {
                    callback.onFotoZoomChanged(binding.ivFotoPublicacionFoto.isZoomed)
                    return true
                }

                override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
                    callback.onFotoZoomChanged(binding.ivFotoPublicacionFoto.isZoomed)
                    return true
                }
            })
        }

    }

}