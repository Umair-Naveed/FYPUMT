package com.example.fypumt;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class FadeOut extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fade_out);
        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 2000ms
                Intent intent = new Intent(FadeOut.this, MainActivity.class);
                startActivity(intent);
            }
        }, 2000);
        ImageView imageView = findViewById(R.id.callMainActivity);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Intent intent = new Intent(FadeOut.this, MainActivity.class);
                        startActivity(intent);
                }
                return true;
            }
        });
    }
}
