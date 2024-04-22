package com.andichia.webviewangular;

import static android.content.Context.NOTIFICATION_SERVICE;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;


import android.os.Build;
import android.provider.MediaStore;
import android.webkit.JavascriptInterface;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class WebAppInterface {

    private Activity _activity;
    private Context _context;

    public WebAppInterface(Context context,  Activity activity){
        _context = context;
        _activity = activity;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @JavascriptInterface
    public void showNotification(String title, String message){
        NotificationChannel channel = new NotificationChannel("STChannel", "ST", NotificationManager.IMPORTANCE_DEFAULT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(_context.getApplicationContext())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setChannelId(channel.getId());

        NotificationManager manager = (NotificationManager) _context.getSystemService(NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);
        manager.notify(1, builder.build());
    }
    @JavascriptInterface
    public void showCall(){
        Intent teleponIntent = new Intent(Intent.ACTION_DIAL);
        _context.startActivity(teleponIntent);
    }

    @JavascriptInterface
    public void showWhatsApp(){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Main"));
        _context.startActivity(intent);
    }

    @JavascriptInterface
    public void showCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        _context.startActivity(intent);
}
}