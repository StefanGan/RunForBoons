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


public class Login extends AppCompatActivity {
    String URL_SERVER = "http://192.168.0.107/boons/server.php";
    String username , password;
    EditText Id , Password;
    ProgressBar loading ;
    Button Logbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Id = (EditText) findViewById(R.id.IdText);
        Password = (EditText) findViewById(R.id.password);
        loading = (ProgressBar) findViewById(R.id.loading);
        loading.setVisibility(View.GONE);

        username = Id.getText().toString().trim();
        password = Password.getText().toString().trim();


            Logbtn = (Button) findViewById(R.id.LoginBtn);
            Logbtn.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view){
            Login();

        }
    });
    Button Registerbtn = (Button) findViewById(R.id.SignupBtn);
    Registerbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent registerIntent = new Intent(Login.this,Signup.class);
            startActivity(registerIntent);
        }
    });

    }

    public void Login(){

        loading.setVisibility(View.VISIBLE);
        username = Id.getText().toString().trim();
        password = Password.getText().toString().trim();



        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SERVER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");



                            if (success.equals("1"))
                            {
                                Toast.makeText(Login.this, "Login Success ", Toast.LENGTH_SHORT).show();
                                Intent Homepage = new Intent(Login.this, Homepage.class);
                                Homepage.putExtra("username",username);
                                startActivity(Homepage);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Login.this, "Login Error"+ e.toString(), Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                            Logbtn.setVisibility(View.VISIBLE);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Login.this, "Error"+ error.toString(), Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                        Logbtn.setVisibility(View.VISIBLE);

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("selectFn","fnLogin");
                params.put("username",username);
                params.put("password",password);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}