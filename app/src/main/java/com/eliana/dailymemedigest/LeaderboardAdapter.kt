package com.eliana.dailymemedigest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_leaderboard.view.*

class LeaderboardAdapter (val lc:ArrayList<LeaderboardClass>):RecyclerView.Adapter<LeaderboardAdapter.LeaderboardViewHolder>(){
    class LeaderboardViewHolder(val v : View) : RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderboardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.card_leaderboard, parent, false)
        return LeaderboardViewHolder(v)
    }

    override fun onBindViewHolder(holder: LeaderboardViewHolder, position: Int) {
        val posisi = position
        with(holder.v){
            val url = lc[position].link
            Picasso.get().load(url).into(holder.v.imageUserLeaderboard)
            txtUsernameLeaderboard.text = lc[position].nama
            txtJumlahLikeLeaderboard.text = lc[position].jumlah_like
        }
    }

    override fun getItemCount(): Int {
        return lc.size
    }
}