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

public class Quest extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

        String username, totals, verify1 , verify2 , promid1 , promid2, ttls , require1 , require2;
        String URL_SERVER = "http://192.168.0.105/boons/server.php";
        TextView quest1 , quest2 , questdis1 , questdis2;
        Button promo1 , promo2;
        TextView navUsername, totalScore;
        int total , r1 , r2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest);
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


        quest1 = (TextView)findViewById(R.id.questname1);
        quest2 = (TextView)findViewById(R.id.questname2);
        questdis1 = (TextView)findViewById(R.id.questdis);
        questdis2 = (TextView)findViewById(R.id.questdis2);
        total = Integer.parseInt(totals);
        onQuest();



        /*
        if (verify1.equals("0"))
        {
            quest1.setVisibility(View.GONE);
            questdis1.setVisibility(View.GONE);
        }
        if (verify2.equals("0"))
        {
            quest2.setVisibility(View.GONE);
            questdis2.setVisibility(View.GONE);
        }*/
        promo1 = (Button)findViewById(R.id.promote1);
        promo2 = (Button)findViewById(R.id.promote2);





        promo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent promo = new Intent(Quest.this, Promotion.class);
                promo.putExtra("username",username);
                promo.putExtra("promoid",promid1);
                startActivity(promo);


            }
        });

        promo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent promo = new Intent(Quest.this, Promotion.class);
                promo.putExtra("username",username);
                promo.putExtra("promid",promid2);
                startActivity(promo);
            }
        });

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
        getMenuInflater().inflate(R.menu.quest, menu);
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
            Intent Login = new Intent(Quest.this, Login.class);
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
            Intent HomePage = new Intent(Quest.this, Homepage.class);
            HomePage.putExtra("username",username);
            startActivity(HomePage);
        } else if (id == R.id.nav_quest) {
            Intent Quest = new Intent(Quest.this, Quest.class);
            Quest.putExtra("username",username);
            Quest.putExtra("totalscore",totals);
            startActivity(Quest);

        } else if (id == R.id.nav_history) {
            Intent History = new Intent(Quest.this, History.class);
            History.putExtra("username",username);
            History.putExtra("totalscore",totals);
            startActivity(History);


        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onQuest(){

        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SERVER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.optString("success");

                            if (success.equals("1"))
                            {
                                String q1 = jsonObject.getString("questname1");
                                String q2 = jsonObject.getString("questname2");
                                String qd1 = jsonObject.getString("questdis1");
                                String qd2 = jsonObject.getString("questdis2");
                                verify1 = jsonObject.getString("verify1");
                                verify2 = jsonObject.getString("verify2");
                                promid1 = jsonObject.getString("promid1");
                                promid2 = jsonObject.getString("promid2");
                               ttls = jsonObject.getString("totalscore");
                               require1 = jsonObject.getString("require1");
                              require2 = jsonObject.getString("require2");


                                r1 = Integer.parseInt(require1);
                                r2 = Integer.parseInt(require2);

                                if (r1 > total){
                                    promo1.setVisibility(View.GONE);
                                }
                                if (r2 > total){
                                    promo2.setVisibility(View.GONE);
                                }


                                quest1.setText(q1);
                                quest2.setText(q2);
                                questdis1.setText(qd1 + "                "+total+" / "+require1);
                                questdis2.setText(qd2 + "                "+total+" / "+require2);
                            }else{

                                Toast.makeText(Quest.this, "Failed data recorded. ", Toast.LENGTH_SHORT).show();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Quest.this, ""+ e.toString(), Toast.LENGTH_SHORT).show();

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Quest.this, "Error"+ error.toString(), Toast.LENGTH_SHORT).show();


            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("selectFn","fnQuest");
                params.put("username",username);

                return params;
            }
        };

        requestQueue.add(stringRequest);
    }
}
