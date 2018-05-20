package com.example.vuclip.multiplerecyclerview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.vuclip.multiplerecyclerview.adapter.MainVerticalRecyclerAdapter
import com.example.vuclip.multiplerecyclerview.models.Curriculum
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data: List<Curriculum> = Arrays.asList(
                Curriculum("First", Arrays.asList("M1", "BCE", "BXE", "M2", "Mechanics", "Physics", "Chemistry"), "SMALL"),
                Curriculum("Second", Arrays.asList("DSPS", "MA", "M3", "OOPS", "DELD", "OSA"), "LARGE"),
                Curriculum("Third", Arrays.asList("AMA", "TOC", "CSFA", "PC", "CN", "DS", "DA", "SSDA", "CC"), "SMALL"),
                Curriculum("Fourth", Arrays.asList("PCDP", "Project", "MC", "NLP", "WT", "BA"), "LARGE")
        )

        val recyclerView = setUpRecyclerView()
        val adapter = MainVerticalRecyclerAdapter(data)
        recyclerView?.adapter = adapter
    }

    private fun setUpRecyclerView(): RecyclerView? {
        val recyclerView = findViewById<RecyclerView>(R.id.vertical_recycler_view)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        return recyclerView
    }
}
