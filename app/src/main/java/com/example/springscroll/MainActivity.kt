package com.example.springscroll

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var list = ArrayList<String>()
        initData(list)
        var linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        var mAdapter = SimpleRecycleViewAdapter(list)
        wrap_recyclerview.setLayoutManager(linearLayoutManager)
        wrap_recyclerview.setAdapter(mAdapter)
    }

    fun initData(list: ArrayList<String>) {
        for (i in 1..20) {
            list.add("item $i")
        }
    }
}
