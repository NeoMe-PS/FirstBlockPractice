package com.psbn.news.presentation.ui

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import com.psbn.news.R
import com.psbn.firstblockpractice.core.R as coreR


class MoneyHelpDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val view = it.layoutInflater.inflate(R.layout.help_money_dialog, null)

            val cancelBtn = view.findViewById<TextView>(R.id.cancel_button)
            val acceptBtn = view.findViewById<TextView>(R.id.accept_button)
            val sum100 = view.findViewById<TextView>(R.id.sum_100_rb)
            val sum500 = view.findViewById<TextView>(R.id.sum_500_rb)
            val sum1000 = view.findViewById<TextView>(R.id.sum_1000_rb)
            val sum2000 = view.findViewById<TextView>(R.id.sum_2000_rb)
            val radioGroup = view.findViewById<RadioGroup>(R.id.sum_radio_group)
            val valueEditText = view.findViewById<EditText>(R.id.value_edittext)

            radioGroup.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    sum100.id -> valueEditText.setText(SUM_100)
                    sum500.id -> valueEditText.setText(SUM_500)
                    sum1000.id -> valueEditText.setText(SUM_1000)
                    sum2000.id -> valueEditText.setText(SUM_2000)
                }
            }

            valueEditText.addTextChangedListener { value ->
                if (value.isNullOrEmpty()) {
                    deactivateAcceptButton(acceptBtn, it)
                    return@addTextChangedListener
                }
                val intValue = value.toString().toInt()
                if (intValue < MIN_SUM || intValue > MAX_SUM) {
                    deactivateAcceptButton(acceptBtn, it)
                    return@addTextChangedListener
                }
                activateAcceptButton(acceptBtn, it)
            }
            cancelBtn.setOnClickListener { dismiss() }
            acceptBtn.setOnClickListener {
                val resultValue = valueEditText.text.toString().toInt()
                parentFragmentManager.setFragmentResult(
                    MONEY_DIALOG_REQUEST_KEY,
                    bundleOf(MONEY_DIALOG_RESULT_KEY to resultValue)
                )
                dismiss()
            }

            builder.setView(view)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun activateAcceptButton(acceptBtn: TextView, context: Context) {
        acceptBtn.setTextColor(ContextCompat.getColor(context, coreR.color.laef))
        acceptBtn.isClickable = true
    }

    private fun deactivateAcceptButton(acceptBtn: TextView, context: Context) {
        acceptBtn.setTextColor(ContextCompat.getColor(context, coreR.color.light_grey_two))
        acceptBtn.isClickable = false
    }

    companion object {
        private const val SUM_100 = "100"
        private const val SUM_500 = "500"
        private const val SUM_1000 = "1000"
        private const val SUM_2000 = "2000"
        private const val MIN_SUM = 1
        private const val MAX_SUM = 9_999_999
    }

}