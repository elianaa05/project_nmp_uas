package com.eliana.dailymemedigest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_register.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        //val user:ArrayList<Users> = ArrayList()

        var username = txtUsernameRegister.text
        var password = txtPasswordRegister.text

        val sdf = SimpleDateFormat("yyyy/MM/dd")
        var tanggal = sdf.format(Date())

        var link = "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png"
        var pengaturan_privasi = 0

        btnCreateAccoutRegister.setOnClickListener {
            Toast.makeText(this, "halo", Toast.LENGTH_SHORT).show()
            var q = Volley.newRequestQueue(it.context)
            var url = "https://ubaya.fun/flutter/160719017/nmp/register.php"

            var stringRequest = object: StringRequest(
                Request.Method.POST,
                url,
                {
                    Log.d("cekdatabaru", it)
                    Toast.makeText(this, "Register Berhsail", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,Login::class.java)
                    startActivity(intent)
                    finish()
                },
                {
                    Log.e("cekdatabaru", it.message.toString())
                    Toast.makeText(this, "Register Gagal", Toast.LENGTH_SHORT).show()
                }
            )
            {
                override fun getParams(): MutableMap<String, String>?{
                    val map = HashMap<String, String>()
                    map.set("username",username.toString())
                    map.set("nama_depan", "")
                    map.set("nama_belakang", "")
                    map.set("password",password.toString())
                    map.set("tanggal",tanggal.toString())
                    map.set("link",link)
                    map.set("pengaturan_privasi",pengaturan_privasi.toString())
                    return map
                }
            }
            q.add(stringRequest)
        }
    }
}