package com.tejuspicsum_app.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tejuspicsum_app.R
import com.tejuspicsum_app.adapter.ImageListAdapter
import com.tejuspicsum_app.model.ImageResponse
import com.tejuspicsum_app.viewmodel.ListViewModel

class MainActivity : AppCompatActivity(),ImageListAdapter.OnClickItemView {

    var listrecyclerview: RecyclerView? = null
    private lateinit var imageListAdapter: ImageListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listrecyclerview = findViewById(R.id.listrecyclerview);

        val viewManager = GridLayoutManager(this, 2)

        listrecyclerview.apply {

            this!!.layoutManager = viewManager
            isNestedScrollingEnabled = false
            hasFixedSize()
            setItemViewCacheSize(50)
            setDrawingCacheEnabled(true)
            setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH)

        }

        getImageList()
    }

    private fun getImageList() {

        ViewModelProvider(this).get(ListViewModel::class.java).getListData().observe(
                this, Observer<ArrayList<ImageResponse>> { getlistResponse ->

            imageListAdapter = ImageListAdapter(getlistResponse, this,this)

            listrecyclerview!!.adapter = imageListAdapter


        })
    }

    override fun OnClompleteItemView(itemsList: ImageResponse) {

        val intent=Intent(this,SingleViewActivity::class.java)
        intent.putExtra("data",itemsList)
        startActivity(intent)
    }


}