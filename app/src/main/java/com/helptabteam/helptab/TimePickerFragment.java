package com.helptabteam.helptab;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by Nimit Agg on 19-07-2016.
 */
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        AddNewDescription.hour=hour;
            AddNewDescription.min=minute;
        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        AddNewDescription.hour=hourOfDay;
        if(minute>=0 && minute<15)
            minute=0;
        else if(minute>=15 && minute<30)
            minute=15;
        else if(minute>=30 && minute<45)
            minute=30;
        else if(minute>=45 && minute<60)
            minute=45;
        AddNewDescription.min=minute;
        AddNewDescription.time.setText("Time: " + hourOfDay + ":"+ minute);
    }

}
