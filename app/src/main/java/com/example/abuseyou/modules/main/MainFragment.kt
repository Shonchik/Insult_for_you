package com.example.abuseyou.modules.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.abuseyou.databinding.FragmentMainBinding
import com.example.abuseyou.modules.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getInsult()

        viewModel.insult.observe(viewLifecycleOwner) {
            binding.textView.text = it.insult
        }

        binding.dislike.setOnClickListener { viewModel.getInsult() }

        binding.like.setOnClickListener {
            val insult = viewModel.insult.value
            if (insult != null) {
                viewModel.putInsult(insult)
            }
            viewModel.getInsult()
        }

    }

}