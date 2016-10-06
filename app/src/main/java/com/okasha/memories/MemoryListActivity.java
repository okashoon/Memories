package com.okasha.memories;

import android.support.v4.app.Fragment;

/**
 * Created by ahmed on 12-Aug-16.
 */
public class MemoryListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment getFragment(){
        return new MemoryListFragment();
    }



}

