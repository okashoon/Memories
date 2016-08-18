package com.okasha.memories;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MemoryCameraActivity extends SingleFragmentActivity {

    @Override
    protected Fragment getFragment() {
        return new MemoryCameraFragment();
    }
}
