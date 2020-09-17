package com.ddd4.dropit.presentation.util.capture

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.*
import android.hardware.camera2.*
import android.media.Image
import android.media.ImageReader
import android.net.Uri
import android.provider.MediaStore
import android.util.Size
import android.util.SparseIntArray
import android.view.Surface
import android.view.TextureView
import com.ddd4.dropit.presentation.util.toast
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import timber.log.Timber
import java.io.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@ActivityScoped
class TextureCaptureUtil @Inject constructor(
    @ActivityContext private val context: Context
) {

    private lateinit var cameraManager: CameraManager
    private lateinit var cameraId: String
    private lateinit var imageDimension: Size

    private lateinit var cameraDevice: CameraDevice
    private lateinit var textureView: TextureView

    @SuppressLint("MissingPermission")
    fun openCamera(textureView: TextureView) {
        cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager

        try {
            cameraId = cameraManager.cameraIdList[0]
            this.textureView = textureView

            val characteristics = cameraManager.getCameraCharacteristics(cameraId)
            val map = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)

            imageDimension = map!!.getOutputSizes<SurfaceTexture>(SurfaceTexture::class.java)[0]

            cameraManager.openCamera(cameraId, stateCallback, null)

        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }
    }

    private val stateCallback = object : CameraDevice.StateCallback() {
        override fun onOpened(camera: CameraDevice) {
            Timber.d("stateCallback : onOpened")
            cameraDevice = camera

            createCameraPreviewSession()
        }
        override fun onDisconnected(camera: CameraDevice) {
            Timber.d("stateCallback : onDisconnected")
            cameraDevice.close()
        }
        override fun onError(camera: CameraDevice, error: Int) {
            Timber.d("stateCallback : onError")
            cameraDevice.close()
        }
    }

    private fun createCameraPreviewSession() {
        try {
            val texture = textureView.surfaceTexture
            texture.setDefaultBufferSize(imageDimension.width, imageDimension.height)

            val surface = Surface(texture)
            val captureRequestBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)
            captureRequestBuilder.addTarget(surface)

            cameraDevice.createCaptureSession(listOf(surface), object : CameraCaptureSession.StateCallback() {
                override fun onConfigureFailed(session: CameraCaptureSession) {}
                override fun onConfigured(session: CameraCaptureSession) {
                    val cropRect = Rect(0, 0, 1755, 3120)
                    captureRequestBuilder.set(CaptureRequest.SCALER_CROP_REGION, cropRect)

                    try {
                        session.setRepeatingRequest(captureRequestBuilder.build(), null, null)
                    } catch (e: CameraAccessException) {
                        e.printStackTrace()
                    }
                }
            }, null)
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }
    }

    fun takePicture(activity: Activity, onTextureImageListener: OnTextureImageListener) {
        try {
            val characteristics = cameraManager.getCameraCharacteristics(cameraDevice.id)
            val jpegSizes = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)!!.getOutputSizes(
                ImageFormat.JPEG)

            val width = jpegSizes[0].width
            val height = jpegSizes[0].height

            val imageReader = ImageReader.newInstance(width, height, ImageFormat.JPEG, 1)

            val outputSurface = ArrayList<Surface>(2)
            outputSurface.add(imageReader.surface)
            outputSurface.add(Surface(textureView.surfaceTexture))

            val captureBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE)
            captureBuilder.addTarget(imageReader.surface)
            captureBuilder.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO)

            val sparseIntArray = SparseIntArray()

            val rotation = activity.windowManager.defaultDisplay.rotation
            captureBuilder.set(CaptureRequest.JPEG_ORIENTATION, sparseIntArray.get(rotation))

            val timeStamp = SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA).format(Date())
            val imageName = "${timeStamp}_"
            val storageDir = activity.filesDir
            val imageFile = File.createTempFile(imageName, ".jpg", storageDir)

            val readerListener = ImageReader.OnImageAvailableListener {
                var image : Image? = null

                try {
                    image = imageReader.acquireLatestImage()

                    val buffer = image!!.planes[0].buffer
                    val bytes = ByteArray(buffer.capacity())
                    buffer.get(bytes)

                    var output: OutputStream? = null
                    try {
                        output = FileOutputStream(imageFile)
                        output.write(bytes)
                    } finally {
                        output?.close()

                        val uri = Uri.fromFile(imageFile)
                        Timber.d("Image Uri Path $uri")

                        val bitmap: Bitmap = BitmapFactory.decodeFile(imageFile.path)

                        val rotateMatrix = Matrix()
                        rotateMatrix.postRotate(90F)
                        val rotatedBitmap = Bitmap.createBitmap(bitmap, 0,0, bitmap.width, bitmap.height, rotateMatrix, false)

                        //90도 돌아간 비트맵을 이미지뷰에 set 해준다
                        //Timber.d("이미지 비트맵: $rotatedBitmap")
                        onTextureImageListener.onImageCaptured(getImageUri(rotatedBitmap).toString())
                    }
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                } finally {
                    image?.close()
                }
            }

            imageReader.setOnImageAvailableListener(readerListener, null)

            val captureListener = object : CameraCaptureSession.CaptureCallback() {
                override fun onCaptureCompleted(session: CameraCaptureSession, request: CaptureRequest, result: TotalCaptureResult) {
                    super.onCaptureCompleted(session, request, result)
                    context.toast("이미지를 가져왔습니다")
                    createCameraPreviewSession()
                }
            }

            cameraDevice.createCaptureSession(outputSurface, object : CameraCaptureSession.StateCallback() {
                override fun onConfigureFailed(session: CameraCaptureSession) {}
                override fun onConfigured(session: CameraCaptureSession) {
                    try {
                        session.capture(captureBuilder.build(), captureListener, null)
                    } catch (e: CameraAccessException) {
                        e.printStackTrace()
                    }
                }
            }, null)
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }
    }

    private fun getImageUri(bitmap: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)

        val titleFormat = SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA)
        val title = titleFormat.format(Date())
        val path = MediaStore.Images.Media.insertImage(context.contentResolver, bitmap, title, null)

        return Uri.parse(path)
    }

    interface OnTextureImageListener {
        fun onImageCaptured(imageUri: String)
    }
}