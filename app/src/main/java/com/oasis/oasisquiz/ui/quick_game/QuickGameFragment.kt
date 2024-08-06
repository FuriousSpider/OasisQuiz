package com.oasis.oasisquiz.ui.quick_game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.oasis.oasisquiz.R
import com.oasis.oasisquiz.databinding.FragmentQuickGameBinding
import com.oasis.oasisquiz.model.Question

class QuickGameFragment : Fragment() {
    private lateinit var binding: FragmentQuickGameBinding
    private lateinit var viewModel: QuickGameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quick_game, container, false)
        viewModel = ViewModelProvider(this)[QuickGameViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val database = Firebase.database.reference
        database.child("questions").get().addOnSuccessListener {
            val questions = it.getValue(object : GenericTypeIndicator<List<Question>>() {})
            Toast.makeText(this@QuickGameFragment.context, questions.toString(), Toast.LENGTH_LONG)
                .show()
        }.addOnFailureListener {
            Toast.makeText(this@QuickGameFragment.context, it.message, Toast.LENGTH_LONG).show()
        }

        database.child("version").get().addOnSuccessListener {
            val version = it.getValue(Int::class.java)
            Toast.makeText(this@QuickGameFragment.context, version.toString(), Toast.LENGTH_LONG)
                .show()
        }.addOnFailureListener {
            Toast.makeText(this@QuickGameFragment.context, it.message, Toast.LENGTH_LONG).show()
        }
    }
}