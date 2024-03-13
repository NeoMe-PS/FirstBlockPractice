package com.ps_pn.firstblockpractice.presentation.fragments.user

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.ps_pn.firstblockpractice.R
import com.ps_pn.firstblockpractice.presentation.fragments.user.EditProfileFragment.Companion.EDIT_BUNDLE_KEY_CHOOSE_PHOTO
import com.ps_pn.firstblockpractice.presentation.fragments.user.EditProfileFragment.Companion.EDIT_BUNDLE_KEY_DELETE
import com.ps_pn.firstblockpractice.presentation.fragments.user.EditProfileFragment.Companion.EDIT_BUNDLE_KEY_GET_PHOTO
import com.ps_pn.firstblockpractice.presentation.fragments.user.EditProfileFragment.Companion.EDIT_REQUEST_CHOOSE_PHOTO
import com.ps_pn.firstblockpractice.presentation.fragments.user.EditProfileFragment.Companion.EDIT_REQUEST_GET_PHOTO
import com.ps_pn.firstblockpractice.presentation.fragments.user.EditProfileFragment.Companion.EDIT_REQUEST_KEY_DELETE
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.toLocalDateTime
import java.io.File
import java.io.IOException

class EditImageDialog : DialogFragment() {
    lateinit var currentPhotoPath: String

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        checkPermissions()
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val view = requireActivity().layoutInflater.inflate(R.layout.image_editor_dialog, null)
            val choosePhotoTv = view.findViewById<TextView>(R.id.choose_photo_tv)
            val makePhotoTv = view.findViewById<TextView>(R.id.make_photo_tv)
            val deleteTv = view.findViewById<TextView>(R.id.delete_tv)

            choosePhotoTv.setOnClickListener {
                pickPhoto()
            }

            deleteTv.setOnClickListener {
                registerDeleteResult()
            }

            makePhotoTv.setOnClickListener {
                dispatchTakePictureIntent()
            }

            builder.setView(view)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun pickPhoto() {
        val photoFromGalleryIntent = Intent()
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.S_V2) {
            photoFromGalleryIntent.setAction(MediaStore.ACTION_PICK_IMAGES)
        } else {
            photoFromGalleryIntent.setAction(Intent.ACTION_PICK)
        }
        photoFromGalleryIntent.setDataAndType(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            "image/*"
        )
        choosePhotoResultLauncher.launch(photoFromGalleryIntent)
    }

    private fun createImageFile(): File {
        val timeStamp: String = getTimeStamp()
        val storageDir: File =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        ).apply {
            currentPhotoPath = absolutePath
        }
    }

    private fun getTimeStamp(): String {
        val formatPattern = "yyyyMMdd_HHmmss"

        @OptIn(FormatStringsInDatetimeFormats::class)
        val dateTimeFormat = LocalDateTime.Format {
            byUnicodePattern(formatPattern)
        }
        return dateTimeFormat.format(
            Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        )
    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        takePictureIntent.resolveActivity(requireActivity().packageManager)
        val photoFile: File? = try {
            createImageFile()
        } catch (ex: IOException) {
            Toast.makeText(requireContext(), "failed to save file", Toast.LENGTH_SHORT).show()
            return
        }
        photoFile?.let {
            val photoURI: Uri = FileProvider.getUriForFile(
                requireActivity(),
                "com.ps_pn.firstblockpractice.provider",
                it
            )
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
        }
        makePhotoResultLauncher.launch(takePictureIntent)
    }

    private fun setPic(imgUri: String): Bitmap {
        return BitmapFactory.decodeFile(imgUri)
    }

    private fun registerDeleteResult() {
        val expectedResult = true
        requireActivity().supportFragmentManager.setFragmentResult(
            EDIT_REQUEST_KEY_DELETE,
            bundleOf(EDIT_BUNDLE_KEY_DELETE to expectedResult)
        )
        this.dismiss()
    }

    private val choosePhotoResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val imgUri = result.data?.data
                imgUri?.let {
                    val inputStream = requireActivity().contentResolver.openInputStream(it)
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    sendChosenPhotoResult(bitmap)
                }
            }
            this.dismiss()
        }

    private val makePhotoResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val img = setPic(currentPhotoPath)
                val rotatedImg = img.fixRotation(Uri.parse(currentPhotoPath))
                sendSnapshotResult(rotatedImg)
            }
            this.dismiss()
        }

    private fun sendSnapshotResult(bitmap: Bitmap) {
        requireActivity().supportFragmentManager.setFragmentResult(
            EDIT_REQUEST_GET_PHOTO,
            bundleOf(EDIT_BUNDLE_KEY_GET_PHOTO to bitmap)
        )
    }

    private fun sendChosenPhotoResult(bitmap: Bitmap) {
        requireActivity().supportFragmentManager.setFragmentResult(
            EDIT_REQUEST_CHOOSE_PHOTO,
            bundleOf(EDIT_BUNDLE_KEY_CHOOSE_PHOTO to bitmap)
        )
    }

    private fun Bitmap.fixRotation(uri: Uri): Bitmap {
        val ei = ExifInterface(uri.path!!)

        val orientation: Int = ei.getAttributeInt(
            ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_UNDEFINED
        )
        Log.i("TestLOG", "orientation : $orientation")
        return when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> rotateImage(90f)
            ExifInterface.ORIENTATION_ROTATE_180 -> rotateImage(180f)
            ExifInterface.ORIENTATION_ROTATE_270 -> rotateImage(270f)
            ExifInterface.ORIENTATION_NORMAL -> this
            else -> this
        }
    }

    private fun Bitmap.rotateImage(angle: Float): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(angle)
        return Bitmap.createBitmap(
            this, 0, 0, width, height,
            matrix, true
        )
    }

    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1
            )
        }
        if (ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1
            )
        }
        if (ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CAMERA), 1
            )
        }
    }
}
