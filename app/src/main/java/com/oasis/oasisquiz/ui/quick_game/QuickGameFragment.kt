package com.oasis.oasisquiz.ui.quick_game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.oasis.oasisquiz.R
import com.oasis.oasisquiz.databinding.FragmentQuickGameBinding

class QuickGameFragment : Fragment() {
    private lateinit var binding: FragmentQuickGameBinding
    private lateinit var viewModel: QuickGameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quick_game, container, false)
        viewModel = ViewModelProvider(this)[QuickGameViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}