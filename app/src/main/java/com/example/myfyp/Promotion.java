package com.example.myfyp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

public class Promotion extends AppCompatActivity {

    String username , promid;
    String URL_SERVER = "http://192.168.0.103/boons/server.php";
    TextView promname, promdis , promterm, promcode , company;
    Button redeem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion);
        Intent login = getIntent();
        username = login.getStringExtra("username");
        promid = login.getStringExtra("promid");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        promname = (TextView)findViewById(R.id.promname);
        promdis = (TextView)findViewById(R.id.promdescip);
        promterm = (TextView)findViewById(R.id.promterm);
        promcode = (TextView)findViewById(R.id.promcode);
        company = (TextView)findViewById(R.id.company);

        redeem = (Button)findViewById(R.id.redeem);
        onPromo();


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
                params.put("selectFn","fnQuest");
                params.put("username",username);
                params.put("promid",promid);


                return params;
            }
        };

        requestQueue.add(stringRequest);
    }
}
