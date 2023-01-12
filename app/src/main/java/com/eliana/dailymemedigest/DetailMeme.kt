package com.eliana.dailymemedigest

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_meme.*
import kotlinx.android.synthetic.main.card_meme.*
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class DetailMeme : AppCompatActivity() {
    val kk: ArrayList<Komentar> = ArrayList()
    fun updateList(){
        val lm: LinearLayoutManager = LinearLayoutManager(this)
        val rv = findViewById<RecyclerView>(R.id.recyclerViewDetail)
        rv?.layoutManager = lm
        rv?.setHasFixedSize(true)
        rv.adapter = KomentarAdapter(kk)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        var id = ""
        //val mm:ArrayList<Memes> = ArrayList()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_meme)

        // Ambil id meme
        id = intent.getStringExtra("id").toString()

        var shared: SharedPreferences =
            getSharedPreferences("com.eliana.dailymemedigest", Context.MODE_PRIVATE)
        var editor: SharedPreferences.Editor = shared.edit()

        val q = Volley.newRequestQueue(this)
        val url = "https://ubaya.fun/flutter/160719017/nmp/detailmeme2.php"

        var stringRequest = object: StringRequest(
            Request.Method.POST,
            url,
            Response.Listener {
                Log.d("apiresult", it)
                val obj = JSONArray(it)
                if(obj.getJSONObject(0).getString("result")=="success") {
                    val data = obj.getJSONObject(0).getJSONArray("data").getJSONObject(0).getJSONArray("memes")
                    for (i in 0 until data.length()) {
                        val comen = data.getJSONObject(i)
                        val c = Komentar(
                            comen.getInt("id"),
                            comen.getInt("id_meme"),
                            comen.getInt("id_pengguna"),
                            comen.getString("isi_komentar"),
                            comen.getString("tanggal_komentar"),
                        )
                        kk.add(c)
                        updateList()
                    }
                }
            },
            Response.ErrorListener {
                Log.wtf("apiresult", it.message.toString())
            }
        ){
            override fun getParams(): MutableMap<String, String>? {
                val map = HashMap<String, String>()
                map.set("id", id.toString())
                return map
            }
        }
        q.add(stringRequest)



        var isi_komentar = txtKirimKomentarDetail.text
        val sdf = SimpleDateFormat("yyyy/MM/dd")
        var tanggal = sdf.format(Date())

        btnKomentarCard.setOnClickListener {
            val q2 = Volley.newRequestQueue(it.context)
            val url2 = "https://ubaya.fun/flutter/160719017/nmp/addcomment.php"
            val stringRequest2 =object: StringRequest(
                Request.Method.POST, url,
                {
                    Log.d("cekparams", it)
                },
                {
                    Log.e("cekparams", it.message.toString())
                }
            ){
                override fun getParams(): MutableMap<String, String>?{
                    var iduser = shared?.getInt("IDUSER",0)
                    //var idmeme = shared?.getInt("IDMEME",0)
                    val map2 = HashMap<String, String>()
                    //$id_meme,$id_pengguna,$isi_komentar,$tanggal_komentar
                    map2.set("id_meme", id.toString())
                    map2.set("id_pengguna", iduser.toString())
                    map2.set("isi_komentar", isi_komentar.toString())
                    map2.set("tanggal_komentar", tanggal.toString())
                    return map2
                }
            }
            q2.add(stringRequest2)
            updateList()
        }
    }

}

