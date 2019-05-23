package com.example.myfyp;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Running extends AppCompatActivity implements SensorEventListener, StepListener {
    TextView TvSteps;
    String username , score;
    String URL_SERVER="http://192.168.0.102/boons/server.php";
    Button BtnStart,BtnStop;
    StepDetector simpleStepDetector;
    SensorManager sensorManager;
    Sensor accel;
    static final String TEXT_NUM_STEPS = "Number of Steps: ";
    int numSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent run = getIntent();
        username = run.getStringExtra("username");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Get an instance of the SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener(this);

        TvSteps = (TextView) findViewById(R.id.tv_steps);
        BtnStart = (Button) findViewById(R.id.btn_start);
        BtnStop = (Button) findViewById(R.id.btn_stop);
        BtnStop.setVisibility(View.GONE);



        BtnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                BtnStop.setVisibility(View.VISIBLE);
                BtnStart.setVisibility(View.GONE);
                numSteps = 0;
                sensorManager.registerListener(Running.this, accel, SensorManager.SENSOR_DELAY_FASTEST);

            }
        });


        BtnStop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                score = Integer.toString(numSteps);
                onRunning();
                sensorManager.unregisterListener(Running.this);

            }
        });



    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            simpleStepDetector.updateAccel(
                    event.timestamp, event.values[0], event.values[1], event.values[2]);
        }
    }

    @Override
    public void step(long timeNs) {
        numSteps++;
        TvSteps.setText(TEXT_NUM_STEPS + numSteps);
    }

    public void onRunning(){

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SERVER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.optString("success");

                            if (success.equalsIgnoreCase("1"))
                            {
                                BtnStart.setVisibility(View.VISIBLE);
                                BtnStop.setVisibility(View.GONE);
                                Toast.makeText(Running.this, "Successful recorded. ", Toast.LENGTH_SHORT).show();
                                Intent Homepage = new Intent(Running.this, Homepage.class);
                                startActivity(Homepage);
                            }else{
                                BtnStart.setVisibility(View.VISIBLE);
                                BtnStop.setVisibility(View.GONE);
                                Toast.makeText(Running.this, "Failed update recorded. ", Toast.LENGTH_SHORT).show();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Running.this, ""+ e.toString(), Toast.LENGTH_SHORT).show();
                            BtnStop.setVisibility(View.VISIBLE);
                            BtnStart.setVisibility(View.GONE);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Running.this, "Error"+ error.toString(), Toast.LENGTH_SHORT).show();
                BtnStop.setVisibility(View.VISIBLE);
                BtnStart.setVisibility(View.GONE);

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("selectFn","fnRunning");
                params.put("steps",score);
                params.put("username",username);

                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

}