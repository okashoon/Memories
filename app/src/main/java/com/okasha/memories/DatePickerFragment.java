package com.okasha.memories;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class DatePickerFragment extends DialogFragment {
    public static final String DIALOG_DATE = "com.okasha.memories.datePickerFragment.dialogDate";
    public static final String EXTRA_DATE = "com.okasha.memories.datePickerFragment.date";
    Date mDate;
    DatePicker dp;
    int day;
    int month;
    int year;

    public static DatePickerFragment newInstance(Date date){

        //make new fragment instance with date from MemoryFragment
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_DATE, date);
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setArguments(args);
        return newFragment;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //use the date in arguments to initialize the datePicker
        mDate =(Date) getArguments().getSerializable(EXTRA_DATE);
        Calendar c = Calendar.getInstance();
        c.setTime(mDate);
        day = c.get(Calendar.DAY_OF_MONTH);
        month = c.get(Calendar.MONTH);
        year = c.get(Calendar.YEAR);

        View v =getActivity().getLayoutInflater().inflate(R.layout.dialog_date, null);
        Dialog d = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendResult(Activity.RESULT_OK);
                    }
                })
                .setView(v)
                .create();

        dp =(DatePicker) v.findViewById(R.id.dialog_date_datePicker);
        dp.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //change mDate according to date selected and put it in the arguments in case device rotated
                mDate = new GregorianCalendar(year, monthOfYear, dayOfMonth).getTime();
                getArguments().putSerializable(EXTRA_DATE, mDate);

            }
        });


        return d;


    }

    private void sendResult(int resultCode){
        if(getTargetFragment() == null){
            return;
        }
        Intent i = new Intent();
        i.putExtra(EXTRA_DATE,mDate);
        //call onActivityResult on MemoryFragment with the intent in it
        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,i);
    }


}
