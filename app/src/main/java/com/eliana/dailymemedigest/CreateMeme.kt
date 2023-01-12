package com.eliana.dailymemedigest

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_create_meme.*

class CreateMeme : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_meme)

        var shared: SharedPreferences = getSharedPreferences("com.eliana.dailymemedigest", Context.MODE_PRIVATE)
        var editor: SharedPreferences.Editor = shared.edit()

        txtLinkAvatarCreateMeme.setOnFocusChangeListener{ view, b ->
            if(!b) {
                Picasso.get().load(txtLinkAvatarCreateMeme.text.toString()).into(imagePreviewCreate2)
            }
        }
        txtTextAtasCM2.setOnFocusChangeListener{ view, b ->
            if(!b) {
                textAtasCreate2.text = txtTextAtasCM2.text
            }
        }
        txtTextBawahCM2.setOnFocusChangeListener{ view, b ->
            if(!b) {
                textBawahCreate2.text = txtTextBawahCM2.text
            }
        }
        btnSubmit2.setOnClickListener {
            var q = Volley.newRequestQueue(it.context)
            val url = "https://ubaya.fun/flutter/160719017/nmp/addmeme.php"

            val stringRequest = object: StringRequest(
                Request.Method.POST,
                url,
                {
                    //Log.d("cekcreate", it)
                    Toast.makeText(this, "Upload Berhasil", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                },
                {
                    //Log.e("gagalcreate", it.message.toString())
                    Toast.makeText(this, "Upload Gagal", Toast.LENGTH_SHORT).show()
                }
            )
            {
                override fun getParams(): MutableMap<String, String>? {
                    val map = HashMap<String, String>()
                    map.set("url", txtLinkAvatarCreateMeme.text.toString())
                    map.set("text_atas", txtTextAtasCM2.text.toString())
                    map.set("text_bawah", txtTextBawahCM2.text.toString())
                    map.set("id_pembuat", shared.getInt("IDUSER",0).toString())
                    return map
                }
            }
            q.add(stringRequest)
        }
    }
}