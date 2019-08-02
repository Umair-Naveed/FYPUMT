package com.example.fypumt;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Switch Switch1, Switch2, Switch3, Switch4, Switch5, Switch6;
    ImageButton cameraButton, sendSpeed;
    String urlGlobal, fanGlobal;
    TextView textView;
    GridLayout gridLayout;

    private String responseUrl;
    String switchData1, switchData2, switchData3, switchData4, switchData5, switchData6, fanSpeed;

    String thingspeek1 = "https://api.thingspeak.com/update?key=VWGY0NZSR0X2FQIX&field1=";
    String thingspeek2 = "https://api.thingspeak.com/update?key=4FGSQSDCDJC0C2AJ&field1=";
    String thingspeek3 = "https://api.thingspeak.com/update?key=ONI9MC3W83M9QLLZ&field1=";
    String thingspeek4 = "https://api.thingspeak.com/update?key=TZ4AJEHKLTAHHUSB&field1=";
    String thingspeek5 = "https://api.thingspeak.com/update?key=Y1A3PCH4SPLT5FZK&field1=";
    String thingspeek6 = "https://api.thingspeak.com/update?key=HU62REVZXEIFPCPX&field1=";
    String fanspeed1 = "https://api.thingspeak.com/update?key=GV0G001K2OZH05FD&field1=";
    String fanspeed2 = "https://api.thingspeak.com/update?key=FVH7VZL9E5U5PEVN&field1=";
    String fanspeed3 = "https://api.thingspeak.com/update?key=9OOK7EGB5LQEPZ0U&field1=";
    String fanspeed4 = "https://api.thingspeak.com/update?key=IV5EEWU4B5PZS74R&field1=";
    private int speed;

    private Croller croller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridLayout = findViewById(R.id.mainGrid);

        croller = findViewById(R.id.croller);
        croller.isEnabled();

        textView = findViewById(R.id.fanSpeedText);
        croller.setOnCrollerChangeListener(new OnCrollerChangeListener() {
            @Override
            public void onProgressChanged(Croller croller, int progress) {
                int circleSeekBar = progress - 1;
                fanSpeed = Integer.toString(circleSeekBar);
                textView.setText(fanSpeed);
                if (circleSeekBar >= 0 && circleSeekBar <= 5) {
                    speed = 0;
                } else if (circleSeekBar > 5 && circleSeekBar <= 25) {
                    speed = 25;
                } else if (circleSeekBar > 25 && circleSeekBar <= 50) {
                    speed = 50;
                } else if (circleSeekBar > 50 && circleSeekBar <= 75) {
                    speed = 75;
                } else if (circleSeekBar > 75) {
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

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeButtonColor(7, 50);
                OpenCameraActivity();
            }
        });

        sendSpeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeButtonColor(6, 50);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        changeButtonColor(6, 50);
                    }
                }, 50);
                if (speed == 0)
                    speed0();
                else if (speed == 25)
                    speed1();
                else if (speed == 50)
                    speed2();
                else if (speed == 75)
                    speed3();
                else if (speed == 100)
                    speed4();
                SaveData();
                Toast.makeText(MainActivity.this, "Fan Speed Sent", Toast.LENGTH_SHORT).show();
                //GenAsync();
            }
        });

        //To receive data from myData
        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                FetchData();
            }
        }, 1500);
    }

    private void speed0() {
        fanGlobal = fanspeed1 + "60";
        RequestUrlGlobal(fanGlobal);
        fanGlobal = fanspeed2 + "130";
        RequestUrlGlobal(fanGlobal);
        fanGlobal = fanspeed3 + "190";
        RequestUrlGlobal(fanGlobal);
        fanGlobal = fanspeed4 + "260";
        RequestUrlGlobal(fanGlobal);
    }

    private void speed1() {
        fanGlobal = fanspeed1 + "61";
        RequestUrlGlobal(fanGlobal);
        fanGlobal = fanspeed2 + "130";
        RequestUrlGlobal(fanGlobal);
        fanGlobal = fanspeed3 + "190";
        RequestUrlGlobal(fanGlobal);
        fanGlobal = fanspeed4 + "260";
        RequestUrlGlobal(fanGlobal);
    }

    private void speed2() {
        fanGlobal = fanspeed1 + "61";
        RequestUrlGlobal(fanGlobal);
        fanGlobal = fanspeed2 + "131";
        RequestUrlGlobal(fanGlobal);
        fanGlobal = fanspeed3 + "190";
        RequestUrlGlobal(fanGlobal);
        fanGlobal = fanspeed4 + "260";
        RequestUrlGlobal(fanGlobal);
    }

    private void speed3() {
        fanGlobal = fanspeed1 + "61";
        RequestUrlGlobal(fanGlobal);
        fanGlobal = fanspeed2 + "131";
        RequestUrlGlobal(fanGlobal);
        fanGlobal = fanspeed3 + "191";
        RequestUrlGlobal(fanGlobal);
        fanGlobal = fanspeed4 + "260";
        RequestUrlGlobal(fanGlobal);
    }

    private void speed4() {
        fanGlobal = fanspeed1 + "61";
        RequestUrlGlobal(fanGlobal);
        fanGlobal = fanspeed2 + "131";
        RequestUrlGlobal(fanGlobal);
        fanGlobal = fanspeed3 + "191";
        RequestUrlGlobal(fanGlobal);
        fanGlobal = fanspeed4 + "261";
        RequestUrlGlobal(fanGlobal);
    }

            @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
        RequestUrlGlobal(urlGlobal);
        //GenAsync();

    }

    public void RequestUrlGlobal(String urlGlobal) {
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

    private void OpenCameraActivity() {
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
    }

    private void Switch1(String condition) {
        if (condition.equals("ON")) {
            urlGlobal = thingspeek1 + "231";
            changeButtonColor(0, "#008080");
        } else if (condition.equals("OFF")) {
            urlGlobal = thingspeek1 + "230";
            changeButtonColor(0, "#FFFFFF");
        }
        showResponse("https://api.thingspeak.com/channels/814144/feeds/last.json");
    }

    private void Switch2(String condition) {
        if (condition.equals("ON")) {
            urlGlobal = thingspeek2 + "271";
            changeButtonColor(1, "#008080");
        } else if (condition.equals("OFF")) {
            urlGlobal = thingspeek2 + "270";
            changeButtonColor(1, "#FFFFFF");
        }

        showResponse("https://api.thingspeak.com/channels/830195/feeds/last.json");
    }

    private void Switch3(String condition) {
        if (condition.equals("ON")) {
            urlGlobal = thingspeek3 + "221";
            changeButtonColor(2, "#008080");
        } else if (condition.equals("OFF")) {
            urlGlobal = thingspeek3 + "220";
            changeButtonColor(2, "#FFFFFF");
        }
        showResponse("https://api.thingspeak.com/channels/830206/feeds/last.json");
    }

    private void Switch4(String condition) {
        if (condition.equals("ON")) {
            urlGlobal = thingspeek4 + "161";
            changeButtonColor(3, "#008080");
        } else if (condition.equals("OFF")) {
            urlGlobal = thingspeek4 + "160";
            changeButtonColor(3, "#FFFFFF");
        }
        showResponse("https://api.thingspeak.com/channels/830217/feeds/last.json");
    }

    private void Switch5(String condition) {
        if (condition.equals("ON")) {
            urlGlobal = thingspeek5 + "201";
            changeButtonColor(4, "#008080");
        } else if (condition.equals("OFF")) {
            urlGlobal = thingspeek5 + "200";
            changeButtonColor(4, "#FFFFFF");
        }
        showResponse("https://api.thingspeak.com/channels/832458/feeds/last.json");
    }

    private void Switch6(String condition) {
        if (condition.equals("ON")) {
            urlGlobal = thingspeek6 + "181";
            changeButtonColor(5, "#008080");
        } else if (condition.equals("OFF")) {
            urlGlobal = thingspeek6 + "180";
            changeButtonColor(5, "#FFFFFF");
        }
        showResponse("https://api.thingspeak.com/channels/832459/feeds/last.json");
    }

    public void SaveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("switch1", switchData1);
        editor.putString("switch2", switchData2);
        editor.putString("switch3", switchData3);
        editor.putString("switch4", switchData4);
        editor.putString("switch5", switchData5);
        editor.putString("switch6", switchData6);
        editor.putString("fanSpeed", fanSpeed);
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
        if (switchData1.equals("ON")) {
            changeButtonColor(0, "#008080");
            Switch1.setChecked(true); }
        else {
            changeButtonColor(0, "#FFFFFF");
            Switch1.setChecked(false); }
        if (switchData2.equals("ON")) {
            changeButtonColor(1, "#008080");
            Switch2.setChecked(true); }
        else {
            changeButtonColor(1, "#FFFFFF");
            Switch2.setChecked(false); }
        if (switchData3.equals("ON")) {
            changeButtonColor(2, "#008080");
            Switch3.setChecked(true); }
        else {
            changeButtonColor(2, "#FFFFFF");
            Switch3.setChecked(false); }
        if (switchData4.equals("ON")) {
            changeButtonColor(3, "#008080");
            Switch4.setChecked(true); }
        else {
            changeButtonColor(3, "#FFFFFF");
            Switch4.setChecked(false); }
        if (switchData5.equals("ON")) {
            changeButtonColor(4, "#008080");
            Switch5.setChecked(true); }
        else {
            changeButtonColor(4, "#FFFFFF");
            Switch5.setChecked(false); }
        if (switchData6.equals("ON")) {
            changeButtonColor(5, "#008080");
            Switch6.setChecked(true); }
        else {
            changeButtonColor(5, "#FFFFFF");
            Switch6.setChecked(false); }

            croller.setProgress(speed + 1);

    }


    private void showResponse(String url) {
        responseUrl = url;
        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                new AsyncCaller().execute();
            }
        }, 3000);
    }
    private class AsyncCaller extends AsyncTask<Void, Void, Void> {
        ProgressDialog pdLoading = new ProgressDialog(MainActivity.this);
        String response;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pdLoading.setMessage("\tGetting response...");
            pdLoading.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            StringBuilder stringBuilder = new StringBuilder();
            try {
                URL link = new URL(responseUrl);
                HttpURLConnection urlConnection = (HttpURLConnection) link.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                byte[] buffer = new byte[8 * 1024];
                int i = inputStream.read(buffer);
                stringBuilder.append(new String(buffer, 0, i));
            } catch (Exception ignored) {} catch (OutOfMemoryError ignored) {}

            response = stringBuilder.toString();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            pdLoading.dismiss();

            try {
                JSONObject jsonObject = new JSONObject(response);
                String fieldData = jsonObject.getString("field1");

                String msg = null;
                if (fieldData.equals("230"))
                    msg = "Bulb turned off";
                else if (fieldData.equals("231"))
                    msg = "Bulb turned on";

                else if (fieldData.equals("270"))
                    msg = "Tuber Light turned off";
                else if (fieldData.equals("271"))
                    msg = "Tuber Light turned on";

                else if (fieldData.equals("220"))
                    msg = "Table Lamp turned off";
                else if (fieldData.equals("221"))
                    msg = "Table Lamp turned on";

                else if (fieldData.equals("160"))
                    msg = "Exhaust turned off";
                else if (fieldData.equals("161"))
                    msg = "Exhaust turned on";

                else if (fieldData.equals("200"))
                    msg = "Television turned off";
                else if (fieldData.equals("201"))
                    msg = "Television turned on";

                else if (fieldData.equals("180"))
                    msg = "Heater turned off";
                else if (fieldData.equals("181"))
                    msg = "Heater turned on";
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            } catch (JSONException ignored) {}
        }
    }

    //new

    public void changeButtonColor(final int index, int time){
        CardView cardView = (CardView) gridLayout.getChildAt(index);
        cardView.setCardBackgroundColor(Color.parseColor("#008080"));
        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                CardView cardView1 = (CardView) gridLayout.getChildAt(index);
                cardView1.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            }
        }, time);
    }
    public void changeButtonColor(final int index, String colorString){
        CardView cardView = (CardView) gridLayout.getChildAt(index);
        cardView.setCardBackgroundColor(Color.parseColor(colorString));
    }
}
