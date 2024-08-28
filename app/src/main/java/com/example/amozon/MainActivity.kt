package com.example.amozon

import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.amozon.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var fragmentManager: FragmentManager
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.nav_open,
            R.string.nav_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navitionDrawer.setNavigationItemSelectedListener(this)

        // Remove the background and set up bottom navigation listener
        binding.bottonNavigation.background = null
        binding.bottonNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.botton_home -> openFragment(HomeFragment())
                R.id.botton_profile -> openFragment(ProfileFragment())
                R.id.botton_cart -> openFragment(CartFragment())
                R.id.botton_menu -> openFragment(MenuFragment())
            }
            true
        }

        // Initialize the FragmentManager and open the default fragment
        fragmentManager = supportFragmentManager
        openFragment(HomeFragment())

        // Handle Floating Action Button click
        binding.feb.setOnClickListener {
            Toast.makeText(this, "Categories", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_prime -> openFragment(PrimeFragment())
            R.id.nav_fashion -> openFragment(FashionFragment()) // Correct the fragment here
            R.id.nav_electronics -> openFragment(ElectronicsFragment()) // Fixed typo
            R.id.nav_fresh -> Toast.makeText(this, "Fresh", Toast.LENGTH_SHORT).show()
            R.id.nav_beauty -> Toast.makeText(this, "Beauty", Toast.LENGTH_SHORT).show()
            R.id.nav_furniture -> Toast.makeText(this, "Furniture", Toast.LENGTH_SHORT).show()
        }

        binding.drawerLayout.closeDrawers(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawers(GravityCompat.START)
        } else
        {
            super.onBackPressed() // Fixed method name
        }
    }

    private fun openFragment(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment) // Fixed ID name
        fragmentTransaction.commit()
    }
}
