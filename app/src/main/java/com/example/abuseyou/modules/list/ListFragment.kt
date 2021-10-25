package com.example.abuseyou.modules.list

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.abuseyou.R
import com.example.abuseyou.databinding.FragmentListBinding
import com.example.abuseyou.modules.list.listadapter.MyListAdapter
import com.example.abuseyou.modules.list.listadapter.SpacingItemDecoration
import com.example.abuseyou.modules.list.viewmodel.ListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private val viewModel by viewModels<ListViewModel>()
    private lateinit var adapter: MyListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.list.addItemDecoration(SpacingItemDecoration(dpToPx(8)))


        adapter = MyListAdapter(::del)
        viewModel.insults.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getInsults()

        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.searchInsults(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    fun del(item: Int) {
        basicAlert(item)
    }

    fun basicAlert(item: Int) {

        val builder = AlertDialog.Builder(requireContext())

        with(builder)
        {
            setTitle(getString(R.string.alert_title))
            setMessage(getString(R.string.alert_message))
            setPositiveButton(getString(R.string.alert_yes)) { dialogInterface, i ->
                viewModel.del(item)
            }
            setNegativeButton(getString(R.string.alert_no)) { dialogInterface, i ->
                Log.d("dbbd", "close")
            }
            show()
        }
    }

    fun dpToPx(dp: Int): Int {
        val displayMetrics: DisplayMetrics = this.getResources().getDisplayMetrics()
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
    }

}