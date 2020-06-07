package com.example.listapp

import CardAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.example.listapp.activity.AboutActivity
import com.example.listapp.model.ApiModel
import com.example.listapp.model.Article
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val  dataList: MutableList<Article> = mutableListOf()
    private lateinit var adapter: CardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setActionBarTitle("Tech News")

        adapter = CardAdapter(dataList)

        rv_news.layoutManager = LinearLayoutManager(this)
        rv_news.addItemDecoration(DividerItemDecoration(this, OrientationHelper.VERTICAL))
        rv_news.adapter = adapter

        AndroidNetworking.initialize(this)

        AndroidNetworking.get("http://newsapi.org/v2/top-headlines?country=id&category=technology&apiKey=36fe26ba67d64765b26ca5e8eaf017b0")
            .build()
            .getAsObject(ApiModel::class.java, object : ParsedRequestListener<ApiModel>{
                override fun onResponse(response: ApiModel) {
                    dataList.addAll(response.articles)
                    adapter.notifyDataSetChanged()
                }

                override fun onError(anError: ANError?) {
                }

            })
    }

    private fun setActionBarTitle(title: String){
        if(supportActionBar != null){
            (supportActionBar as ActionBar).title = title
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem):Boolean = when(item.itemId){
        R.id.about -> {
            val moveIntent = Intent(this@MainActivity, AboutActivity::class.java)
            startActivity(moveIntent)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}
