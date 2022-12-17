package com.shahir.footlive;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.shahir.footballlive.R;

import java.util.HashMap;
import java.util.Map;

public class jeinz extends AppCompatActivity {

    WebView webViewJeinz;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeinz);

        AdBlocker.init(this);

        webViewJeinz = findViewById(R.id.webViewJeinz);
        webViewJeinz.setWebViewClient(new MyBrowser());

        WebSettings webSettings = webViewJeinz.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webViewJeinz.loadUrl("https://cn.jeinzmacias.net/");
    }

    public class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        private Map<String, Boolean> loadedUrls = new HashMap<>();
        @Nullable
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
            boolean ad;
            if (!loadedUrls.containsKey(url)) {
                ad = AdBlocker.isAd(url);
                loadedUrls.put(url, ad);
            } else {
                ad = loadedUrls.get(url);
            }
            return ad ? AdBlocker.createEmptyResource() :
                    super.shouldInterceptRequest(view, url);
        }
    }

    @Override
    public void onBackPressed() {
        if (webViewJeinz.canGoBack()){
            webViewJeinz.goBack();
        }else {
//            super.onBackPressed();
            startActivity(new Intent(jeinz.this, Home.class));
            finish();
        }
    }
}