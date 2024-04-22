package com.andichia.webviewangular;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private WebView _webView1;
    private Button _kirimButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _webView1 = findViewById(R.id.webView1);

        WebViewClient webViewClient = new WebViewClient();
        _webView1.setWebViewClient(webViewClient);

        WebChromeClient webChromeClient = new WebChromeClient();
        _webView1.setWebChromeClient(webChromeClient);

        WebSettings webSettings = _webView1.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);

        // Set cache mode to load from cache if available, otherwise from the network
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        // Enable DOM storage
        webSettings.setDomStorageEnabled(true);

        com.andichia.webviewangular.WebAppInterface webAppInterface = new WebAppInterface(MainActivity.this, this);
        _webView1.addJavascriptInterface(webAppInterface, "Android");

        String url = "https://stmikpontianak.net/011100862/angular011100862";
        _webView1.loadUrl(url);

    }

    @JavascriptInterface
    public void showNotification() {
        // Create notification channel
        NotificationChannel channel = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = new NotificationChannel("twChannel", "T", NotificationManager.IMPORTANCE_DEFAULT);
        }

        // Create notification builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "twChannel")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("E-mail Form")
                .setContentText("E-mail sudah dikirim kepada anda")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Get NotificationManager and create the notification channel
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.createNotificationChannel(channel);
        }

        // Notify with the builder
        manager.notify(1, builder.build());
    }





}
