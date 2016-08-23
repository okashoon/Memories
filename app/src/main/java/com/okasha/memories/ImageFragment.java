package com.okasha.memories;


import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageFragment extends DialogFragment {
    public static final String EXTRA_IMAGE_PATH = "com.okasha.memories.imagefragment.extra_image_path";
    ImageView mImageView;


    public static ImageFragment newInstance(String imagePath){
        ImageFragment fragment = new ImageFragment();
        fragment.setStyle(DialogFragment.STYLE_NO_TITLE,0);
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_IMAGE_PATH,imagePath);
        fragment.setArguments(args);
        return fragment;
    }


    public ImageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String imagePath = (String)getArguments().getSerializable(EXTRA_IMAGE_PATH);
        mImageView = new ImageView(getActivity());
        BitmapDrawable image = PictureUtils.getScaledImage(getActivity(), imagePath);
        mImageView.setImageDrawable(image);
        return mImageView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        PictureUtils.cleanImageView(mImageView);
    }
}
