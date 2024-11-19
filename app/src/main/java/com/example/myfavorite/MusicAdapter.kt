package com.example.myfavorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MusicAdapter(
    private val musicList: List<Music>,
    private val onFavoriteClicked: (Music) -> Unit,
    private val showFavoriteIcon: Boolean
) : RecyclerView.Adapter<MusicAdapter.MusicViewHolder>() {

    inner class MusicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val favoriteButton: ImageButton = itemView.findViewById(R.id.favoriteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_music, parent, false)
        return MusicViewHolder(view)
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        val music = musicList[position]
        holder.titleTextView.text = music.title

        // Tampilkan atau sembunyikan ikon favorit berdasarkan parameter showFavoriteIcon
        if (showFavoriteIcon) {
            holder.favoriteButton.visibility = View.VISIBLE
            holder.favoriteButton.setImageResource(
                if (music.isFavorite) R.drawable.ic_favorite else R.drawable.ic_unfavorite
            )

            holder.favoriteButton.setOnClickListener {
                music.isFavorite = !music.isFavorite
                onFavoriteClicked(music)
                notifyItemChanged(position)
            }
        } else {
            // Sembunyikan ikon favorit jika di FavoritesFragment
            holder.favoriteButton.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int = musicList.size
}
