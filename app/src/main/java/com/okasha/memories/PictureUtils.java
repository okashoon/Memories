package com.okasha.memories;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.Display;
import android.widget.ImageView;

/**
 * Created by ahmed on 22-Aug-16.
 */
public class PictureUtils {

    public static BitmapDrawable getScaledImage(Activity a, String path){
        //get the size of default display of device
        Display display = a.getWindowManager().getDefaultDisplay();
        float destHeight = display.getHeight();
        float destWidth = display.getWidth();

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; //get bitmap outWidth and outHeight without having to allocate it in memory
        BitmapFactory.decodeFile(path,options);
        float srcHeight = options.outHeight;
        float srcWidth = options.outWidth;

        int inSampleSize = 1;
        if(srcHeight>destHeight||srcWidth>destWidth){
            if(srcHeight>destHeight){
                inSampleSize = Math.round(srcHeight/destHeight);
            }
            if (srcWidth>destWidth){
                inSampleSize = Math.round(srcWidth/destWidth);
            }
        }
        options = new BitmapFactory.Options();
        options.inSampleSize = inSampleSize;
        Bitmap bitmap = BitmapFactory.decodeFile(path,options);
        return new BitmapDrawable(a.getResources(),bitmap);

    }

    public static void cleanImageView(ImageView imageView){
        if(!(imageView.getDrawable() instanceof BitmapDrawable)) return;

        ((BitmapDrawable) imageView.getDrawable()).getBitmap().recycle();
        imageView.setImageDrawable(null);
    }
}
