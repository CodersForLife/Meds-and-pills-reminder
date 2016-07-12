package com.helptabteam.helptab;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button skip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final SharedPreferences sp=getSharedPreferences("Pref",MODE_PRIVATE);
        if (sp.contains("name1")) {
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
            finish();
        }
        setContentView(R.layout.activity_main);
        skip= (Button) findViewById(R.id.skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(MainActivity.this,EmergencyContact.class));
                finish();
            }
        });
    }
}
