package com.example.fypumt;

import android.graphics.Color;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CameraActivity extends AppCompatActivity {

    WebView webview;
    String videoUrl = "https://youtu.be/3XqcZPJxiyw";

    GridLayout gridLayout;

    String urlOn, urlOff, camera;
    String local = "http://192.168.1.25";
    String global = "https://api.thingspeak.com/update?key=3GHF2BHT1ZK3XB38&field1=25";
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
                urlOn = global + "1";
                //urlOn = local + "/digital/25/1";
                RequestUrl(urlOn);
                camera = "ON";
                //GenAsync();
                Toast.makeText(CameraActivity.this, "Door is Unlocked!", Toast.LENGTH_SHORT).show();
                //Time delay for 17 seconds
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        urlOff = global + "0";
                        camera = "OFF";
                        //GenAsync();
                        RequestUrl(urlOff);
                    }
                }, 17000);
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
        webview.loadUrl(videoUrl);
    }
    public void RequestUrl(String url){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
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
        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                CardView cardView1 = (CardView) gridLayout.getChildAt(index);
                cardView1.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            }
        }, 1500);
    }

}
