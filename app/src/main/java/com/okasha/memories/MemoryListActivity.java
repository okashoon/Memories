package com.okasha.memories;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;

public class MemoryListActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .add(R.id.list_fragment_container,new MemoryListFragment() )
                .commit();
    }
}
