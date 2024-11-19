package com.example.myfavorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// FavoritesFragment.kt
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var musicAdapter: MusicAdapter
    private lateinit var musicDatabase: MusicDatabase
    private val favoriteList = mutableListOf<Music>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        musicDatabase = MusicDatabase.getDatabase(requireContext())

        // Mengambil data favorit dari database
        CoroutineScope(Dispatchers.IO).launch {
            val favorites = musicDatabase.musicDao().getAllFavorites()
            withContext(Dispatchers.Main) {
                favoriteList.clear()
                favoriteList.addAll(favorites)
                musicAdapter.notifyDataSetChanged()
            }
        }

        // FavoritesFragment.kt
        musicAdapter = MusicAdapter(favoriteList, { /* no-op */ }, showFavoriteIcon = false) // Sembunyikan ikon favorit di FavoritesFragment
        recyclerView.adapter = musicAdapter

        return view
    }
}
