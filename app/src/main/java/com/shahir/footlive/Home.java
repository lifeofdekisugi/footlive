package com.shahir.footlive;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.shahir.footballlive.R;

public class Home extends AppCompatActivity {

    TextView tvSixtyMinutes, tvYallaShoot, tvJeinzMacias, tvLiveSports;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvSixtyMinutes = findViewById(R.id.tvSixtyMinutes);
        tvYallaShoot = findViewById(R.id.tvYallaShoot);
        tvJeinzMacias = findViewById(R.id.tvJeinzMacias);
        tvLiveSports = findViewById(R.id.tvLiveSports);

        tvSixtyMinutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, sixtyMinutes.class));
            }
        });

        tvYallaShoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, yallahShoot.class));
            }
        });

        tvJeinzMacias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, jeinz.class));
            }
        });

        tvLiveSports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, liveSports.class));
            }
        });

    }


//    private void oldCode() {
//
//        /*
//                AdBlocker.init(this);
//
//        webView = findViewById(R.id.webView);
//        webView.setWebViewClient(new MyBrowser());
//
//        WebSettings webSettings = webView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//
//        webView.loadUrl("https://9goaltv.cc/home");
//         */
//
//
//        webView = findViewById(R.id.webView);
//        progressBar = findViewById(R.id.progressBar);
//        //progressBar.setVisibility(View.GONE);
//        progressBar.setMax(100);
//
//        webView.setWebViewClient(new WebViewClient());
//
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.getSettings().setUseWideViewPort(true);
//        webView.getSettings().setDomStorageEnabled(true);
//
//        webView.setWebChromeClient(new WebChromeClient(){
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                super.onProgressChanged(view, newProgress);
//                progressBar.setProgress(newProgress);
//            }
//
//            @Override
//            public void onReceivedTitle(WebView view, String title) {
//                super.onReceivedTitle(view, title);
//            }
//
//            @Override
//            public void onReceivedIcon(WebView view, Bitmap icon) {
//                super.onReceivedIcon(view, icon);
//            }
//        });
//
//        webView.loadUrl("https://9goaltv.cc/home");
//    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
        builder.setMessage("Do you want to exit the app ? \n \nDevelop by : Shahir Islam\n");
        builder.setCancelable(true);
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}