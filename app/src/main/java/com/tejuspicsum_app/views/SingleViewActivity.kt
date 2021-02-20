package com.tejuspicsum_app.views

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.tejuspicsum_app.ApiService.ApiService
import com.tejuspicsum_app.R
import com.tejuspicsum_app.model.ImageResponse

class SingleViewActivity : AppCompatActivity() {
    var authourImage: ImageView? = null
    var authourName: TextView? = null

    var imageResponse: ImageResponse? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_view)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        if (intent != null) {

            imageResponse = intent.getSerializableExtra("data") as ImageResponse
        }

        authourImage = findViewById(R.id.authourImage)
        authourName = findViewById(R.id.authourName)

        Glide.with(this).asBitmap().load(ApiService.Image_URL + imageResponse!!.id)
            .into(authourImage!!)

        authourName!!.text = imageResponse!!.author


    }

    override fun onBackPressed() {
        super.onBackPressed()

        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId === android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
