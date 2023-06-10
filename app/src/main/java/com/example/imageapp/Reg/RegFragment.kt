package com.example.imageapp.Reg

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.imageapp.Auth.AuthFragment
import com.example.imageapp.MainActivityViewModel
import com.example.imageapp.R
import com.example.imageapp.room.User
import com.example.imageapp.room.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RegFragment : Fragment() {

    companion object {
        fun newInstance() = RegFragment()
    }

    private lateinit var viewModel: UserViewModel
    private var mainActivityViewModel = MainActivityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_reg, container, false)
        val btn = view.findViewById<Button>(R.id.reg_btn_first)
        val phone = view.findViewById<EditText>(R.id.etPhone).text
        val pass = view.findViewById<EditText>(R.id.etPass).text
        val progressBar = view.findViewById<ProgressBar>(R.id.regProgressBar)
        progressBar.visibility = View.GONE

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        btn.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                progressBar.visibility = View.VISIBLE
                delay(1500)
                val isValid = insertDataToDatabase()
                progressBar.visibility = View.GONE
                    findNavController().navigate(R.id.action_regFragment_to_authFragment)
            }


//            setFragment(AuthFragment())


        }

        return view
    }

    private fun insertDataToDatabase() : Boolean{
        val etPhone = view?.findViewById<EditText>(R.id.etPhone)
        val phone = etPhone?.text.toString()
        val etPass = view?.findViewById<EditText>(R.id.etPass)
        val pass = etPass?.text.toString()


            val user = User(0, phone, pass)
            CoroutineScope(Dispatchers.Main).launch {
                    viewModel.addUser(user)
                    Toast.makeText(requireContext(), "Success!", Toast.LENGTH_LONG).show()
                }


//        if (isDataValid(phone, pass)) {
//            val user = User(0, phone, pass)
//            CoroutineScope(Dispatchers.Main).launch {
//                if (viewModel.getUserByPhone(phone) == null) {
//                    viewModel.addUser(user)
//                    Toast.makeText(requireContext(), "Success!", Toast.LENGTH_LONG).show()
//                } else {
//                    Toast.makeText(
//                        requireContext(),
//                        "This phone number is already registered",
//                        Toast.LENGTH_LONG).show()
//                }
//            }
//
//        } else {
//            Toast.makeText(requireContext(), "Check your input fields!", Toast.LENGTH_LONG).show()
//        }

        return isDataValid(phone, pass)
    }

    private fun isDataValid(phone : String, pass : String) : Boolean{
        return !(TextUtils.isEmpty(pass)) && phone.length == 11
    }


    private fun setFragment(fragment: Fragment) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction?.replace(R.id.nav_host_fragment, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }




}