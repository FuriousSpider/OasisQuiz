package com.oasis.oasisquiz.ui.main_menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.oasis.oasisquiz.R
import com.oasis.oasisquiz.databinding.FragmentMainMenuBinding
import com.oasis.oasisquiz.model.Questions

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

        testFirebaseAndRemoveLater()
    }

    private fun testFirebaseAndRemoveLater() {
        val db = Firebase.firestore

        db.collection("questions").document("questions").get().addOnSuccessListener { document ->
            val questions: Questions? = document.toObject(Questions::class.java)
            questions?.let {
                Toast.makeText(this@MainMenuFragment.context, "Success: " + it.questions[0].question, Toast.LENGTH_LONG).show()
            }
            
        }.addOnFailureListener { error ->
            Toast.makeText(this@MainMenuFragment.context, error.message, Toast.LENGTH_LONG).show()
        }
    }
}