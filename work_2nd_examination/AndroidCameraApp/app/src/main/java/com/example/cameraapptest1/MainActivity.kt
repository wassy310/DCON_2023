package com.example.cameraapptest1

import android.app.Activity
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.Manifest
import android.os.Bundle
import android.R
import android.graphics.ImageFormat
import android.hardware.camera2.CameraCaptureSession
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CaptureRequest
import android.hardware.camera2.params.OutputConfiguration
import android.hardware.camera2.params.SessionConfiguration
import android.media.ImageReader
import android.os.Handler
import android.os.HandlerThread
import android.view.Surface
import android.view.TextureView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setup()
    }

    fun setup() {

    }
}

class CameraProcessor {
    private var cameraDevice: CameraDevice? = null
    private val REQUEST_CAMERA_PERMISSION = 1
    private var activity: Activity
    private val textureView: TextureView

    constructor(textureView: TextureViewm, activity: Activity) {
        this.textureView = textureView
        this.activity = activity

        fun take() {
            this.cameraCaptureSession?.stopRepeating()
            this.bitmap = this.textureView.bitmap

            val request = this.createPreviewRequest(this.cameraDevice!!)
            this.cameraCaptureSession?.setRepeatingRequest(request, null, null)
        }
    }

    private fun showDialogCameraPermission() {
        if(!ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)) {
            ActivityCompat.requestPermissions(
                activity, arrayOf(Manifest.permission.CAMERA),
                REQUEST_CAMERA_PERMISSION
            )
            return
        }

        AlertDialog.Builder(activity)
            .setMessage("R string request permission")
            .setPositiveButton(R.string.ok) { dialog, which ->
                ActivityCompat.requestPermissions(
                    activity, arrayOf(Manifest.permission.CAMERA),
                    REQUEST_CAMERA_PERMISSION
                )
            }
            .setNegativeButton(R.string.cancel) { dialog, which -> }
            .create()
    }

    private var mBackgroundThread: HandlerThread? = null
    private var mBackgroundHandler: Handler? = null

    private fun startBackgroundThread() {
        val thread = HandlerThread("CameraBackground");

        mBackgroundHandler = Handler(thread.looper)
        mBackgroundThread = thread

        thread.start();
    }

    private fun stopBackgroundThread() {
        mBackgroundThread?.quitSafely()

        mBackgroundThread?.join()
        mBackgroundThread = null
        mBackgroundHandler = null
    }

    fun setup() {
        if (activity.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY) === false)
            return

        val currentPermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)
        if (currentPermission != PackageManager.PERMISSION_GRANTED) {
            showDialogCameraPermission()
            return
        }

        if (cameraDevice !== null) {
            return
        }

        val cameraManager
        val cameraId: String = cameraManager.cameraIdList[0]
        cameraManager.openCamera(cameraId, object: CameraDevice.StateCallback() {
            override fun onOpened(cameraDevice: CameraDevice) {
                this@CameraProcessor.cameraDevice = cameraDevice
                createCameraPreviewSession()
            }

            override fun onDisconnected(cameraDevice: CameraDevice) {
                cameraDevice.close()
                this@CameraProcessor.cameraDevice = null
                stopBackgroundThread()
            }
            override fun onError(cameraDevice: CameraDevice, error: Int) {
                cameraDevice.close()
                this@CameraProcessor.cameraDevice = null
                stopBackgroundThread()
            }
        }, mBackgroundHandler)
    }

    fun createCameraPreviewSession() {
        if(this.cameraDevice == null)
            return

        val cameraDevice = this.cameraDevice!!
        val texture = this.textureView.surfaceTexture
        val surface = Surface(texture)
        val imageReader = ImageReader.newInstance(
            /* width = */ this.textureView.width,
            /* height = */ this.textureView.height,
            /* format = */ ImageFormat.JPEG,
            /* maxImages = */ 2
        )

        val surfaces: List = Arrays.asList(surface, imageReader?.surface)
        val type = SessionConfiguration.SESSION_REGULAR
        val configurations: List = surfaces.map { OutputConfiguration(it) }
        val executor = this.activity.mainExecutor
        val previewRequest = this.createPreviewRequest(cameraDevice)
        val callback = object: CameraCaptureSession.StateCallback() {
            override fun onConfigured(cameraCaptureSession: CameraCaptureSession) {
                this@CameraProcessor.cameraCaptureSession = cameraCaptureSession
                cameraCaptureSession.setRepeatingRequest(previewRequest, null, null)
            }

            override fun onConfigureFailed(session: CameraCaptureSession) {

            }
        }

        val configuration = SessionConfiguration(type, configurations, executor, callback)
        cameraDevice.createCaptureSession(configuration)
    }

    private var cameraCaptureSession: CameraCaptureSession? = null

    private fun createPreviewRequest(cameraDevice: CameraDevice): CaptureRequest {
        val surface = Surface(this.textureView.surfaceTexture)
        val imageReader = ImageReader.newInstance(
            /* width = */ this.textureView.width,
            /* height = */ this.textureView.height,
            /* format = */ ImageFormat.JPEG,
            /* maxImages = */ 2
        )

        val previewRequestBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)
        previewRequestBuilder.addTarget(surface)
        previewRequestBuilder.set(
            CaptureRequest.CONTROL_AF_MODE,
            CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE
        )

        return previewRequestBuilder.build()
    }
}