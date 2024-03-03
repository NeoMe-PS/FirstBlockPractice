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
import com.ps_pn.firstblockpractice.presentation.fragments.user.EditProfileFragment.Companion.EDIT_BUNDLE_KEY_DELETE
import com.ps_pn.firstblockpractice.presentation.fragments.user.EditProfileFragment.Companion.EDIT_BUNDLE_KEY_GET_PHOTO
import com.ps_pn.firstblockpractice.presentation.fragments.user.EditProfileFragment.Companion.EDIT_REQUEST_GET_PHOTO
import com.ps_pn.firstblockpractice.presentation.fragments.user.EditProfileFragment.Companion.EDIT_REQUEST_KEY_DELETE
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date

class EditImageDialog : DialogFragment() {

    lateinit var currentPhotoPath: String

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            checkPermissions()
            val builder = AlertDialog.Builder(it)
            val view = requireActivity().layoutInflater.inflate(R.layout.image_editor_dialog, null)
            val choosePhotoTv = view.findViewById<TextView>(R.id.choose_photo_tv)
            val makePhotoTv = view.findViewById<TextView>(R.id.make_photo_tv)
            val deleteTv = view.findViewById<TextView>(R.id.delete_tv)

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

    private fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
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
        resultLauncher.launch(takePictureIntent)
    }

    private fun setPic(): Bitmap {
        BitmapFactory.decodeFile(currentPhotoPath)
        return BitmapFactory.decodeFile(currentPhotoPath)
    }

    private fun registerDeleteResult() {
        val expectedResult = true
        requireActivity().supportFragmentManager.setFragmentResult(
            EDIT_REQUEST_KEY_DELETE,
            bundleOf(EDIT_BUNDLE_KEY_DELETE to expectedResult))
        this.dismiss()
    }

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val img = setPic()
                val rotatedImg = img.fixRotation(Uri.parse(currentPhotoPath))!!
                sendPhotoResult(rotatedImg)
            }
            this.dismiss()
        }

    private fun sendPhotoResult(bitmap: Bitmap) {
        requireActivity().supportFragmentManager.setFragmentResult(
            EDIT_REQUEST_GET_PHOTO,
            bundleOf(EDIT_BUNDLE_KEY_GET_PHOTO to bitmap))
    }

    private fun Bitmap.fixRotation(uri: Uri): Bitmap? {
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

    private fun Bitmap.rotateImage(angle: Float): Bitmap? {
        val matrix = Matrix()
        matrix.postRotate(angle)
        return Bitmap.createBitmap(
            this, 0, 0, width, height,
            matrix, true)
    }

    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
        }
        if (ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        }
        if (ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CAMERA), 1)
        }
    }
}
