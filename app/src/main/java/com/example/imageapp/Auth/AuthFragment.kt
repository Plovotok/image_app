package com.example.imageapp.Auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.*
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.imageapp.MainActivityViewModel
import com.example.imageapp.R
import com.example.imageapp.Reg.RegFragment
import com.example.imageapp.image.ImageFragment
import com.example.imageapp.room.User
import com.example.imageapp.room.UserViewModel
import kotlinx.coroutines.*

class AuthFragment : Fragment() {

    companion object {
        fun newInstance() = AuthFragment()
    }

    private lateinit var viewModel: UserViewModel
    private var mainActivityViewModel = MainActivityViewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_auth, container, false)
        val regBtn = view.findViewById<Button>(R.id.reg_btn)
        val contBtn = view.findViewById<Button>(R.id.cont_btn)
        val progressBar = view.findViewById<ProgressBar>(R.id.authProgressBar)
        progressBar.visibility = View.GONE

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        regBtn.setOnClickListener {
//            if (getDataFromDatabase()) {
//
//            }
//            setFragment(RegFragment())
            findNavController().navigate(R.id.action_authFragment_to_regFragment)
        }

        contBtn.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                progressBar.visibility = View.VISIBLE
                delay(1500)
                getDataFromDatabase()
                if (getDataFromDatabase()) {
                    Toast.makeText(requireContext(), "Success!", Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.action_authFragment_to_imageFragment)
                } else {
                    Toast.makeText(requireContext(), "   User not Found \n Check Phone number and password", Toast.LENGTH_LONG).show()
                }
//                if (getDataFromDatabase()) {
//                    CoroutineScope(Dispatchers.Main).launch {
//                        Toast.makeText(requireContext(), "Success!", Toast.LENGTH_LONG).show()
////                        setFragment(ImageFragment())
//                        findNavController().navigate(R.id.action_authFragment_to_imageFragment)
//                    }
//
//
//                } else {
//                    CoroutineScope(Dispatchers.Main).launch {
//                        Toast.makeText(requireContext(), "   User not Found \n Check Phone number and password", Toast.LENGTH_LONG).show()
//                    }
//
//                }
                progressBar.visibility = View.GONE
            }
        }

        return view
    }

    private suspend fun getDataFromDatabase() : Boolean {
        val etPhone = view?.findViewById<EditText>(R.id.etPhone)
        val phone = etPhone?.text.toString()
        val etPass = view?.findViewById<EditText>(R.id.etPass)
        val pass = etPass?.text.toString()
        val user = User(0, phone, pass)

        if (viewModel.getUser(user.phone, user.password) != null) {
            return true
        }
        return false

    }

    private fun setFragment(fragment: Fragment) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction?.replace(R.id.nav_host_fragment, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }




//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
//        // TODO: Use the ViewModel
//    }



}