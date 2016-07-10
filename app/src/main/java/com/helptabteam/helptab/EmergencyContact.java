package com.helptabteam.helptab;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EmergencyContact extends AppCompatActivity {
    Button save,skip;
    EditText name1,phone1,email1,name2,phone2,email2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contact);
        save= (Button) findViewById(R.id.save);
        skip= (Button) findViewById(R.id.skip);
        name1= (EditText) findViewById(R.id.name);
        phone1= (EditText) findViewById(R.id.phone1);
        email1= (EditText) findViewById(R.id.email1);
        name2= (EditText) findViewById(R.id.name2);
        phone2= (EditText) findViewById(R.id.phone2);
        email2= (EditText) findViewById(R.id.email2);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences =getSharedPreferences("Pref",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("name1",name1.getText().toString());
                editor.putString("phone1",phone1.getText().toString());
                editor.putString("email1",email1.getText().toString());
                editor.putString("name2",name2.getText().toString());
                editor.putString("phone2",phone2.getText().toString());
                editor.putString("email2",email2.getText().toString());
                editor.apply();
                editor.commit();
                startActivity(new Intent(EmergencyContact.this,HomeActivity.class));
            }
        });
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EmergencyContact.this,HomeActivity.class));
            }
        });
    }
}
