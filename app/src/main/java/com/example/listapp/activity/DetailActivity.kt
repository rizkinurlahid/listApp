package com.example.listapp.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.example.listapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object{
        const val imgKey = "img_key"
        const val authorKey = "author_key"
        const val publishedKey = "published_key"
        const val titleKey = "title_key"
        const val contentKey = "content_key"
        const val urlKey = "url_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setActionBarTitle("News Detail")

        val image = intent.getStringExtra(imgKey)
        val author = intent.getStringExtra(authorKey) ?: "-"
        val published = intent.getStringExtra(publishedKey)
        val title = intent.getStringExtra(titleKey)
        val content = intent.getStringExtra(contentKey)
        val url = intent.getStringExtra(urlKey)

        Picasso.get()
            .load(image)
            .into(detail_img)

        detail_tv_author.text = "Author : $author"
        detail_tv_published.text = published
        detail_tv_title.text = title
        detail_tv_content.text = content
        detail_tv_url.text = url

        detail_tv_url.setOnClickListener {
            val actionViewIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(actionViewIntent)
        }
    }

    private fun setActionBarTitle(title: String){
        if(supportActionBar != null){
            (supportActionBar as ActionBar).title = title
        }
    }
}
