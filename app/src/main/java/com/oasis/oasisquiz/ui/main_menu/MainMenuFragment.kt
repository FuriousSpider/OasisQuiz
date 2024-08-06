package com.oasis.oasisquiz.ui.main_menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.oasis.oasisquiz.R
import com.oasis.oasisquiz.databinding.FragmentMainMenuBinding

class MainMenuFragment : Fragment() {

    private lateinit var binding: FragmentMainMenuBinding
    private lateinit var viewModel: MainMenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_menu, container, false)
        viewModel = ViewModelProvider(this)[MainMenuViewModel::class.java]
        viewModel.navController = findNavController()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainMenuQuickGameButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainMenuFragment_to_quickGameFragment)
        }

        binding.mainMenuOptionsButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainMenuFragment_to_optionsFragment)
        }
    }
}