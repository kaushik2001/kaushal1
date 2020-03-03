package com.appsnipp.loginsamples.document_recycle;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


import com.appsnipp.loginsamples.R;


public class document_view extends AppCompatActivity  {

    private int pageNumber = 0;

    private String pdfFileName;

    public ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_view);
        Intent i=getIntent();
        String s=i.getStringExtra("dec_name");

        WebView webView = (WebView) findViewById(R.id.pdfview);
      webView.setWebViewClient(new WebViewClient());
      webView.loadUrl("http://docs.google.com/gview?embedded=true&url="+s);
      WebSettings se=webView.getSettings();
       se.setJavaScriptEnabled(true);
    }
}
