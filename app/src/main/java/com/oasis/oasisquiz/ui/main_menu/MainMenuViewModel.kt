package com.oasis.oasisquiz.ui.main_menu

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.oasis.oasisquiz.R

class MainMenuViewModel : ViewModel() {
    lateinit var navController: NavController

    fun navigateToQuickGame() {
        navController.navigate(R.id.action_mainMenuFragment_to_quickGameFragment)
    }

    fun navigateToOptions() {
        navController.navigate(R.id.action_mainMenuFragment_to_optionsFragment)
    }
}