package com.okasha.memories;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DatePickerFragment extends DialogFragment {
    public static final String DIALOG_DATE = "com.okasha.memories.datePickerFragment.dialogDate";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v =getActivity().getLayoutInflater().inflate(R.layout.dialog_date,null);
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok, null)
                .setView(v)
                .create();

    }
}
