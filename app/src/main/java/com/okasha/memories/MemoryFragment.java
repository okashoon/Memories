package com.okasha.memories;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.text.SimpleDateFormat;


/**
 * A simple {@link Fragment} subclass.
 */
public class MemoryFragment extends Fragment {

    private Memory mMemory;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mStarred;

    public MemoryFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMemory = new Memory();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_memory, container, false);

        mTitleField =(EditText) v.findViewById(R.id.memory_title);
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mMemory.setTitle(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mDateButton = (Button)v.findViewById(R.id.memory_date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, yyyy");
        String dateFormatted = dateFormat.format(mMemory.getDate());
        mDateButton.setText(dateFormatted);
        mDateButton.setClickable(false);

        mStarred = (CheckBox) v.findViewById(R.id.starred_checkbox);
        mStarred.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mMemory.setStarred(isChecked);
            }
        });
        return  v;
    }

}
