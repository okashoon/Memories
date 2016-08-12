package com.okasha.memories;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;

public class MemoryActivity extends SingleFragmentActivity {

    @Override
    protected Fragment getFragment() {
        return new MemoryFragment();
    }
}
