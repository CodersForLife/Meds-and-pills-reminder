package com.helptabteam.helptab;

import android.content.ContentValues;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    public static int hour,min;
    public static TextView date,time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_description);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.iconslogo);
        title = (EditText) findViewById(R.id.title);
        description = (EditText) findViewById(R.id.decription);
        save = (Button) findViewById(R.id.save);
        date = (TextView) findViewById(R.id.date);
        time = (TextView) findViewById(R.id.time);
        final Calendar c = Calendar.getInstance();
        final SimpleDateFormat df = new SimpleDateFormat("dd MMM,yyyy", Locale.ENGLISH);
        SimpleDateFormat ti = new SimpleDateFormat("HH:mm");
        date.setText(date.getText() + " " + df.format(c.getTime()));
        //time.setText(time.getText() + " " + ti.format(c.getTime()));
        //final Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        min = c.get(Calendar.MINUTE);
        if(min>=0 && min<15)
            min=0;
        else if(min>=15 && min<30)
            min=15;
        else if(min>=30 && min<45)
            min=30;
        else if(min>=45 && min<60)
            min=45;
        time.setText("Time: " + hour + ":"+ min);
        final SimpleDateFormat time2 = new SimpleDateFormat("hh:mm:ss");
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(QuoteColumns.TITLE, title.getText().toString());
                contentValues.put(QuoteColumns.DESCRIPTION, description.getText().toString());
                contentValues.put(QuoteColumns.START, "" + hour+":"+min+":"+"00");
                getContentResolver().insert(QuoteProvider.Quotes.CONTENT_URI, contentValues);
                finish();
            }
        });
    }
        public void showTimePickerDialog(View v) {
            DialogFragment newFragment = new TimePickerFragment();
            newFragment.show(getSupportFragmentManager(), "timePicker");
            Log.e("kjnin",hour+""+min);
        }

}
