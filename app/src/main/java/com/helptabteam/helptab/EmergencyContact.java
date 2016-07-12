package com.helptabteam.helptab;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Digits;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.Max;
import com.mobsandgeeks.saripaar.annotation.Min;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

public class EmergencyContact extends AppCompatActivity {
    Button save,skip;
    @NotEmpty(trim = true,message = "Enter a valid name")
            EditText name1,name2;
    @NotEmpty
            @Email
            EditText email1,email2;

   // @Digits(integer=10,message = "Enter a 10 digit mobile number")
    @Length(min = 10,max = 10,trim = true,message ="Enter a 10 digit mobile number" )
            EditText phone1,phone2;
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

        final Validator validate=new Validator(this);
        validate.setValidationListener(new Validator.ValidationListener() {
            @Override
            public void onValidationSucceeded() {
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
                finish();

            }

            @Override
            public void onValidationFailed(List<ValidationError> errors) {
                for (ValidationError error : errors) {
                    View view = error.getView();
                    String message = error.getCollatedErrorMessage(EmergencyContact.this);

                    // Display error messages ;)
                    if (view instanceof EditText) {
                        ((EditText) view).setError(message);
                    } else {
                        Toast.makeText(EmergencyContact.this, message, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate.validate();
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
