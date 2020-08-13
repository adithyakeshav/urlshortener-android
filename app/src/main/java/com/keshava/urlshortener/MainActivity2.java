package com.keshava.urlshortener;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.keshava.urlshortener.utils.ServerUtility;

import org.json.JSONException;
import org.json.JSONObject;

import static com.keshava.urlshortener.utils.Constants.*;

public class MainActivity2 extends AppCompatActivity {

    private static final String TAG = "MainActivity2";
    EditText shortString, expansionString;
    Button shorten ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        shortString = findViewById(R.id.shortString);
        expansionString = findViewById(R.id.expansionString);
        shorten = findViewById(R.id.shorten);

        shorten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject jsonObject = null;
                ServerUtility serverUtility = new ServerUtility(MainActivity2.this);
                Log.e(TAG, "onClick: " + "Button Clicked" );
                try {
                    jsonObject = serverUtility.createShortenApiJsonObject(
                            shortString.getText().toString(),
                            expansionString.getText().toString());
                    Log.e(TAG, "JSON Created: " + jsonObject.toString() );

                } catch (JSONException exception) {
                    Log.e(TAG, "onClick: " + exception.getMessage() );
                }

                serverUtility.callApiPost(SHORTEN_URL, jsonObject);
            }
        });

        findViewById(R.id.backbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ServerUtility(MainActivity2.this).callApiGet(GET_ALL_URL);
//                startActivity(new Intent(MainActivity2.this, MainActivity.class));
//                finish();
            }
        });

    }
}