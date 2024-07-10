package com.psbn.news.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.psbn.firstblockpractice.core.R
import com.psbn.firstblockpractice.core.exception.BindingException
import com.psbn.firstblockpractice.core.uiUtills.HasCustomBottomBar
import com.psbn.news.data.DonateWorker
import com.psbn.news.databinding.FragmentNewsDetailBinding

const val MONEY_DIALOG_REQUEST_KEY = "money_request_key"
const val MONEY_DIALOG_RESULT_KEY = "money_result_key"

class NewsDetailFragment : Fragment(), HasCustomBottomBar {

    private var _binding: FragmentNewsDetailBinding? = null
    private val binding
        get() = _binding ?: throw BindingException(
            "FragmentNewsDetailBinding is null"
        )
    private val args: NewsDetailFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()
        setUIFromArgs()
        setDialogListener()
    }

    private fun setDialogListener() {
        setFragmentResultListener(MONEY_DIALOG_REQUEST_KEY) { _, bundle ->
            val sumValue = bundle.getInt(MONEY_DIALOG_RESULT_KEY)
            val workManager = WorkManager.getInstance(requireActivity().application)
            workManager.enqueueUniqueWork(
                DonateWorker.WORK_NAME,
                ExistingWorkPolicy.APPEND,
                DonateWorker.makeRequest(
                    title = args.event.label,
                    sum = sumValue,
                    id = args.event.id,
                    infoText = requireContext().getString(
                        R.string.donate_notification_info_text,
                        sumValue
                    )
                )
            )
        }
    }

    private fun setUIFromArgs() {
        val item = args.event
        with(binding) {
            toolbarLabelDetailedNewsTv.text = item.label
            newsTitleTv.text = item.label
            newsTextFirstPartTv.text = item.shortDesc
            timerTv.text = item.date
            fondNameTv.text = item.company
            addressTv.text = item.address
            phonesTv.text = item.phone
            photoNews1.setImageResource(item.newsImages[0])
            photoNews2.setImageResource(item.newsImages[1])
            photoNews3.setImageResource(item.newsImages[2])
            newsTextFirstPartTv.text = item.shortDesc
            newsTextSecondPartTv.text = item.fullDesc
        }
    }

    private fun setBackButton() {
        binding.imageButtonBack.setOnClickListener {
            val direction =
                NewsDetailFragmentDirections.actionNewsDetailFragmentToNewsFragment()
            findNavController().navigate(direction)
        }
    }

    private fun setOnClickListeners() {
        setBackButton()
        setOnHelpButton()
    }

    private fun setOnHelpButton() {
        binding.helpMoneyBtn.setOnClickListener {
            showMoneyHelpDialog()
        }
    }

    private fun showMoneyHelpDialog() {
        MoneyHelpDialog().show(parentFragmentManager, MONEY_DIALOG_REQUEST_KEY)
    }
}
