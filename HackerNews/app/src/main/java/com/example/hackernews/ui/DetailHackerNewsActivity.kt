package com.example.hackernews.ui

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity
import com.example.hackernews.R


class DetailHackerNewsActivity : AppCompatActivity() {

    private lateinit var webViewHackerNew: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_hacker_news)
        setwebView()
    }

    private fun setwebView(){
        webViewHackerNew = findViewById(R.id.webView)
        webViewHackerNew.webViewClient = WebViewClient()
        webViewHackerNew.setWebChromeClient(WebChromeClient())
        val webSettings: WebSettings = webViewHackerNew.getSettings()
        webSettings.javaScriptEnabled = true
        webSettings.allowFileAccess = true
        webSettings.setAppCacheEnabled(true)
        val url: String? = intent.getStringExtra("url")
        url?.let {
            webViewHackerNew.loadUrl(it)
        }

    }
}