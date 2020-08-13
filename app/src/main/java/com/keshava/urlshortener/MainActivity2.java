package com.keshava.urlshortener;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.keshava.urlshortener.utils.JSONUtil;
import com.keshava.urlshortener.utils.ServerResponseCallback;
import com.keshava.urlshortener.utils.ServerUtility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.keshava.urlshortener.constants.Constants.*;

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
                Log.e(TAG, "onClick: " + "Button Clicked" );
                try {
                    jsonObject = JSONUtil.createShortenApiJsonObject(
                            shortString.getText().toString(),
                            expansionString.getText().toString());
                    Log.e(TAG, "JSON Created: " + jsonObject.toString() );

                } catch (JSONException exception) {
                    Log.e(TAG, "onClick: " + exception.getMessage() );
                }

                ServerUtility.callApiPost(MainActivity2.this, SHORTEN_URL, jsonObject, new ServerResponseCallback() {
                    @Override
                    public void onJSONObjectResponse(JSONObject jsonObject) {
                        Log.e(TAG, "onJSONObjectResponse: " + jsonObject.toString() );

                        if (jsonObject.has(SHORT_STRING)) {
                            Toast.makeText(MainActivity2.this, "URL Shortened! Success", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity2.this, MainActivity.class));
                            finish();
                        } else {
                            try {
                                Toast.makeText(MainActivity2.this, jsonObject.getString(ERROR_RESPONSE) , Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                Log.e(TAG, "onJSONObjectResponse: ", e );
                                Toast.makeText(MainActivity2.this, "Unknown Error", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }

                    @Override
                    public void onJSONArrayResponse(JSONArray jsonArray) {

                    }

                    @Override
                    public void onErrorResponse(Exception e) {

                    }
                });
            }
        });

        findViewById(R.id.backbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, MainActivity.class));
                finish();
            }
        });

    }
}