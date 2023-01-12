package com.eliana.dailymemedigest

import android.content.Context
import android.content.ContextParams
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
    val ak: ArrayList<AddKomentar> = ArrayList()


    fun updateList() {
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

        var shared: SharedPreferences =
            getSharedPreferences("com.eliana.dailymemedigest", Context.MODE_PRIVATE)
        var editor: SharedPreferences.Editor = shared.edit()

        id = intent.getStringExtra("id").toString()
        var isi_komentar = txtKirimkomentarDetail.text
        val sdf = SimpleDateFormat("yyyy/MM/dd")
        var tanggal = sdf.format(Date())

        btnSendComment.setOnClickListener{
            val q = Volley.newRequestQueue(this)
            val url = "https://ubaya.fun/flutter/160719017/nmp/addcomment.php"

            var stringRequest = object : StringRequest(
            Request.Method.POST,
            url,
            Response.Listener {
                Log.d("apiresult3", it)
                val obj = JSONObject(it)
                if (obj.getString("result") == "success") {
                    val data = obj.getJSONArray("data")
                    for (i in 0 until data.length()) {
                        var komen = data.getJSONObject(i)
                        val comment = AddKomentar(
                            komen.getInt("id_meme"),
                            komen.getInt("id_pengguna"),
                            komen.getString("isi_komentar"),
                            komen.getString("tanggal_komentar"),
                        )
                        ak.add(comment)
                        updateList()
                    }
                }
            },
            Response.ErrorListener {
                Log.wtf("apiresult", it.message.toString())
            }
            ) {
            override fun getParams(): MutableMap<String, String>? {
                var iduser = shared.getInt("IDUSER",0)
                val map = HashMap<String, String>()
                map.set("id_meme", id.toString())
                map.set("id_pengguna", iduser.toString())
                map.set("isi_komentar", isi_komentar.toString())
                map.set("tanggal_komentar", tanggal.toString())
                return map
            }
        }
            q.add(stringRequest)
        }

        //tampil card
        val q = Volley.newRequestQueue(this)
        val url = "https://ubaya.fun/flutter/160719017/nmp/getmeme.php"

        var stringRequest = object : StringRequest(
            Request.Method.POST,
            url,
            Response.Listener {
                Log.d("apiresult3", it)
                val obj = JSONObject(it)
                if (obj.getString("result") == "OK") {
                    val data = obj.getJSONArray("data")
                    //Log.d("halolalalla", data.toString())
                    for (i in 0 until data.length()) {
                        val dm = data.getJSONObject(i)
                        val mm = Memes(
                            dm.getInt("id"),
                            dm.getString("url"),
                            dm.getString("text_atas"),
                            dm.getString("text_bawah"),
                            dm.getString("tanggal"),
                            dm.getInt("id_pembuat"),
                            dm.getInt("jumlah_like")
                        )
                        //Log.d("aaaaa", dm.toString())
                        Picasso.get().load(mm.url).into(imageViewMeme)
                        txtTextAtas.setText(mm.text_atas).toString()
                        txtTextBawah.setText(mm.text_bawah).toString()
                        txtTotalLikeDetail.setText(mm.jumlah_like.toString())
                        //Log.d("detailhalo", mm.toString())
                    }
                }
            },
            Response.ErrorListener {
                Log.wtf("apiresult", it.message.toString())
            }
        ) {
            override fun getParams(): MutableMap<String, String>? {
                val map = HashMap<String, String>()
                map.set("id", id.toString())
                return map
            }
        }
        q.add(stringRequest)
        listComment()
    }

    //tampil komen
    fun listComment() {
        var id = intent.getStringExtra("id").toString()
        val q2 = Volley.newRequestQueue(this)
        val url = "https://ubaya.fun/flutter/160719017/nmp/get_comment.php"

        var stringRequest2 = object : StringRequest(
            Request.Method.POST,
            url,
            Response.Listener {
                //Log.d("apiresult2", it)
                val obj = JSONObject(it)
                if (obj.getString("result") == "success") {
                    val data = obj.getJSONArray("data")
                    //Log.d("halo", data.toString())
                    for (i in 0 until data.length()) {
                        var komen = data.getJSONObject(i)
                        val comment = Komentar(
                            komen.getInt("id_meme"),
                            komen.getInt("id_pengguna"),
                            komen.getString("username"),
                            komen.getString("isi_komentar"),
                            komen.getString("tanggal_komentar"),
                        )
                        kk.add(comment)
                        updateList()
                    }
                }
            },
            Response.ErrorListener {
                Log.wtf("apiresult2", it.message.toString())
            }
        ){
            override fun getParams(): MutableMap<String, String>? {
                val map = HashMap<String, String>()
                map.set("id_meme", id.toString())
                return map
            }

        }
        q2.add(stringRequest2)
    }





//        var isi_komentar = txtKirimKomentarDetail.text
//        val sdf = SimpleDateFormat("yyyy/MM/dd")
//        var tanggal = sdf.format(Date())

//        btnKomentarCard.setOnClickListener {
//            val q2 = Volley.newRequestQueue(it.context)
//            val url2 = "https://ubaya.fun/flutter/160719017/nmp/addcomment.php"
//            val stringRequest2 =object: StringRequest(
//                Request.Method.POST, url,
//                {
//                    Log.d("cekparams", it)
//                },
//                {
//                    Log.e("cekparams", it.message.toString())
//                }
//            ){
//                override fun getParams(): MutableMap<String, String>?{
//                    var iduser = shared?.getInt("IDUSER",0)
//                    //var idmeme = shared?.getInt("IDMEME",0)
//                    val map2 = HashMap<String, String>()
//                    //$id_meme,$id_pengguna,$isi_komentar,$tanggal_komentar
//                    map2.set("id_meme", id.toString())
//                    map2.set("id_pengguna", iduser.toString())
//                    map2.set("isi_komentar", isi_komentar.toString())
//                    map2.set("tanggal_komentar", tanggal.toString())
//                    return map2
//                }
//            }
//            q2.add(stringRequest2)
//            updateList()
//        }
    }

