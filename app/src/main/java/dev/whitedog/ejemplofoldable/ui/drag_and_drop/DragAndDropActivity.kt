package dev.whitedog.ejemplofoldable.ui.drag_and_drop

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.util.component1
import androidx.core.util.component2
import androidx.core.view.OnReceiveContentListener
import androidx.databinding.DataBindingUtil
import androidx.draganddrop.DropHelper
import com.bumptech.glide.Glide
import dev.whitedog.ejemplofoldable.R
import dev.whitedog.ejemplofoldable.databinding.ActivityDragAndDropBinding
import dev.whitedog.ejemplofoldable.ui.BaseActivity
import dev.whitedog.ejemplofoldable.utils.size_converter.SizeConverter

class DragAndDropActivity : BaseActivity(), DragAndDropContract.View {

    private lateinit var binding: ActivityDragAndDropBinding
    private lateinit var controller: DragAndDropContract.Controller

    private val viewModel: DragAndDropViewModel by viewModels()

    //-----------------------------------------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        controller = DragAndDropController(applicationContext, this, viewModel)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_drag_and_drop)
        binding.controller = controller
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        controller.onCreate()
    }

    override fun getActivity(): BaseActivity {
        return this
    }

    //-----------------------------------------------------------------------------------

    override fun iniciarListenerDragAndDrop() {
        val onReceiveContentListener: OnReceiveContentListener = OnReceiveContentListener { _, payload ->
            val (uriContent, remaining) = payload.partition { item -> item.uri != null }

            if(uriContent != null) {
                val clip = uriContent.clip

                if(clip.itemCount > 0) {
                    val uri = clip.getItemAt(0).uri
                    controller.tratarImagen(uri)
                }
            }

            remaining
        }

        val options: DropHelper.Options = DropHelper.Options.Builder()
            .setHighlightColor(getColor(R.color.verde))
            .setHighlightCornerRadiusPx(SizeConverter.dpToPx(16.0f, this))
            .build()

        DropHelper.configureView(this, binding.ibDragdropFoto, arrayOf("image/*"), options, onReceiveContentListener)
    }

    //-----------------------------------------------------------------------------------

    override fun setFotoPerfil(bitmap: Bitmap?) {
        bitmap?.let { imagen ->
            Glide.with(this)
                .load(imagen)
                .circleCrop()
                .placeholder(ContextCompat.getDrawable(this, R.drawable.ic_person_96))
                .into(binding.ibDragdropFoto)

            binding.ibDragdropQuitarFoto.visibility = View.VISIBLE
        } ?: run {
            Glide.with(this).clear(binding.ibDragdropFoto)

            binding.ibDragdropQuitarFoto.visibility = View.INVISIBLE
        }
    }

    //-----------------------------------------------------------------------------------

    override fun finalizarActivity() {
        finish()
    }

    override fun onDestroy() {
        controller.onDestroy()

        super.onDestroy()
    }

}