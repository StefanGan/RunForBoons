package com.example.myfyp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class History extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String username , totals;
    String URL_SERVER = "http://192.168.0.9/boons/server.php";
    TextView navUsername, totalScore;
    TextView date1 , steps1;
    TextView date2 , steps2;
    TextView date3 , steps3;
    TextView date4 , steps4;
    TextView date5 , steps5;
    TextView date6 , steps6;
    TextView date7 , steps7;
    TextView date8 , steps8;
    TextView date9 , steps9;
    TextView date10 , steps10;
    int num;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Intent login = getIntent();
        username = login.getStringExtra("username");
        totals = login.getStringExtra("totalscore");


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        navUsername = (TextView) headerView.findViewById(R.id.userid);
        totalScore = (TextView) headerView.findViewById(R.id.totalscore);
        navUsername.setText(username);
        totalScore.setText("Total Steps : " + totals);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        date1 = (TextView)findViewById(R.id.textView1);
        steps1 = (TextView)findViewById(R.id.textView11);
        date2 = (TextView)findViewById(R.id.textView2);
        steps2 = (TextView)findViewById(R.id.textView22);
        date3 = (TextView)findViewById(R.id.textView3);
        steps3 = (TextView)findViewById(R.id.textView33);
        date4 = (TextView)findViewById(R.id.textView4);
        steps4 = (TextView)findViewById(R.id.textView44);
        date5 = (TextView)findViewById(R.id.textView5);
        steps5 = (TextView)findViewById(R.id.textView55);
        date6 = (TextView)findViewById(R.id.textView6);
        steps6 = (TextView)findViewById(R.id.textView66);
        date7 = (TextView)findViewById(R.id.textView7);
        steps7 = (TextView)findViewById(R.id.textView77);
        date8 = (TextView)findViewById(R.id.textView8);
        steps8 = (TextView)findViewById(R.id.textView88);
        date9 = (TextView)findViewById(R.id.textView9);
        steps9 = (TextView)findViewById(R.id.textView99);
        date10 = (TextView)findViewById(R.id.textView10);
        steps10 = (TextView)findViewById(R.id.textView1010);


        onHistory();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.history, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            Intent Login = new Intent(History.this, Login.class);
            startActivity(Login);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent HomePage = new Intent(History.this, Homepage.class);
            HomePage.putExtra("username",username);
            startActivity(HomePage);
        } else if (id == R.id.nav_quest) {
            Intent Quest = new Intent(History.this, Quest.class);
            Quest.putExtra("username",username);
            Quest.putExtra("totalscore",totals);
            startActivity(Quest);

        } else if (id == R.id.nav_history) {
            Intent History = new Intent(History.this, History.class);
            History.putExtra("username",username);
            History.putExtra("totalscore",totals);
            startActivity(History);


        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onHistory(){

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SERVER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            //String success = jsonObject.optString("success");

                           // if (success.equals("1"))
                           // {
                                String n = jsonObject.getString("name");
                                String dat = jsonObject.getString("date");
                                String de = jsonObject.getString("des");
                                String cod = jsonObject.getString("code");

                                name.setText(n);
                                date.setText(dat);
                                des.setText(de);
                                code.setText(cod);


                           // }else{

                            //    Toast.makeText(History.this, "Failed data recorded. ", Toast.LENGTH_SHORT).show();
                           //   }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(History.this, ""+ e.toString(), Toast.LENGTH_SHORT).show();

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(History.this, "Error"+ error.toString(), Toast.LENGTH_SHORT).show();


            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("selectFn","fnHistory");
                params.put("username",username);

                return params;
            }
        };

        requestQueue.add(stringRequest);
    }
}
