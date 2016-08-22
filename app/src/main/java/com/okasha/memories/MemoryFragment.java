package com.okasha.memories;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Camera;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;



public class MemoryFragment extends Fragment {

    public static final String EXTRA_MEMORY_ID = "MemoryFragment.extra_memory_id";
    public static int REQUEST_DATE = 0;
    public static int REQUEST_PIC = 1;


    private Memory mMemory;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mStarredCheckBox;
    private ImageView mImageView;

    public MemoryFragment() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != Activity.RESULT_OK) return;
        if(requestCode==REQUEST_DATE){
            Date d =(Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mMemory.setDate(d);
            mDateButton.setText(mMemory.getDate().toString());
        }
        if (requestCode==REQUEST_PIC){
            String fileName = data.getStringExtra(MemoryCameraFragment.EXTRA_PIC_FILENAME);
            Photo p = new Photo(fileName);
            mMemory.setPhoto(p);
            Log.d("aaa", "memory " + mMemory.getTitle() + " has got a picture");
            showPhoto();
        }
    }

    //dont use constructor with memory as parameter with fragments, because when it is re-instantiated by the activity
    //the empty comstructor will be called
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
                DatePickerFragment dateDialog = DatePickerFragment.newInstance(mMemory.getDate());
                FragmentManager fm = getActivity().getSupportFragmentManager();
                dateDialog.setTargetFragment(MemoryFragment.this, REQUEST_DATE);
                dateDialog.show(fm, DatePickerFragment.DIALOG_DATE);


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

        Button mTakePicButton = (Button) v.findViewById(R.id.take_pic_button);
        mTakePicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MemoryCameraActivity.class);
                startActivityForResult(intent, REQUEST_PIC);
            }
        });

        Boolean hasCamera = Camera.getNumberOfCameras()>0;
        if(!hasCamera){
            mTakePicButton.setEnabled(false);
        }

        mImageView =(ImageView) v.findViewById(R.id.memory_imageView);
        return  v;
    }

    public void showPhoto(){
        Photo p = mMemory.getPhoto();
        BitmapDrawable b = null;
        if(p!=null){
            String path = getActivity().getFileStreamPath(p.getFilename()).getAbsolutePath();
            b = PictureUtils.getScaledImage(getActivity(),path);
        }
        mImageView.setImageDrawable(b);
    }

    @Override
    public void onStart() {
        super.onStart();
        showPhoto();
    }

    @Override
    public void onStop() {
        super.onStop();
        PictureUtils.cleanImageView(mImageView);
    }
}
