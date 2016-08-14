package com.okasha.memories;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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

    public static final String EXTRA_MEMORY_ID = "MemoryFragment.extra_memory_id";

    private Memory mMemory;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mStarredCheckBox;

    public MemoryFragment() {

    }



    public static MemoryFragment newInstance(UUID id){
        MemoryFragment fragment = new MemoryFragment();
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_MEMORY_ID,id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID id=(UUID) getArguments().getSerializable(EXTRA_MEMORY_ID);
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

        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment dateDialog = new DatePickerFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                dateDialog.show(fm,DatePickerFragment.DIALOG_DATE);
            }
        });

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
