package com.example.myfyp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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


public class Signup extends AppCompatActivity {

    EditText user;
    EditText pass;
    EditText conpass ;
    String URL_SERVER ="http://192.168.0.102/boons/server.php";
    ProgressBar loading ;
    Button cancel;
    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        user = (EditText) findViewById(R.id.Username);
        pass= (EditText) findViewById(R.id.Userpass);
        conpass = (EditText) findViewById(R.id.Conpass);
        loading = (ProgressBar) findViewById(R.id.loading);
        cancel = (Button) findViewById(R.id.Cancel);
        submit = (Button) findViewById(R.id.Signup);
        loading.setVisibility(View.GONE);





        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Loginintent = new Intent(Signup.this,Login.class);
                startActivity(Loginintent);

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String password = pass.getText().toString().trim();
                String confirm_pass = conpass.getText().toString().trim();

        if (password.equals(confirm_pass)){
                Regist();
            }else
        {
            Toast.makeText(Signup.this, "Password not match", Toast.LENGTH_SHORT).show();
        }
            }
        });


    }

        public void Regist(){

            loading.setVisibility(View.VISIBLE);
            submit.setVisibility(View.GONE);
            final String username = user.getText().toString().trim();
            final String password = pass.getText().toString().trim();

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
                                    Toast.makeText(Signup.this, "Register Success ", Toast.LENGTH_SHORT).show();
                                    Intent Login = new Intent(Signup.this, Login.class);
                                    startActivity(Login);
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(Signup.this, ""+ e.toString(), Toast.LENGTH_SHORT).show();
                                loading.setVisibility(View.GONE);
                                submit.setVisibility(View.VISIBLE);
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Signup.this, "Error"+ error.toString(), Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                            submit.setVisibility(View.VISIBLE);

                        }
                    })
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("selectFn","fnSignup");
                    params.put("username",username);
                    params.put("password",password);

                    return params;
                }
            };

            requestQueue.add(stringRequest);
        }

    }



