package com.ps_pn.firstblockpractice.presentation.fragments.user

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.ps_pn.firstblockpractice.R
import com.ps_pn.firstblockpractice.presentation.fragments.user.EditProfileFragment.Companion.EDIT_BUNDLE_KEY_DELETE
import com.ps_pn.firstblockpractice.presentation.fragments.user.EditProfileFragment.Companion.EDIT_BUNDLE_KEY_GET_PHOTO
import com.ps_pn.firstblockpractice.presentation.fragments.user.EditProfileFragment.Companion.EDIT_REQUEST_GET_PHOTO
import com.ps_pn.firstblockpractice.presentation.fragments.user.EditProfileFragment.Companion.EDIT_REQUEST_KEY_DELETE

class EditImageDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val view = requireActivity().layoutInflater.inflate(R.layout.image_editor_dialog, null)
            val choosePhotoTv = view.findViewById<TextView>(R.id.choose_photo_tv)
            val makePhotoTv = view.findViewById<TextView>(R.id.make_photo_tv)
            val deleteTv = view.findViewById<TextView>(R.id.delete_tv)

            deleteTv.setOnClickListener {
                registerDeleteResult()
            }
            makePhotoTv.setOnClickListener {
                openCameraForResult()
            }
            builder.setView(view)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun registerDeleteResult() {
        val expectedResult = true
        requireActivity().supportFragmentManager.setFragmentResult(
            EDIT_REQUEST_KEY_DELETE,
            bundleOf(EDIT_BUNDLE_KEY_DELETE to expectedResult))
        this.dismiss()
    }

    private fun openCameraForResult() {
        val intentCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        resultLauncher.launch(intentCamera)
    }

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                val capturedImg = data?.extras?.get("data") as Bitmap
                sendPhotoResult(capturedImg)
            }
            this.dismiss()
        }

    private fun sendPhotoResult(bitmap: Bitmap) {
        requireActivity().supportFragmentManager.setFragmentResult(
            EDIT_REQUEST_GET_PHOTO,
            bundleOf(EDIT_BUNDLE_KEY_GET_PHOTO to bitmap))
    }
}
