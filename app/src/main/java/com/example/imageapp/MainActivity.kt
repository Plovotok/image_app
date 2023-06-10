package com.example.imageapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.imageapp.Auth.AuthFragment
import com.example.imageapp.image.ImageFragment
import com.example.imageapp.ui.main.MainFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        viewModel = MainActivityViewModel()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, AuthFragment.newInstance())
                .commitNow()

        }
//        setupActionBarWithNavController(findNavController(R.id.nav_host_fragment))
//        CoroutineScope(Dispatchers.Main).launch {
//            delay(2000L)
////                setFragment(AuthFragment())
////            findNavController(R.id.nav_host_fragment).navigate(R.id.action_mainFragment_to_authFragment)
////            viewModel.currentFragment.value = AuthFragment()
//        }




//        viewModel.currentFragment.value = AuthFragment()
    }

    override fun onResume() {
        super.onResume()
        findNavController(R.id.nav_host_fragment)
    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment)
//        return navController.navigateUp() || super.onSupportNavigateUp()
//    }



    private fun setFragment(fragment: Fragment) {

        val fm: FragmentManager = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.replace(R.id.nav_host_fragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


}