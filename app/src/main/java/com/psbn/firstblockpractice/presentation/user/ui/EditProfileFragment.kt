package com.psbn.firstblockpractice.presentation.user.ui

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.psbn.firstblockpractice.R
import com.psbn.firstblockpractice.core.exception.BindingException
import com.psbn.firstblockpractice.databinding.FragmentEditProfileBinding
import com.psbn.firstblockpractice.presentation.navigateutill.WithoutBottomBar

class EditProfileFragment : Fragment(), WithoutBottomBar {

    private var _binding: FragmentEditProfileBinding? = null
    private val binding: FragmentEditProfileBinding
        get() = _binding ?: throw BindingException(
            "FragmentEditProfileBinding is null"
        )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        setEditingDialogListener()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnLogoClickListener()
        setBackButton()
    }

    private fun setBackButton() {
        binding.imageButtonBack.setOnClickListener {
            val direction =
                EditProfileFragmentDirections.actionEditProfileFragmentToProfileFragment()
            findNavController().navigate(direction)
        }
    }
    private fun setOnLogoClickListener() {
        binding.editLogoImg.setOnClickListener {
            val direction =
                EditProfileFragmentDirections.actionEditProfileFragmentToEditImageDialog()
            findNavController().navigate(direction)
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
        setFragmentResultListener(EDIT_REQUEST_CHOOSE_PHOTO) { _, bundle ->
            binding.editLogoImg.setImageResource(R.drawable.user_icon)
            val takenImage = bundle.get(EDIT_BUNDLE_KEY_CHOOSE_PHOTO) as Bitmap
            binding.editLogoImg.setImageBitmap(takenImage)
        }
    }

    companion object {
        const val EDIT_REQUEST_KEY_DELETE = "deleteImg"
        const val EDIT_BUNDLE_KEY_DELETE = "deleteImg"
        const val EDIT_REQUEST_GET_PHOTO = "getPhoto"
        const val EDIT_BUNDLE_KEY_GET_PHOTO = "getPhoto"
        const val EDIT_REQUEST_CHOOSE_PHOTO = "getPhoto"
        const val EDIT_BUNDLE_KEY_CHOOSE_PHOTO = "getPhoto"
    }
}
