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

class LeaderboardFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    val lc:ArrayList<LeaderboardClass> = ArrayList()

    fun updateList(){
        val lm: LinearLayoutManager = LinearLayoutManager(activity)
        val rv = view?.findViewById<RecyclerView>(R.id.recyclerViewLeaderboard)
        rv?.layoutManager = lm
        rv?.setHasFixedSize(true)
        rv?.adapter = LeaderboardAdapter(lc)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        val q = Volley.newRequestQueue(activity)
        val url = "https://ubaya.fun/flutter/160719017/nmp/leaderboard.php"
        Log.d("url",url)

        var shared = this.activity?.getSharedPreferences("com.eliana.dailymemedigest", Context.MODE_PRIVATE)
        var nama = shared?.getString("FIRSTNAME", "")

        var stringRequest = StringRequest(
            Request.Method.POST,
            url,
            Response.Listener {
                Log.d("cekleader", it)
                val obj = JSONObject(it)
                if(obj.getString("result")=="success"){
                    val data = obj.getJSONArray("data")
                    for(i in 0 until data.length()){
                        val lb = data.getJSONObject(i)
                        val leaderboard = LeaderboardClass(
                            lb.getInt("id"),
                            lb.getString("nama"),
                            lb.getString("jumlah_like"),
                            lb.getString("link"),
                        )
                        lc.add(leaderboard)
                    }
                    //Log.d("leaderboardku", lc.toString())
                    updateList()
                }
            },
            Response.ErrorListener {
                Log.wtf("gagalleaderboard", it.message.toString())
            }
        )
        q.add(stringRequest)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_leaderboard, container, false)
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            LeaderboardFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}