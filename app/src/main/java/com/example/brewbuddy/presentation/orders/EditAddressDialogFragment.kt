package com.example.brewbuddy.presentation.orders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.example.brewbuddy.R
import com.example.brewbuddy.databinding.FragmentAddressBinding
import com.example.brewbuddy.databinding.FragmentPaymentBinding

class EditAddressDialogFragment : DialogFragment() {
    private lateinit var binding: FragmentAddressBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val saveButton = view.findViewById<Button>(R.id.saveBtn)
        saveButton.setOnClickListener {
            val newAddress = binding.adressEdit.text.toString()

            parentFragmentManager.setFragmentResult(
                "edit_address_request",
                bundleOf("new_address" to newAddress)
            )

            dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }
        }
