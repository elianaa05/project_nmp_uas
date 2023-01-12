package com.eliana.dailymemedigest

import android.Manifest
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.ContextThemeWrapper
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.bottomsheet.*
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.lang.Exception

class Settings : AppCompatActivity() {
    val pickImage = 100
    var imageUri: Uri? = null

    val REQUEST_IMAGE_CAPTURE = 1

    //val bottomSheet = BottomSheet()

    fun takePicture(){
        val intent = Intent()
        intent.action = MediaStore.ACTION_IMAGE_CAPTURE
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
        //Picasso.get().load(intent).into(imageViewSettings)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        var shared: SharedPreferences = getSharedPreferences("com.eliana.dailymemedigest", Context.MODE_PRIVATE)
        var editor: SharedPreferences.Editor = shared.edit()

        val imageView = findViewById<ImageView>(R.id.imageViewSettings)

        imageView.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Update gambar dengan : ").setPositiveButton("Kamera", DialogInterface.OnClickListener{
                dialogInterface, i ->  takePicture()
            }).setNegativeButton("Galeri", DialogInterface.OnClickListener{
                dialogInterface, i->
                val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                startActivityForResult(gallery, pickImage)
            }).show()
        }
        
        var firstname = shared.getString("FIRSTNAME", "")
        var lastname = shared.getString("LASTNAME", "")
        Picasso.get().load(shared.getString("LINK", "")).into(imageViewSettings)


        txtNameSettings.text = firstname + " " + lastname
        txtUsernameSettings.text = shared.getString("USERNAME", "")
        txtTanggalSettings.text = "Active since " + shared.getString("REGDATE", "")

        txtFirstNameSettings.setText(firstname)
        txtLastNameSettings.setText(lastname)

        btnSave.setOnClickListener {
            var q = Volley.newRequestQueue(it.context)
            var url = "https://ubaya.fun/flutter/160719017/nmp/update_profile.php"

            var stringRequest = object: StringRequest(
                Request.Method.POST,
                url,
                {
//                    Log.d("cekdatabaru", it)
                    Toast.makeText(this, "Update Berhasil", Toast.LENGTH_SHORT).show()
                    editor.putString("FIRSTNAME", txtFirstNameSettings.text.toString())
                    editor.putString("LASTNAME", txtLastNameSettings.text.toString())
                    editor.putString("LINK", imageUri.toString() )
                    editor.apply()

                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                },
                {
                    Log.d("apiresult", it.message.toString())
                    Toast.makeText(this, "Update Gagal", Toast.LENGTH_SHORT).show()
                }
            )
            {
                override fun getParams(): MutableMap<String, String>?{
                    val params = HashMap<String, String>()
                    params.set("nama_depan", txtFirstNameSettings.text.toString())
                    params.set("nama_belakang", txtLastNameSettings.text.toString())
                    params.set("link", imageUri.toString())
                    params.set("pengaturan_privasi", "1")
                    params["id"] = shared.getInt("IDUSER", 0).toString()
                    return params
                }
            }
            q.add(stringRequest)
        }


        btnLogoutSettings.setOnClickListener {
            editor.clear()
            editor.apply()
            val intent = Intent(this,Login::class.java)
            startActivity(intent)
            finish()
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK && requestCode == pickImage){
            imageUri = data?.data
            Picasso.get().load(imageUri).into(imageViewSettings)
            //imageViewSettings.setImageURI(imageUri)
        }
        else if(requestCode == REQUEST_IMAGE_CAPTURE){
            val extras = data?.extras
            val imageBitmap: Bitmap = extras?.get("data") as Bitmap
            imageViewSettings.setImageBitmap(imageBitmap)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            REQUEST_IMAGE_CAPTURE -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    takePicture()
                }
                else{
                    Toast.makeText(this, "You must grant permission to access the camera", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}