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

public class liveSports extends AppCompatActivity {

    WebView webViewLiveSports;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_sports);

        AdBlocker.init(this);

        webViewLiveSports = findViewById(R.id.webViewLiveSports);
        webViewLiveSports.setWebViewClient(new MyBrowser());

        WebSettings webSettings = webViewLiveSports.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webViewLiveSports.loadUrl("https://us.livesports808.com/");
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
        if (webViewLiveSports.canGoBack()){
            webViewLiveSports.goBack();
        }else {
//            super.onBackPressed();
            startActivity(new Intent(liveSports.this, Home.class));
            finish();
        }
    }
}