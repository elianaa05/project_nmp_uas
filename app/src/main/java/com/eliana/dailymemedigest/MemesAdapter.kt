package com.eliana.dailymemedigest

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_meme.view.*
import kotlinx.android.synthetic.main.card_creation.view.*
import kotlinx.android.synthetic.main.card_meme.*
import kotlinx.android.synthetic.main.card_meme.view.*
import kotlinx.android.synthetic.main.card_meme.view.btnKomentarCard
import kotlinx.android.synthetic.main.card_meme.view.btnLikeCard
import kotlinx.android.synthetic.main.card_meme.view.imageMemeCard
import kotlinx.android.synthetic.main.card_meme.view.txtJumlahLikeCard
import kotlinx.android.synthetic.main.card_meme.view.txtTextAtasDetail
import kotlinx.android.synthetic.main.card_meme.view.txtTextBawahDetail

class MemesAdapter (val meme:ArrayList<Memes>):RecyclerView.Adapter<MemesAdapter.MemesViewHolder>(){
    class MemesViewHolder(val v : View) : RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.card_meme, parent, false)
        return MemesViewHolder(v)
    }

    override fun onBindViewHolder(holder: MemesViewHolder, position: Int) {
        val posisi = position
        with(holder.v){
            txtTextAtasDetail.text = meme[position].text_atas
            txtTextBawahDetail.text = meme[position].text_bawah
            txtJumlahLikeCard.text = meme[posisi].jumlah_like.toString()
            val url = meme[position].url
            Picasso.get().load(url).into(holder.v.imageMemeCard)

            cardViewMeme.setOnClickListener {
                val intent = Intent(context, DetailMeme::class.java)
                intent.putExtra("id", meme[position].id.toString())
                context.startActivity(intent)
            }
        }

        holder.v.btnLikeCard.setOnClickListener {
            val q = Volley.newRequestQueue(it.context)
            val url = "https://ubaya.fun/flutter/160719017/nmp/updatelike.php"
            val stringRequest = object : StringRequest(
                Request.Method.POST,
                url,
                {
                    Log.d("cekparams", it)
                    meme[posisi].id.toString()
                    meme[posisi].jumlah_like++
                    var newlikes = meme[posisi].jumlah_like
                    holder.v.txtJumlahLikeCard.text = newlikes.toString()
                },
                {
                    Log.e("cekparams", it.message.toString())
                }
            ){
                override fun getParams(): MutableMap<String, String>? {
                    val map = HashMap<String, String>()
                    map.set("id", meme[posisi].id.toString())
                    return map
                }
            }
            q.add(stringRequest)
        }
    }

    override fun getItemCount(): Int {
        return  meme.size
    }

}