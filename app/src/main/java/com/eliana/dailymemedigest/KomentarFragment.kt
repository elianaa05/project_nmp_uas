package com.eliana.dailymemedigest

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [KomentarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class KomentarFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    val komentars:ArrayList<Komentar> = ArrayList()
    val mm:ArrayList<Memes> = ArrayList()

    fun updateList(){
        val lm: LinearLayoutManager = LinearLayoutManager(activity)
        val rv = view?.findViewById<RecyclerView>(R.id.recyclerViewDetail)
        rv?.layoutManager = lm
        rv?.setHasFixedSize(true)
        rv?.adapter = KomentarAdapter(komentars)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        //id = intent.getStringExtra("id").toString()

        var shared = this.activity?.getSharedPreferences("com.eliana.dailymemedigest", Context.MODE_PRIVATE)


        val q = Volley.newRequestQueue(activity)
        val url = "https://ubaya.fun/flutter/160719017/nmp/detailmeme2.php"

        var stringRequest =object: StringRequest(
            Request.Method.POST,
            url,
            Response.Listener {
                val obj = JSONArray(it)
                if(obj.getJSONObject(0).getString("result")=="success"){
                    val data = obj.getJSONObject(0).getJSONArray("data").getJSONObject(0).getJSONArray("memes")
                    //val data = obj.getJSONArray("data")
                    for(i in 0 until data.length()){
                        val komentarCard = data.getJSONObject(i)
                        val komentar = Komentar(
                            komentarCard.getInt("id"),
                            komentarCard.getInt("id_meme"),
                            komentarCard.getInt("id_pengguna"),
                            komentarCard.getString("isi_komentar"),
                            komentarCard.getString("tanggal_komentar"),
                        )
                        komentars.add(komentar)
                        Log.d("komentar", data.toString())
                    }
                    updateList()
                }
            },
            Response.ErrorListener {
                Log.wtf("komentar", it.message.toString())
            }
        ){
            override fun getParams(): MutableMap<String, String>? {
                //val id = shared?.getInt("id",0)
                //var idmeme = shared?.getInt("IDMEME",0)
                //var idmeme = 1
                val map = HashMap<String, String>()
                //map.set("id", idmeme.toString())
                return map
            }
        }
        q.add(stringRequest)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_komentar, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment KomentarFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            KomentarFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}