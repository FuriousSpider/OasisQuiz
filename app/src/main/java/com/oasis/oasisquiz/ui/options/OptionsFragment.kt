package com.oasis.oasisquiz.ui.options

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.oasis.oasisquiz.R
import com.oasis.oasisquiz.databinding.FragmentOptionsBinding

class OptionsFragment : Fragment() {
    private lateinit var binding: FragmentOptionsBinding
    private lateinit var viewModel: OptionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_options, container, false)
        viewModel = ViewModelProvider(this)[OptionsViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}