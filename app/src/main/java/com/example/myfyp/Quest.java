package com.example.myfyp;

import android.content.Intent;
import android.graphics.Color;
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

        String username, totals, ttls ;
        String verify1 , promid1 , require1;
        String verify2 , promid2 , require2;
        String verify3 , promid3 , require3;
        String verify4 , promid4 , require4;
        String verify5 , promid5 , require5;
        String verify6 , promid6 , require6;
        String URL_SERVER = "http://192.168.43.238/boons/server.php";
        TextView quest1 , quest2 , quest3, quest4, quest5, quest6;
        TextView questdis1 , questdis2 , questdis3, questdis4 , questdis5, questdis6;
        TextView req1 , req2 , req3 , req4 , req5 , req6 ;
        Button promo1 , promo2 , promo3 , promo4 , promo5 , promo6;
        TextView navUsername, totalScore;
        int total , r1 , r2 , r3 , r4 , r5 , r6;


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


        quest1 = (TextView)findViewById(R.id.textView1);
        quest2 = (TextView)findViewById(R.id.textView2);
        quest3 = (TextView)findViewById(R.id.textView3);
        quest4 = (TextView)findViewById(R.id.textView4);
        quest5 = (TextView)findViewById(R.id.textView5);
        quest6 = (TextView)findViewById(R.id.textView6);
        questdis1 = (TextView)findViewById(R.id.textView11);
        questdis2 = (TextView)findViewById(R.id.textView22);
        questdis3 = (TextView)findViewById(R.id.textView33);
        questdis4 = (TextView)findViewById(R.id.textView44);
        questdis5 = (TextView)findViewById(R.id.textView55);
        questdis6 = (TextView)findViewById(R.id.textView66);

        req1 = (TextView)findViewById(R.id.textViewr1);
        req2 = (TextView)findViewById(R.id.textViewr2);
        req3 = (TextView)findViewById(R.id.textViewr3);
        req4 = (TextView)findViewById(R.id.textViewr4);
        req5 = (TextView)findViewById(R.id.textViewr5);
        req6 = (TextView)findViewById(R.id.textViewr6);



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
        promo1 = (Button)findViewById(R.id.button1);
        promo2 = (Button)findViewById(R.id.button2);
        promo3 = (Button)findViewById(R.id.button3);
        promo4 = (Button)findViewById(R.id.button4);
        promo5 = (Button)findViewById(R.id.button5);
        promo6 = (Button)findViewById(R.id.button6);





        promo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent promo = new Intent(Quest.this, Promotion.class);
                promo.putExtra("username",username);
                promo.putExtra("promid",promid1);
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
                                String q3 = jsonObject.getString("questname3");
                                String q4 = jsonObject.getString("questname4");
                                String q5 = jsonObject.getString("questname5");
                                String q6 = jsonObject.getString("questname6");
                                String qd1 = jsonObject.getString("questdis1");
                                String qd2 = jsonObject.getString("questdis2");
                                String qd3 = jsonObject.getString("questdis3");
                                String qd4 = jsonObject.getString("questdis4");
                                String qd5 = jsonObject.getString("questdis5");
                                String qd6 = jsonObject.getString("questdis6");
                                require1 = jsonObject.getString("require1");
                                require2 = jsonObject.getString("require2");
                                require3 = jsonObject.getString("require3");
                                require4 = jsonObject.getString("require4");
                                require5 = jsonObject.getString("require5");
                                require6 = jsonObject.getString("require6");
                                promid1 = jsonObject.getString("promid1");
                                promid2 = jsonObject.getString("promid2");
                                promid3 = jsonObject.getString("promid3");
                                promid4 = jsonObject.getString("promid4");
                                promid5 = jsonObject.getString("promid5");
                                promid6 = jsonObject.getString("promid6");


                               ttls = jsonObject.getString("totalscore");



                                r1 = Integer.parseInt(require1);
                                r2 = Integer.parseInt(require2);
                                r3 = Integer.parseInt(require3);
                                r4 = Integer.parseInt(require4);
                                r5 = Integer.parseInt(require5);
                                r6 = Integer.parseInt(require6);

                                if (r1 > total){
                                    promo1.setVisibility(View.GONE);
                                    req1.setText("Unsuccess The Quest");
                                    req1.setTextColor(Color.parseColor("red"));
                                }
                                if (r2 > total){
                                    promo2.setVisibility(View.GONE);
                                    req2.setText("Unsuccess The Quest");
                                    req2.setTextColor(Color.parseColor("red"));
                                }
                                if (r3 > total){
                                    promo3.setVisibility(View.GONE);
                                    req3.setText("Unsuccess The Quest");
                                    req3.setTextColor(Color.parseColor("red"));
                                }
                                if (r4 > total){
                                    promo4.setVisibility(View.GONE);
                                    req4.setText("Unsuccess The Quest");
                                    req4.setTextColor(Color.parseColor("red"));
                                }
                                if (r5 > total){
                                    promo5.setVisibility(View.GONE);
                                    req5.setText("Unsuccess The Quest");
                                    req5.setTextColor(Color.parseColor("red"));
                                }
                                if (r6 > total){
                                    promo6.setVisibility(View.GONE);
                                    req6.setText("Unsuccess The Quest");
                                    req6.setTextColor(Color.parseColor("red"));
                                }


                                quest1.setText(q1);
                                quest2.setText(q2);
                                quest3.setText(q3);
                                quest4.setText(q4);
                                quest5.setText(q5);
                                quest6.setText(q6);
                                questdis1.setText(qd1 + "                "+total+" / "+require1);
                                questdis2.setText(qd2 + "                "+total+" / "+require2);
                                questdis3.setText(qd1 + "                "+total+" / "+require3);
                                questdis4.setText(qd2 + "                "+total+" / "+require4);
                                questdis5.setText(qd1 + "                "+total+" / "+require5);
                                questdis6.setText(qd2 + "                "+total+" / "+require6);
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
