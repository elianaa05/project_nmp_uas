package com.eliana.dailymemedigest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_meme.view.*

class MemesAdapter (val memes:ArrayList<Memes>):RecyclerView.Adapter<MemesAdapter.MemesViewHolder>(){
    class MemesViewHolder(val v : View) : RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.card_meme, parent, false)
        return MemesViewHolder(v)
    }

    override fun onBindViewHolder(holder: MemesViewHolder, position: Int) {
        val posisi = position
        with(holder.v){
            txtTextAtas.text = memes[position].text_atas
            txtTextBawah.text = memes[position].text_bawah
            val url = memes[position].url
            Picasso.get().load(url).into(holder.v.imageMemeCard)
        }
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}