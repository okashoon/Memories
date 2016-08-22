package com.okasha.memories;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


public class MemoryCameraFragment extends Fragment {
    public static final String EXTRA_PIC_FILENAME = "com.okasha.memories.pic_filename";

    private Camera mCamera;
    View mProgressContainer;

    Camera.ShutterCallback  mShutterCallback = new Camera.ShutterCallback() {
        @Override
        public void onShutter() {
            mProgressContainer.setVisibility(View.VISIBLE);
        }
    };

    Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            String fileName = (UUID.randomUUID().toString())+".jpeg";
            FileOutputStream os = null;
            boolean success = true;

            try {
                os = getActivity().openFileOutput(fileName, Context.MODE_PRIVATE);
                os.write(data);
            } catch (Exception e) {
                Log.d("aaa","Error writing to file" + fileName, e);
                success = false;
            } finally {
                try {
                    if (os != null) {
                        os.close();
                    }
                } catch (Exception e){
                    Log.d("aaa","cant close file" +fileName ,e);
                    success = false;
                }
            }
            if(success){
                Log.d("aaa", " file successfully saved" + fileName);
                Intent intent = new Intent();
                intent.putExtra(EXTRA_PIC_FILENAME, fileName);
                getActivity().setResult(Activity.RESULT_OK,intent);
            }
            getActivity().finish();

        }
    };


    public MemoryCameraFragment() {
    }

    @Override
    public void onResume() {
        super.onResume();
        mCamera = Camera.open(0);
    }



    @Override
    public void onPause() {
        super.onPause();
        if (mCamera != null){
            mCamera.release();
            mCamera = null;

        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v  = inflater.inflate(R.layout.fragment_memory_camera, container, false);

        Button mTakePictureButton = (Button) v.findViewById(R.id.memory_camera_button);
        mTakePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCamera != null) {
                    mCamera.takePicture(mShutterCallback, null, mPictureCallback);
                }
            }
        });

        SurfaceView mSurfaceView = (SurfaceView) v.findViewById(R.id.memory_camera_surfaceView);
        SurfaceHolder holder = mSurfaceView.getHolder();
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (mCamera != null) {
                        mCamera.setPreviewDisplay(holder);

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                if (mCamera == null) return;

                try {
                    mCamera.startPreview();
                    Log.d("aaa", "preview started");
                } catch (Exception e) {
                    Log.e("aaa", "couldnt start preview", e);
                    mCamera.release();
                    mCamera = null;
                }

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

                if (mCamera != null) {
                    mCamera.stopPreview();

                }

            }
        });

        mProgressContainer = v.findViewById(R.id.memory_camera_progressContainer);
        mProgressContainer.setVisibility(View.INVISIBLE);

        return  v;
    }




}
