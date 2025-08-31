package com.example.brewbuddy.presentation.orders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.brewbuddy.data.orderdb.OrderEntity
import com.example.brewbuddy.databinding.FragmentPaymentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentFragment : Fragment() {

    private val viewModel: PaymentViewModel by viewModels()
    private lateinit var binding: FragmentPaymentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPaymentBinding.bind(view)

        val args = arguments?.let { PaymentFragmentArgs.fromBundle(it) }

        binding.orderItemQuantity.text = "${args?.quantity}x"
        binding.orderItemName.text = args?.title
        binding.orderItemPrice.text = "Rp ${args?.price}"
        binding.totalValue.text = "Rp ${args?.price}"

        binding.placeOrderButton.setOnClickListener {
            args?.let {
                val order = OrderEntity(
                    quantity = it.quantity,
                    name = it.title ?: "",
                    imageUrl = it.imageUrl,
                    date = System.currentTimeMillis()
                )

                viewModel.addOrder(order)
            }
        }
        binding.editButton.setOnClickListener {
            val dialog = EditAddressDialogFragment()
            dialog.show(parentFragmentManager, "edit_address")
        }
    }
}