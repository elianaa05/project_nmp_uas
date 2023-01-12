package com.eliana.dailymemedigest

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_settings.*
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SettingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        //SharedPreferences preferences = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);

        val q = Volley.newRequestQueue(activity)
        val url = "https://ubaya.fun/flutter/160719017/nmp/userlist.php"

//        var stringRequest = StringRequest(
//            Request.Method.GET,url,
//            {
//                Log.d("setting", it)
//                val obj = JSONObject(it)
//                var link = obj.getString("link")
//                var nama_depan = obj.getString("nama_depan")
//                var nama_belakang = obj.getString("nama_belakang")
//                var tanggal = obj.getString("tanggal")
//                var pengaturan_privasi = obj.getInt("pengaturan_privasi")
//
//                txtFirstNameSettings.text = nama_depan
//                txtLastNameSettings.text = nama_belakang
//                Picasso.get().load(link).into(imageView)
//                txtNameSettings.text =
//            }
//        )
//        q.add(stringRequest)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SettingFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SettingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}