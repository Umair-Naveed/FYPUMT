package com.example.fypumt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.sdsmdg.harjot.crollerTest.Croller;
import com.sdsmdg.harjot.crollerTest.OnCrollerChangeListener;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Switch Switch1, Switch2,Switch3,Switch4,Switch5,Switch6;
    ImageButton cameraButton, sendSpeed;
    String urlGlobal, urlLocal, fanLocal;
    TextView textView;
    GridLayout gridLayout;

    String switchData1, switchData2, switchData3, switchData4, switchData5, switchData6, fanSpeed;

    String ddns = "http://umtfyp.ddns.net";
    String arest = "https://cloud.arest.io/FYPUMT";
    String localIp = "http://192.168.1.25";
    //ImageView imageView;
    private int speed;

    private Croller croller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //imageView = findViewById(R.id.imgvw);
        //Animation animFadeOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
        //imageView.startAnimation(animFadeOut);

        gridLayout = findViewById(R.id.mainGrid);

        croller = findViewById(R.id.croller);
        croller.isEnabled();

        textView = findViewById(R.id.fanSpeedText);
        //To set the position of croller
        //croller.setProgress(25);
        croller.setOnCrollerChangeListener(new OnCrollerChangeListener() {
            @Override
            public void onProgressChanged(Croller croller, int progress) {
                int circleSeekBar = progress - 1;
                fanSpeed = Integer.toString(circleSeekBar);
                textView.setText(fanSpeed);
                if (circleSeekBar >= 0 && circleSeekBar <= 5){
                    speed = 0;
                }
                else if (circleSeekBar > 5 && circleSeekBar <= 25){
                    speed = 25;
                }
                else if (circleSeekBar > 25 && circleSeekBar <= 50){
                    speed = 50;
                }
                else if (circleSeekBar > 50 && circleSeekBar <= 75){
                    speed = 75;
                }
                else if (circleSeekBar > 75){
                    speed = 100;
                }
            }

            @Override
            public void onStartTrackingTouch(Croller croller) {

            }

            @Override
            public void onStopTrackingTouch(Croller croller) {

            }
        });

        Switch1 = findViewById(R.id.switch1);
        Switch2 = findViewById(R.id.switch2);
        Switch3 = findViewById(R.id.switch3);
        Switch4 = findViewById(R.id.switch4);
        Switch5 = findViewById(R.id.switch5);
        Switch6 = findViewById(R.id.switch6);

        cameraButton = findViewById(R.id.camera);

        sendSpeed = findViewById(R.id.sendSpeed);

        Switch1.setOnClickListener(this);
        Switch2.setOnClickListener(this);
        Switch3.setOnClickListener(this);
        Switch4.setOnClickListener(this);
        Switch5.setOnClickListener(this);
        Switch6.setOnClickListener(this);

        //Switch1.setChecked(true);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardView cardView = (CardView) gridLayout.getChildAt(7);
                cardView.setCardBackgroundColor(Color.parseColor("#008080"));
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Do something after 50ms
                        CardView cardView1 = (CardView) gridLayout.getChildAt(7);
                        cardView1.setCardBackgroundColor(Color.parseColor("#FFFFFF"));

                    }
                }, 50);

                OpenCameraActivity();
            }
        });

        sendSpeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardView cardView = (CardView) gridLayout.getChildAt(6);
                cardView.setCardBackgroundColor(Color.parseColor("#008080"));
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Do something after 100ms
                        CardView cardView1 = (CardView) gridLayout.getChildAt(6);
                        cardView1.setCardBackgroundColor(Color.parseColor("#FFFFFF"));

                    }
                }, 50);
                if(speed == 0)
                    speed0();
                else if(speed == 25)
                    speed1();
                else if(speed == 50)
                    speed2();
                else if(speed == 75)
                    speed3();
                else if(speed == 100)
                    speed4();

                Toast.makeText(MainActivity.this, "Fan Speed Sent", Toast.LENGTH_SHORT).show();
            }
        });


        //To receive data from myData
        FetchData();

    }
    private void speed0(){
        fanLocal = ddns + "/digital/6/0";
        //fanLocal = localIp + "/digital/6/0";
        RequestUrlLocal(fanLocal);
        fanLocal = ddns + "/digital/13/0";
        //fanLocal = localIp + "/digital/13/0";
        RequestUrlLocal(fanLocal);
        fanLocal = ddns + "/digital/19/0";
        //fanLocal = localIp + "/digital/19/0";
        RequestUrlLocal(fanLocal);
        fanLocal = ddns + "/digital/26/0";
        //fanLocal = localIp + "/digital/26/0";
        RequestUrlLocal(fanLocal);
        SaveData();
    }
    private void speed1(){
        fanLocal = ddns + "/digital/6/1";
        //fanLocal = localIp + "/digital/6/1";
        RequestUrlLocal(fanLocal);
        fanLocal = ddns + "/digital/13/0";
        //fanLocal = localIp + "/digital/13/0";
        RequestUrlLocal(fanLocal);
        fanLocal = ddns + "/digital/19/0";
        //fanLocal = localIp + "/digital/19/0";
        RequestUrlLocal(fanLocal);
        fanLocal = ddns + "/digital/26/0";
        //fanLocal = localIp + "/digital/26/0";
        RequestUrlLocal(fanLocal);
        SaveData();
    }
    private void speed2(){
        fanLocal = ddns + "/digital/6/1";
        //fanLocal = localIp + "/digital/6/1";
        RequestUrlLocal(fanLocal);
        fanLocal = ddns + "/digital/13/1";
        //fanLocal = localIp + "/digital/13/1";
        RequestUrlLocal(fanLocal);
        fanLocal = ddns + "/digital/19/0";
        //fanLocal = localIp + "/digital/19/0";
        RequestUrlLocal(fanLocal);
        fanLocal = ddns + "/digital/26/0";
        //fanLocal = localIp + "/digital/26/0";
        RequestUrlLocal(fanLocal);
        SaveData();
    }
    private void speed3(){
        fanLocal = ddns + "/digital/6/1";
        //fanLocal = localIp + "/digital/6/1";
        RequestUrlLocal(fanLocal);
        fanLocal = ddns + "/digital/13/1";
        //fanLocal = localIp + "/digital/13/1";
        RequestUrlLocal(fanLocal);
        fanLocal = ddns + "/digital/19/1";
        //fanLocal = localIp + "/digital/19/1";
        RequestUrlLocal(fanLocal);
        //fanLocal = localIp + "/digital/26/0";
        fanLocal = ddns + "/digital/26/0";
        RequestUrlLocal(fanLocal);
        SaveData();
    }
    private void speed4(){
        fanLocal = ddns + "/digital/6/1";
        //fanLocal = localIp + "/digital/6/1";
        RequestUrlLocal(fanLocal);
        fanLocal = ddns + "/digital/13/1";
        //fanLocal = localIp + "/digital/13/1";
        RequestUrlLocal(fanLocal);
        fanLocal = ddns + "/digital/19/1";
        //fanLocal = localIp + "/digital/19/1";
        RequestUrlLocal(fanLocal);
        fanLocal = ddns + "/digital/26/1";
        //fanLocal = localIp + "/digital/26/1";
        RequestUrlLocal(fanLocal);
        SaveData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.switch1:

                if (Switch1.isChecked())
                    switchData1 = "ON";
                else
                    switchData1 = "OFF";
                Switch1(switchData1);
                SaveData();
                break;
            case R.id.switch2:

                if (Switch2.isChecked())
                    switchData2 = "ON";
                else
                    switchData2 = "OFF";
                Switch2(switchData2);
                SaveData();
                break;
            case R.id.switch3:

                if (Switch3.isChecked())
                    switchData3 = "ON";
                else
                    switchData3 = "OFF";
                Switch3(switchData3);
                SaveData();
                break;
            case R.id.switch4:

                if (Switch4.isChecked())
                    switchData4 = "ON";
                else
                    switchData4 = "OFF";
                Switch4(switchData4);
                SaveData();
                break;
            case R.id.switch5:

                if (Switch5.isChecked())
                    switchData5 = "ON";
                else
                    switchData5 = "OFF";
                Switch5(switchData5);
                SaveData();
                break;
            case R.id.switch6:

                if (Switch6.isChecked())
                    switchData6 = "ON";
                else
                    switchData6 = "OFF";
                Switch6(switchData6);
                SaveData();
                break;
                default:
                    Toast.makeText(this, "Default", Toast.LENGTH_SHORT).show();
        }
        RequesturlGlobal(urlGlobal);
        RequestUrlLocal(urlLocal);
    }
    public void RequesturlGlobal(String urlGlobal){
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
    public void RequestUrlLocal(String urlLocal){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(urlLocal).build();
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

    private void OpenCameraActivity()
    {
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
    }

    private void Switch1(String condition){
        CardView cardView1 = (CardView) gridLayout.getChildAt(0);
        if (condition.equals("ON"))
        {
            urlGlobal = ddns + "/digital/17/1";
            urlLocal = localIp + "/digital/17/1";
            cardView1.setCardBackgroundColor(Color.parseColor("#008080"));
            Toast.makeText(this, "Bulb ON", Toast.LENGTH_SHORT).show();
        }
        else if (condition.equals("OFF")){
            urlGlobal = ddns + "/digital/17/0";
            urlLocal = localIp + "/digital/17/0";
            cardView1.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            Toast.makeText(this, "Bulb OFF", Toast.LENGTH_SHORT).show();

        }
    }
    private void Switch2(String condition){
        CardView cardView2 = (CardView) gridLayout.getChildAt(1);
        if (condition.equals("ON"))
        {
            urlGlobal = ddns + "/digital/27/1";
            urlLocal = localIp + "/digital/27/1";
            cardView2.setCardBackgroundColor(Color.parseColor("#008080"));
            Toast.makeText(this, "Tube Light ON", Toast.LENGTH_SHORT).show();
        }
        else if (condition.equals("OFF"))
        {
            urlGlobal = ddns + "/digital/27/0";
            urlLocal = localIp + "/digital/27/0";
            cardView2.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            Toast.makeText(this, "Tube Light OFF", Toast.LENGTH_SHORT).show();
        }

    }
    private void Switch3(String condition){
        CardView cardView3 = (CardView) gridLayout.getChildAt(2);
        if (condition.equals("ON")){
            urlGlobal = ddns + "/digital/22/1";
            urlLocal = localIp + "/digital/22/1";
            cardView3.setCardBackgroundColor(Color.parseColor("#008080"));
            Toast.makeText(this, "Table Lamp ON", Toast.LENGTH_SHORT).show();
        }else if (condition.equals("OFF")){
            urlGlobal = ddns + "/digital/22/0";
            urlLocal = localIp + "/digital/22/0";
            cardView3.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            Toast.makeText(this, "Table Lamp OFF", Toast.LENGTH_SHORT).show();
        }
    }
    private void Switch4(String condition){
        CardView cardView4 = (CardView) gridLayout.getChildAt(3);
        if (condition.equals("ON")){
            urlGlobal = ddns + "/digital/16/1";
            urlLocal = localIp + "/digital/16/1";
            cardView4.setCardBackgroundColor(Color.parseColor("#008080"));
            Toast.makeText(this, "Exhaust ON", Toast.LENGTH_SHORT).show();
        }else if(condition.equals("OFF")){
            urlGlobal = ddns + "/digital/16/0";
            urlLocal = localIp + "/digital/16/0";
            cardView4.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            Toast.makeText(this, "Exhaust OFF", Toast.LENGTH_SHORT).show();
        }
    }
    private void Switch5(String condition){
        CardView cardView5 = (CardView) gridLayout.getChildAt(4);
        if (condition.equals("ON")){
            urlGlobal = ddns + "/digital/20/1";
            urlLocal = localIp + "/digital/20/1";
            cardView5.setCardBackgroundColor(Color.parseColor("#008080"));
            Toast.makeText(this, "Television ON", Toast.LENGTH_SHORT).show();
        }else if (condition.equals("OFF")){
            urlGlobal = ddns + "/digital/20/0";
            urlLocal = localIp + "/digital/20/0";
            cardView5.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            Toast.makeText(this, "Television OFF", Toast.LENGTH_SHORT).show();
        }
    }
    private void Switch6(String condition){
        CardView cardView6 = (CardView) gridLayout.getChildAt(5);
        if (condition.equals("ON")){
            urlGlobal = ddns + "/digital/21/1";
            urlLocal = localIp + "/digital/21/1";
            cardView6.setCardBackgroundColor(Color.parseColor("#008080"));
            Toast.makeText(this, "Heater ON", Toast.LENGTH_SHORT).show();
        }else if (condition.equals("OFF")){
            urlGlobal = ddns + "/digital/21/0";
            urlLocal = localIp + "/digital/21/0";
            cardView6.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            Toast.makeText(this, "Heater OFF", Toast.LENGTH_SHORT).show();
        }
    }

    public void SaveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("switch1", switchData1);
        editor.putString("switch2", switchData2);
        editor.putString("switch3", switchData3);
        editor.putString("switch4", switchData4);
        editor.putString("switch5", switchData5);
        editor.putString("switch6", switchData6);
        editor.putString("fanSpeed", fanSpeed);
        //editor.commit();
        editor.apply();
    }

    public void FetchData() {

        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);

        switchData1 = sharedPreferences.getString("switch1", "");
        switchData2 = sharedPreferences.getString("switch2", "");
        switchData3 = sharedPreferences.getString("switch3", "");
        switchData4 = sharedPreferences.getString("switch4", "");
        switchData5 = sharedPreferences.getString("switch5", "");
        switchData6 = sharedPreferences.getString("switch6", "");
        fanSpeed = sharedPreferences.getString("fanSpeed", "0");
        final int speed = Integer.parseInt(fanSpeed);
        Switch1(switchData1);
        Switch2(switchData2);
        Switch3(switchData3);
        Switch4(switchData4);
        Switch5(switchData5);
        Switch6(switchData6);
        if (switchData1.equals("ON"))
            Switch1.setChecked(true);
        else
            Switch1.setChecked(false);
        if (switchData2.equals("ON"))
            Switch2.setChecked(true);
        else
            Switch2.setChecked(false);
        if (switchData3.equals("ON"))
            Switch3.setChecked(true);
        else
            Switch3.setChecked(false);
        if (switchData4.equals("ON"))
            Switch4.setChecked(true);
        else
            Switch4.setChecked(false);
        if (switchData5.equals("ON"))
            Switch5.setChecked(true);
        else
            Switch5.setChecked(false);
        if (switchData6.equals("ON"))
            Switch6.setChecked(true);
        else
            Switch6.setChecked(false);
        croller.setProgress(speed + 1);

    }
}
