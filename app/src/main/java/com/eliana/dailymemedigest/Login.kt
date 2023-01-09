package com.eliana.dailymemedigest

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_register.*


class Login : AppCompatActivity() {
    companion object {
        val USERNAME = "USERNAME"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var shared: SharedPreferences = getSharedPreferences("com.eliana.dailymemedigest", Context.MODE_PRIVATE)

        btnLogin.setOnClickListener{
            var username = txtUsernameLogin.text.toString()
            var password = txtPasswordLogin.text.toString()
            var url = ""
                var stringRequest = object: StringRequest(
                    Request.Method.POST, url,
                    Response.Listener { Log.d("cekparams", it) },
                    Response.ErrorListener { Log.d("cekparams", it.message.toString()) }
                )
                {
                    override fun getParams(): MutableMap<String, String>? {
                        val map = HashMap<String, String>()
                        map.put("username", username)
                        map.put("password", password)
                        return map
                    }
                }
            Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnCreateAccount.setOnClickListener {
            val intent = Intent(this,Register::class.java)
            startActivity(intent)
            finish()
        }



    }
}