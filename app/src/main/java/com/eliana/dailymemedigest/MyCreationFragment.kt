package com.eliana.dailymemedigest

import android.content.Context
import android.content.SharedPreferences
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
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MyCreationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyCreationFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    val mm:ArrayList<Memes> = ArrayList()

    fun updateList(){
        val lm: LinearLayoutManager = LinearLayoutManager(activity)
        val rv = view?.findViewById<RecyclerView>(R.id.recyclerViewCardCreation)
        rv?.layoutManager = lm
        rv?.setHasFixedSize(true)
        rv?.adapter = MyCreationAdapter(mm)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        //this = your fragment
        var shared = getActivity()?.getSharedPreferences("com.eliana.dailymemedigest", Context.MODE_PRIVATE)

        val q = Volley.newRequestQueue(activity)
        val url = "https://ubaya.fun/flutter/160719017/nmp/creation.php"
        Log.d("creationku", url)

        var stringRequest =object: StringRequest(
            Request.Method.POST,
            url,
            Response.Listener {
                Log.d("cobacreation", it)
                val obj = JSONObject(it)
                if(obj.getString("result")=="success"){
                    val data = obj.getJSONArray("data")
                    for(i in 0 until data.length()){

                        val myCreation = data.getJSONObject(i)
                        val mc = Memes(
                            myCreation.getInt("id"),
                            myCreation.getString("url"),
                            myCreation.getString("text_atas"),
                            myCreation.getString("text_bawah"),
                            myCreation.getString("tanggal"),
                            myCreation.getInt("id_pembuat"),
                            myCreation.getInt("jumlah_like")
                        )
                        //Log.d("mycreation", mm.toString())
                        //Log.d("mycreation", id.toString())
                        mm.add(mc)
                    }
                    updateList()
                    Log.d("kreasi",mm.toString())

                }
            },
            Response.ErrorListener {
                Log.wtf("mycreation", it.message.toString())
            }
        ){
            override fun getParams(): MutableMap<String, String>? {
                var id = shared?.getInt("IDUSER",0)
                val map = HashMap<String, String>()
                map.set("id_pembuat", id.toString())
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
        return inflater.inflate(R.layout.fragment_my_creation, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MyCreationFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}