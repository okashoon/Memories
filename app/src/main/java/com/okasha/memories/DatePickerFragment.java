package com.okasha.memories;


import android.app.AlertDialog;
import android.app.Dialog;
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


        Bundle args = new Bundle();
        args.putSerializable(EXTRA_DATE, date);
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setArguments(args);
        return newFragment;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        mDate =(Date) getArguments().getSerializable(EXTRA_DATE);
        Calendar c = Calendar.getInstance();
        c.setTime(mDate);
        day = c.get(Calendar.DAY_OF_MONTH);
        month = c.get(Calendar.MONTH);
        year = c.get(Calendar.YEAR);

        View v =getActivity().getLayoutInflater().inflate(R.layout.dialog_date, null);
        Dialog d = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok, null)
                .setView(v)
                .create();

        dp =(DatePicker) v.findViewById(R.id.dialog_date_datePicker);
        dp.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //change mDate according to date selected and put it in the arguments in case device rotated
                mDate = new GregorianCalendar(year,monthOfYear,dayOfMonth).getTime();
                getArguments().putSerializable(EXTRA_DATE,mDate);

            }
        });

        return d;


    }


}
