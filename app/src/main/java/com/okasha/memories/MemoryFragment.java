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
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class MemoryFragment extends Fragment {

    public static final String EXTRA_CRIME_ID = "MemoryFragment.extra_crime_id";

    private Memory mMemory;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mStarredCheckBox;

    public MemoryFragment() {

    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID id=(UUID) getArguments().getSerializable(EXTRA_CRIME_ID);
        mMemory =(MemoryJournal.get(getActivity())).getMemory(id);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_memory, container, false);

        mTitleField =(EditText) v.findViewById(R.id.memory_title);
        mTitleField.setText(mMemory.getTitle());
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

        mStarredCheckBox = (CheckBox) v.findViewById(R.id.starred_checkbox);
        mStarredCheckBox.setChecked(mMemory.isStarred());
        mStarredCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mMemory.setStarred(isChecked);
            }
        });
        return  v;
    }

}
