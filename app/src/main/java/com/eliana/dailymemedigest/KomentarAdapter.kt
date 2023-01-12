package com.eliana.dailymemedigest

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.internal.ContextUtils.getActivity
import kotlinx.android.synthetic.main.activity_detail_meme.view.*
import kotlinx.android.synthetic.main.card_komentar.view.*
import kotlinx.android.synthetic.main.card_meme.view.*
import org.json.JSONObject
import java.security.AccessController.getContext
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class KomentarAdapter (val komentars:ArrayList<Komentar>): RecyclerView.Adapter<KomentarAdapter.KomentarViewHolder>() {
    class KomentarViewHolder(val v : View) : RecyclerView.ViewHolder(v)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KomentarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.card_komentar, parent, false)
        return KomentarViewHolder(v)
    }

    override fun onBindViewHolder(holder: KomentarViewHolder, position: Int) {
        //var shared: SharedPreferences = getSharedPreferences("com.eliana.dailymemedigest", Context.MODE_PRIVATE)
        var shared = holder.v.getContext().getSharedPreferences("com.eliana.dailymemedigest", Context.MODE_PRIVATE)

        val posisi = position
        with(holder.v){
            txtUsernameKomentarDetail.text = komentars[position].id_pengguna.toString()
            txtTanggalKomentarDetail.text = komentars[position].tanggal_komentar
            txtIsiKomentarDetail.text = komentars[posisi].isi_komentar

            val mm:ArrayList<Memes> = ArrayList()
            var isi_komentar = txtKirimKomentarDetail.text
            val sdf = SimpleDateFormat("yyyy/MM/dd")
            var tanggal = sdf.format(Date())
        }
    }

    override fun getItemCount(): Int {
        return  komentars.size
    }
}