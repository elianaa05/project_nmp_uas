package com.eliana.dailymemedigest

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

class MemesFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    val mm:ArrayList<Memes> = ArrayList()

    fun updateList(){
        val lm: LinearLayoutManager = LinearLayoutManager(activity)
        val rv = view?.findViewById<RecyclerView>(R.id.recyclerViewMeme)
        rv?.layoutManager = lm
        rv?.setHasFixedSize(true)
        rv?.adapter = MemesAdapter(mm)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        val q = Volley.newRequestQueue(activity)
        val url = "https://ubaya.fun/flutter/160719017/nmp/memes.php"

        var stringRequest = StringRequest(
            Request.Method.POST,
            url,
            Response.Listener {
                Log.d("apiresult", it)
                val obj = JSONObject(it)
                if(obj.getString("result")=="OK"){
                    val data = obj.getJSONArray("data")
                    for(i in 0 until data.length()){
                        val memesCard = data.getJSONObject(i)
                        val memes = Memes(
                            memesCard.getInt("id"),
                            memesCard.getString("url"),
                            memesCard.getString("text_atas"),
                            memesCard.getString("text_bawah"),
                            memesCard.getString("tanggal"),
                            memesCard.getInt("id_pembuat"),
                            memesCard.getInt("jumlah_like")
                        )
                        mm.add(memes)
                    }

                    updateList()

                    //Log.d("cekisiarrayList", playlists.toString())
                }
            },
            Response.ErrorListener {
                Log.wtf("apiresult", it.message.toString())
            }
        )
        q.add(stringRequest)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*fab?.setOnClickListener {
            activity?.let {
                val intent = Intent(it, AddMemeActivity::class.java)
                it.startActivity(intent)
            }
        }*/
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_memes, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MemesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MemesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}