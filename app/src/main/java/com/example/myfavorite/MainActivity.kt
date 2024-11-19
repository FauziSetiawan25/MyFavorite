package com.example.myfavorite

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

// MainActivity.kt
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set default fragment saat aplikasi dibuka
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MusicFragment())
            .commit()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Listener untuk item yang dipilih
        bottomNavigationView.setOnItemSelectedListener { item ->
            val selectedFragment: Fragment = when (item.itemId) {
                R.id.nav_music -> MusicFragment()
                R.id.nav_favorites -> FavoritesFragment()
                else -> MusicFragment()
            }

            // Ganti fragment saat item dipilih
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, selectedFragment)
                .commit()

            true
        }

        // Listener untuk item yang dipilih ulang
        bottomNavigationView.setOnItemReselectedListener { item ->
            when (item.itemId) {
                R.id.nav_music -> {
                    // Aksi ketika item "Musik" dipilih ulang (jika ingin ada aksi tertentu)
                    Toast.makeText(this, "Musik sudah dipilih", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_favorites -> {
                    // Aksi ketika item "Favorit" dipilih ulang (jika ingin ada aksi tertentu)
                    Toast.makeText(this, "Favorit sudah dipilih", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
