package com.example.fypumt;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CameraActivity extends AppCompatActivity {

    WebView webview;
    String videoURL = "http://umtfyp.ddns.net:8081/";

    GridLayout gridLayout;

    String urlOn, urlOff;
    ImageButton openDoor, reload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        gridLayout = findViewById(R.id.cameraGrid);

        openDoor = findViewById(R.id.openDoor);
        reload = findViewById(R.id.reload);

        openDoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urlOn = "https://cloud.arest.io/FYPUMT/digital/17/1";
                RequestUrlGlobal(urlOn);
                Toast.makeText(CameraActivity.this, "Door is Unlocked!", Toast.LENGTH_SHORT).show();
                //Time delay for 500 milli seconds
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Do something after 100ms

                        urlOff = "https://cloud.arest.io/FYPUMT/digital/17/0";
                        RequestUrlGlobal(urlOff);
                    }
                }, 1000);

                changeButtonColor(0, 500);
            }
        });

        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webview.reload();
                changeButtonColor(1, 50);
            }
        });

        webview = findViewById(R.id.webView);
        webview.setWebViewClient(new WebViewClient());
        WebSettings set = webview.getSettings();
        set.setJavaScriptEnabled(true);
        set.setBuiltInZoomControls(true);
        webview.loadUrl(videoURL);
    }
    public void RequestUrlGlobal(String urlGlobal){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(urlGlobal).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

    public void changeButtonColor(final int index, int time){
        CardView cardView = (CardView) gridLayout.getChildAt(index);
        cardView.setCardBackgroundColor(Color.parseColor("#008080"));
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                CardView cardView1 = (CardView) gridLayout.getChildAt(index);
                cardView1.setCardBackgroundColor(Color.parseColor("#FFFFFF"));

            }
        }, time);
    }
}
