package com.eliana.dailymemedigest

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.bottomsheet.*

class BottomSheet:BottomSheetDialogFragment(){
    val pickImage = 100
    var imageUri: Uri? = null

    val REQUEST_IMAGE_CAPTURE = 1

    //val bottomSheet = BottomSheet()

//    fun takePicture(){
//        val intent = Intent()
//        intent.action = MediaStore.ACTION_IMAGE_CAPTURE
//        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        when(requestCode){
//            REQUEST_IMAGE_CAPTURE -> {
//                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                    takePicture()
//                }
//                else{
//                    Toast.makeText(context, "You must grant permission to access the camera", Toast.LENGTH_LONG).show()
//                }
//            }
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottomsheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        imageViewSettings.setOnClickListener {
//            bottomSheet.show(supportFragmentManager, "BottomSheetDialog")
//            btnGaleri.setOnClickListener {
//                Toast.makeText(context, "Galeri", Toast.LENGTH_SHORT).show()
//                val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
//                startActivityForResult(gallery, pickImage)
//            }
//
//            btnKamera.setOnClickListener {
//                //val view = layoutInflater.inflate(R.layout.)
//                if(ContextCompat.checkSelfPermission(btnKamera.context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
//                    ActivityCompat.requestPermissions(context as Activity, arrayOf(Manifest.permission.CAMERA), REQUEST_IMAGE_CAPTURE)
//                }
//                else{
//                    takePicture()
//                }
//            }
//
//        }
//
////        btnKamera.setOnClickListener {
////            Toast.makeText(context, "Kamera", Toast.LENGTH_SHORT).show()
////        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (resultCode == AppCompatActivity.RESULT_OK && requestCode == pickImage){
//            imageUri = data?.data
//            Picasso.get().load(imageUri).into(imageViewSettings)
//            //imageViewSettings.setImageURI(imageUri)
//        }
//        else{
//
//        }
//    }


}