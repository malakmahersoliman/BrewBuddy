package com.example.brewbuddy.presentation.orders

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.brewbuddy.databinding.FragmentPaymentBinding
import com.example.brewbuddy.domain.model.Order
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date

@AndroidEntryPoint
class PaymentFragment : Fragment() {

    private val viewModel: PaymentViewModel by viewModels()

    private lateinit var binding: FragmentPaymentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPaymentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments?.let { PaymentFragmentArgs.fromBundle(it) }

        binding.orderItemQuantity.text = "${args?.quantity}x"
        binding.orderItemName.text = args?.title
        binding.orderItemPrice.text = "${args?.price}"
        binding.totalValue.text = "${args?.price}"

        binding.placeOrderButton.setOnClickListener {
            args?.let {
                val order = Order(
                    id = it.drinkId,
                    quantity = it.quantity,
                    name = it.title ?: "",
                    imageUrl = it.imageUrl,
                    description = null,
                    date = Date(System.currentTimeMillis())
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