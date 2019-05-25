package com.example.myfyp;

import android.content.Intent;
import android.os.Bundle;
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

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Promotion extends AppCompatActivity {

    String username , promid;
    String URL_SERVER = "http://192.168.0.101/boons/server.php";
    TextView promname, promdis , promterm, promcode , company;
    Button redeem;
    String currentdate , test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion);
        Intent login = getIntent();
        username = login.getStringExtra("username");
        promid = login.getStringExtra("promid");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Calendar calendar = Calendar.getInstance();
        currentdate = DateFormat.getDateInstance().format(calendar.getTime());

        promname = (TextView)findViewById(R.id.promname);
        promdis = (TextView)findViewById(R.id.promdescip);
        promterm = (TextView)findViewById(R.id.promterm);
        promcode = (TextView)findViewById(R.id.promcode);
        company = (TextView)findViewById(R.id.company);


        redeem = (Button)findViewById(R.id.redeem);
        onPromo();

        redeem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUpdate();
                redeem.setVisibility(View.GONE);
            }
        });


    }

    public void onPromo(){

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SERVER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.optString("success");

                            if (success.equals("1"))
                            {
                                String pname = jsonObject.getString("promname");
                                String pdes = jsonObject.getString("promdes");
                                String pterm = jsonObject.getString("promterm");
                                String pprice = jsonObject.getString("promprice");
                                String pcode = jsonObject.getString("promcode");
                                String comname = jsonObject.getString("comname");
                                String verify = jsonObject.getString("verify");
                                test = jsonObject.getString("test");

                                promname.setText(pname);
                                promdis.setText(pdes);
                                promterm.setText(pterm);
                                if (test.equalsIgnoreCase("1")){
                                    promcode.setText(pcode);
                                }else{
                                    promcode.setText("THE CODE HAD BEEN REDEEMED");
                                }

                                redeem.setText(pprice + "OFF DISCOUNT REDEEM!");
                                company.setText(comname);


                            }else{

                                Toast.makeText(Promotion.this, "Failed data recorded. ", Toast.LENGTH_SHORT).show();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Promotion.this, ""+ e.toString(), Toast.LENGTH_SHORT).show();

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Promotion.this, "Error"+ error.toString(), Toast.LENGTH_SHORT).show();


            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("selectFn","fnPromo");
                params.put("username",username);
                params.put("promid",promid);
                params.put("currentdate",currentdate);


                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    public void onUpdate(){

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SERVER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.optString("success");

                            if (success.equals("1"))
                            {
                                Toast.makeText(Promotion.this, "Successful redeemed , can check in History page. ", Toast.LENGTH_SHORT).show();

                            }else{

                                Toast.makeText(Promotion.this, "Failed data recorded. ", Toast.LENGTH_SHORT).show();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Promotion.this, ""+ e.toString(), Toast.LENGTH_SHORT).show();

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Promotion.this, "Error"+ error.toString(), Toast.LENGTH_SHORT).show();


            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("selectFn","fnUphis");
                params.put("username",username);
                params.put("promid",promid);
                params.put("currentdate",currentdate);


                return params;
            }
        };

        requestQueue.add(stringRequest);
    }
}
