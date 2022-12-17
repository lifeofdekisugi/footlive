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

public class yallahShoot extends AppCompatActivity {

    WebView webViewYallah;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yallah_shoot);

        AdBlocker.init(this);

        webViewYallah = findViewById(R.id.webViewYallah);
        webViewYallah.setWebViewClient(new MyBrowser());

        WebSettings webSettings = webViewYallah.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webViewYallah.loadUrl("https://live.shoot-yalla.tv/");
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
        if (webViewYallah.canGoBack()){
            webViewYallah.goBack();
        }else {
//            super.onBackPressed();
            startActivity(new Intent(yallahShoot.this, Home.class));
            finish();
        }
    }
}