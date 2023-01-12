package com.eliana.dailymemedigest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_creation.view.*

class MyCreationAdapter (val CM:ArrayList<Memes>):RecyclerView.Adapter<MyCreationAdapter.MyCreationViewHolder>(){
    class MyCreationViewHolder(val v: View) : RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCreationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.card_creation, parent, false)
        return MyCreationViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyCreationViewHolder, position: Int) {
        val shared = holder.v.getContext().getSharedPreferences("com.eliana.dailymemedigest", Context.MODE_PRIVATE)
        val posisi = position
        with(holder.v){
            var url = CM[position].url
            Picasso.get().load(url).into(holder.v.imageCreation)
            txtAtasCreation.text = CM[position].text_atas
            txtBawahCreation.text = CM[position].text_bawah
            txtLike.text = CM[posisi].jumlah_like.toString()
        }
        
        
            /*fun getParams(): MutableMap<String, String>? {
                val params = HashMap<String, String>()
                params["id_pembuat"] = shared.getInt("IDUSER", 0).toString()
                return  params
                
            }*/
    }



    override fun getItemCount(): Int {
        return CM.size
    }
}