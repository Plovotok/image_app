package com.example.imageapp.test

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imageapp.R
import com.example.imageapp.databinding.FragmentTestBinding

class TestFragment : Fragment() {

    companion object {
        fun newInstance() = TestFragment()
    }

    lateinit var binding : FragmentTestBinding

    lateinit var viewModel: TestViewModel
    lateinit var adapter: TestAdapter
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_test, container, false)
        viewModel = ViewModelProvider(this).get(TestViewModel::class.java)
        binding = FragmentTestBinding.bind(view)
        init()



        return view
    }

    fun init() {

        recyclerView = binding.rvTest
        adapter = TestAdapter(requireContext())
        recyclerView.adapter = adapter
        adapter.setList(fillList())
    }

    fun fillList() : ArrayList<User> {
        val userList = arrayListOf<User>()
        val user1 = User(1, "Zhenia", "+79252205822", "zhenia@mail.ru")
        userList.add(user1)

        val user2 = User(1, "Pavel", "+79147896534", "pavel@mail.ru")
        userList.add(user2)

        val user3 = User(1, "Ruslan", "+79735413537", "ruslan@mail.ru")
        userList.add(user3)

        val user4 = User(1, "Kostya", "+79086784561", "kostya@mail.ru")
        userList.add(user4)

        val user5 = User(1, "Dima", "+79764567892", "dima@mail.ru")
        userList.add(user5)

        val user6 = User(1, "Natasha", "+79607865641", "natasha@mail.ru")
        userList.add(user6)

        val user7 = User(1, "Fenya", "+79795689001", "fenya@mail.ru")
        userList.add(user7)

        val user8 = User(1, "Danil", "+79695671516", "danil@mail.ru")
        userList.add(user8)

        val user9 = User(1, "Oleg", "+79056735450", "oleg@mail.ru")
        userList.add(user9)

        val user10 = User(1, "Kolya", "+79263653234", "kolya@mail.ru")
        userList.add(user10)



        return userList
    }



}