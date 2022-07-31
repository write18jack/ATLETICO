package com.example.atletico

import android.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.AdapterView
import androidx.navigation.findNavController
import com.example.atletico.databinding.ActivityMainBinding
import com.example.atletico.ui.BottomNavManager
import kotlinx.android.synthetic.main.custom_top_bar.*

class MainActivity : AppCompatActivity() {

    private var bottomNavManager: BottomNavManager? = null
    private val binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigationManager()
    }

    private fun setupNavigationManager(){
        bottomNavManager?.setupNavController() ?: kotlin.run {
            bottomNavManager = BottomNavManager(
                fragmentManager = supportFragmentManager,
                containerId = R.id.nav_host_container,
                bottomNavigationView = findViewById(R.id.nav_view)
            )
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        bottomNavManager?.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        bottomNavManager?.onRestoreInstanceState(savedInstanceState)
        setupNavigationManager()
    }

    override fun onBackPressed() {
        if (bottomNavManager?.onBackPressed() == false) super.onBackPressed()
    }
}