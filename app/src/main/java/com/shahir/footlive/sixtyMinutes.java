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

public class sixtyMinutes extends AppCompatActivity {

    WebView webViewSixty;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixty_minutes);

        AdBlocker.init(this);

        webViewSixty = findViewById(R.id.webViewSixty);
        webViewSixty.setWebViewClient(new MyBrowser());

        WebSettings webSettings = webViewSixty.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webViewSixty.loadUrl("https://wc.60mins.online/");
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
        if (webViewSixty.canGoBack()){
            webViewSixty.goBack();
        }else {
//            super.onBackPressed();
            startActivity(new Intent(sixtyMinutes.this, Home.class));
            finish();
        }
    }
}