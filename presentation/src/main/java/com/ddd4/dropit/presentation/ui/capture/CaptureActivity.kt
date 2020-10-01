package com.ddd4.dropit.presentation.ui.capture

import android.app.Activity
import android.graphics.*
import android.view.TextureView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.ddd4.dropit.presentation.R
import com.ddd4.dropit.presentation.base.ui.BaseActivity
import com.ddd4.dropit.presentation.databinding.ActivityCaptureBinding
import com.ddd4.dropit.presentation.util.Constants
import com.ddd4.dropit.presentation.util.capture.TextureCaptureUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_add.toolbar
import kotlinx.android.synthetic.main.activity_capture.*
import javax.inject.Inject

@AndroidEntryPoint
class CaptureActivity : BaseActivity<ActivityCaptureBinding>(R.layout.activity_capture) {

    private val viewModel: CaptureViewModel by viewModels()

    @Inject
    lateinit var textureCaptureUtil: TextureCaptureUtil

    override fun setBind() {
        binding.apply {
            view = this@CaptureActivity
            captureVM = viewModel
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun setObserve() {
        viewModel.captureComplete.observe(this, Observer { imageUri ->
            val intent = intent.putExtra(Constants.EXTRA_NAME_IMAGE_PATH, imageUri)
            setResult(Activity.RESULT_OK, intent)
            finish()
        })
        viewModel.captureClick.observe(this, Observer {
            textureCaptureUtil.takePicture(this, setImageListener)
        })
    }

    override fun setInit() {
        setSupportActionBar(toolbar)
        supportActionBar.apply {
            this?.setDisplayShowHomeEnabled(true)
            this?.setDisplayHomeAsUpEnabled(true)
            this?.setHomeAsUpIndicator(R.drawable.ic_back_gray_32dp)
            this?.setDisplayShowTitleEnabled(false)
        }
    }

    val textureListener = object: TextureView.SurfaceTextureListener {
        override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture?, width: Int, height: Int) {}
        override fun onSurfaceTextureUpdated(surface: SurfaceTexture?) {}
        override fun onSurfaceTextureDestroyed(surface: SurfaceTexture?): Boolean {
            return false
        }
        override fun onSurfaceTextureAvailable(surface: SurfaceTexture?, width: Int, height: Int) {
            textureCaptureUtil.openCamera(texture)
        }
    }

    private val setImageListener = object: TextureCaptureUtil.OnTextureImageListener {
        override fun onImageCaptured(imageUri: String) {
            viewModel.setCapturedImage(imageUri)
        }
    }
}