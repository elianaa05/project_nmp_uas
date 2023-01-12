package com.eliana.dailymemedigest

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONObject


class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var shared: SharedPreferences = getSharedPreferences("com.eliana.dailymemedigest", Context.MODE_PRIVATE)
        var editor: SharedPreferences.Editor = shared.edit()

        var username = txtUsernameLogin.text
        var password = txtPasswordLogin.text

        btnLogin.setOnClickListener{
            var q = Volley.newRequestQueue(it.context)
            var url = "https://ubaya.fun/flutter/160719017/nmp/login.php"

            var stringRequest = object: StringRequest(
                Request.Method.POST,
                url,
                {
                        var json = JSONObject(it)
                        if(json.getString("result") == "success") {
                            editor.putString("USERNAME", username.toString())
                            editor.putString("FIRSTNAME", json.getString("firstname"))
                            editor.putString("LASTNAME", json.getString("lastname"))
                            editor.putString("LINK", json.getString("link"))
                            editor.putInt("IDUSER", json.getInt("id"))
                            editor.putString("REGDATE", json.getString("tanggal"))
                            editor.apply()

                            Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show()

                            val intent = Intent(this,MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                        else if (TextUtils.isEmpty(txtUsernameLogin.text.toString())) {
                            Toast.makeText(this, "Cek Username & Password", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this,Login::class.java)
                            startActivity(intent)
                            finish()
                        }
                },
                {
//                    Toast.makeText(this, "Login Gagal", Toast.LENGTH_SHORT).show()
                }
            )
            {
                override fun getParams(): MutableMap<String, String>? {
                    val params = HashMap<String, String>()
                    params.put("username", username.toString())
                    params.put("password", password.toString())
                    return params
                }
            }
            q.add(stringRequest)
        }

        btnCreateAccount.setOnClickListener {
            val intent = Intent(this,Register::class.java)
            startActivity(intent)
            finish()
        }

    }

}
