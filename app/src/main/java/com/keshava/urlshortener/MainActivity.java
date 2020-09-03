package com.keshava.urlshortener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.keshava.urlshortener.constants.Constants;
import com.keshava.urlshortener.utils.ListItemView;
import com.keshava.urlshortener.utils.ServerResponseCallback;
import com.keshava.urlshortener.utils.ServerUtility;
import com.keshava.urlshortener.utils.UrlModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.keshava.urlshortener.constants.Constants.DOMAIN_URL;
import static com.keshava.urlshortener.constants.Constants.ERROR_RESPONSE;
import static com.keshava.urlshortener.constants.Constants.EXPANSION_STRING;
import static com.keshava.urlshortener.constants.Constants.GET_ALL_URL;
import static com.keshava.urlshortener.constants.Constants.SHORT_STRING;

public class MainActivity extends AppCompatActivity {

    public static String TAG = "MainActivity";
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ListView listView;
    ListItemView listItemView;
    ArrayList<UrlModel> urlModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);

        navigationView.bringToFront();

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(new ListenerClass(getApplicationContext()));
        navigationView.setCheckedItem(R.id.nav_home);

        listView = findViewById(R.id.list_view);
        urlModels = new ArrayList<>();
        listItemView = new ListItemView(getApplicationContext(), urlModels);

        listView.setAdapter(listItemView);

        ServerUtility.callApiGet(MainActivity.this, GET_ALL_URL, new ServerResponseCallback() {
            @Override
            public void onJSONObjectResponse(JSONObject jsonObject) {

            }

            @Override
            public void onJSONArrayResponse(JSONArray jsonArray) {
                Log.e(TAG, "onJSONArrayResponse: " + jsonArray.length() );
                for (int i = 0; i< jsonArray.length(); i++) {
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        UrlModel urlModel = new UrlModel(
                                jsonObject.getString(SHORT_STRING),
                                jsonObject.getString(EXPANSION_STRING)
                        );

                        urlModels.add(urlModel);

                    } catch (JSONException e) {
                        Log.e(TAG, "onJSONArrayResponse: ", e );
                    }
                }

                listItemView.notifyDataSetChanged();
            }

            @Override
            public void onErrorResponse(Exception e) {
                Log.e(TAG, "onErrorResponse: ", e );
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e(TAG, "onItemClick: " +  urlModels.get(i).getExpansionString() );
                Intent browse = new Intent( Intent.ACTION_VIEW , Uri.parse( urlModels.get(i).getExpansionString() ) );

                startActivity( browse );
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            Log.d(TAG, "onBackPressed: " + "Back Button");
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public class ListenerClass implements NavigationView.OnNavigationItemSelectedListener {

        Context context;

        public ListenerClass() {
        }

        public ListenerClass(Context context) {
            this.context = context;
        }

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Log.e(TAG, "onNavigationItemSelected: " );
            switch (menuItem.getItemId()) {
                case R.id.nav_devices:
                    startActivity(new Intent(context, MainActivity2.class));
                    break;
                case R.id.nav_home:
                    break;
            }
            return true;
        }
    }

}