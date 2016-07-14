package com.helptabteam.helptab;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddNewDescription extends AppCompatActivity {

    @NotEmpty
        private EditText title,description;
    private Button save;
    private TextView date,time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_description);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.iconslogo);
        title= (EditText) findViewById(R.id.title);
        description= (EditText) findViewById(R.id.decription);
        save= (Button) findViewById(R.id.save);
        date= (TextView) findViewById(R.id.date);
        time= (TextView) findViewById(R.id.time);
        final Calendar c = Calendar.getInstance();
        final SimpleDateFormat df = new SimpleDateFormat("dd MMM,yyyy", Locale.ENGLISH);
        SimpleDateFormat ti=new SimpleDateFormat("h:mm a");
        date.setText(date.getText()+" "+df.format(c.getTime()));
        time.setText(time.getText()+" "+ti.format(c.getTime()));
        final SimpleDateFormat time2=new SimpleDateFormat("hh:mm:ss");
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(QuoteColumns.TITLE, title.getText().toString());
                contentValues.put(QuoteColumns.DESCRIPTION,description.getText().toString());
                contentValues.put(QuoteColumns.START,""+time2.format(c.getTime()));
                getContentResolver().insert(QuoteProvider.Quotes.CONTENT_URI,contentValues);
                finish();
            }
        });
    }
}
