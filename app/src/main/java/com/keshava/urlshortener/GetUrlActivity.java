package com.keshava.urlshortener;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.keshava.urlshortener.constants.Constants.DOMAIN_URL;

public class GetUrlActivity extends AppCompatActivity {

    EditText getUrl;
    Button setUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_url);

        setUrl = findViewById(R.id.urlbutton);
        getUrl = findViewById(R.id.url);

        setUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = getUrl.getText().toString();
                DOMAIN_URL = url;
                Toast.makeText(GetUrlActivity.this, "URL is Set", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(GetUrlActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}