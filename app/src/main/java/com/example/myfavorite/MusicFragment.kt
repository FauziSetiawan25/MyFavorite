package com.example.myfavorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MusicFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var musicAdapter: MusicAdapter
    private lateinit var musicDatabase: MusicDatabase
    private val musicList = mutableListOf<Music>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_music, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        musicDatabase = MusicDatabase.getDatabase(requireContext())

        // data album Marshmello
        musicList.add(Music(1, "Keep It Mello (feat. Omar LinX) - Joytime", false))
        musicList.add(Music(2, "Know Me - Joytime", false))
        musicList.add(Music(3, "Summer - Joytime", false))
        musicList.add(Music(4, "Tell Me - Joytime II", false))
        musicList.add(Music(5, "Rooftops - Joytime II", false))
        musicList.add(Music(6, "Check This Out - Joytime II", false))
        musicList.add(Music(7, "Rescue Me (feat. A Day to Remember) - Joytime III", false))
        musicList.add(Music(8, "Here We Go Again (feat. Carnage) - Joytime III", false))
        musicList.add(Music(9, "Run It Up - Joytime III", false))

        // Memuat status favorit dari database
        CoroutineScope(Dispatchers.IO).launch {
            val favoriteMusicIds = musicDatabase.musicDao().getAllFavorites().map { it.id }

            // Perbarui status favorit di musicList
            withContext(Dispatchers.Main) {
                musicList.forEach { music ->
                    music.isFavorite = favoriteMusicIds.contains(music.id)
                }
                musicAdapter.notifyDataSetChanged()
            }
        }

        musicAdapter = MusicAdapter(musicList, { music ->
            CoroutineScope(Dispatchers.IO).launch {
                if (music.isFavorite) {
                    musicDatabase.musicDao().insert(music) // Tambah ke favorit
                } else {
                    musicDatabase.musicDao().delete(music) // Hapus dari favorit
                }
            }
        }, showFavoriteIcon = true) // Tampilkan ikon favorit di MusicFragment

        recyclerView.adapter = musicAdapter

        return view
    }
}