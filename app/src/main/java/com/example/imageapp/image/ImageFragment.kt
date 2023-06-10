package com.example.imageapp.image

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.imageapp.R
import com.example.imageapp.image.recyclerview.ImageViewAdapter
import com.example.imageapp.image.retrofit.Data
import com.example.imageapp.test.TestFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ImageFragment : Fragment() {


    lateinit var rvImage : RecyclerView
    lateinit var adapter : ImageViewAdapter

    companion object {
        fun newInstance() = ImageFragment()
    }

    private lateinit var viewModel: ImageViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_image, container, false)
        viewModel = ViewModelProvider(this).get(ImageViewModel::class.java)
        rvImage = view?.findViewById<RecyclerView>(R.id.rv_pics)!!
        adapter = ImageViewAdapter(this.requireActivity())
        rvImage.adapter = adapter

        if (viewModel._itemList.value != null) {
            adapter.itemList = viewModel._itemList.value!!
        }

        val resetBtn = view.findViewById<Button>(R.id.reset_btn)
        val testBtn = view.findViewById<Button>(R.id.test_btn)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

        progressBar.visibility = View.GONE
        viewModel._itemList.observe(viewLifecycleOwner) {
            adapter.itemList = it
        }

        adapter.itemList
        resetBtn.setOnClickListener {
            resetBtn.isEnabled = false
            progressBar.visibility = View.VISIBLE
            CoroutineScope(Dispatchers.Main).launch {
                delay(2000)
                viewModel.getAllData()
                resetBtn.isEnabled = true
                progressBar.visibility = View.GONE
            }

        }

        testBtn.setOnClickListener {
//            setFragment(TestFragment())
            findNavController().navigate(R.id.action_imageFragment_to_testFragment)
        }


        return view
    }


    fun setupRecyclerView() {
        val rvImage = view?.findViewById<RecyclerView>(R.id.rv_pics)
        adapter = ImageViewAdapter(requireContext())
        rvImage?.adapter = adapter
    }

    private fun setFragment(fragment: Fragment) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}