package com.ps_pn.firstblockpractice.presentation.fragments.user

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.ps_pn.firstblockpractice.R
import com.ps_pn.firstblockpractice.databinding.FragmentEditProfileBinding

class EditProfileFragment : Fragment() {

    private var _binding: FragmentEditProfileBinding? = null
    private val binding: FragmentEditProfileBinding
        get() = _binding ?: throw RuntimeException("FragmentEditProfileBinding is null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        setEditingDialogListener()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editLogoImg.setOnClickListener {
            openChoosePhotoDialog()
        }
    }

    private fun setEditingDialogListener() {
        setFragmentResultListener(EDIT_REQUEST_KEY_DELETE) { _, _ ->
            binding.editLogoImg.setImageResource(R.drawable.user_icon)
        }
        setFragmentResultListener(EDIT_REQUEST_GET_PHOTO) { _, bundle ->
            binding.editLogoImg.setImageResource(R.drawable.user_icon)
            val takenImage = bundle.get(EDIT_BUNDLE_KEY_GET_PHOTO) as Bitmap
            binding.editLogoImg.setImageBitmap(takenImage)
        }
    }

    private fun openChoosePhotoDialog() {
        EditImageDialog().show(requireActivity().supportFragmentManager, null)
    }

    companion object {
        const val EDIT_REQUEST_KEY_DELETE = "deleteImg"
        const val EDIT_BUNDLE_KEY_DELETE = "deleteImg"
        const val EDIT_REQUEST_GET_PHOTO = "getPhoto"
        const val EDIT_BUNDLE_KEY_GET_PHOTO = "getPhoto"

        @JvmStatic
        fun newInstance() = EditProfileFragment()
    }
}
