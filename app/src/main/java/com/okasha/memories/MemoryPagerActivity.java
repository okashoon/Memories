package com.okasha.memories;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

public class MemoryPagerActivity extends AppCompatActivity {

    private final String LOG = "MemoryPagerACtivity";
    private ViewPager mViewPager;
    private ArrayList<Memory> mMemories;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);

        FragmentManager fm = getSupportFragmentManager();
        //get the memories arraylist
        mMemories = MemoryJournal.get(this).getMemories();



        FragmentStatePagerAdapter pagerAdapter = new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
              Memory m = mMemories.get(position);
                //return MemoryFragment instance with the memory id in position
              return MemoryFragment.newInstance(m.getID());

            }

            @Override
            public int getCount() {
                return mMemories.size();
            }


        };



        mViewPager.setAdapter(pagerAdapter);

        //set the actionbar title to the memory selected
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setTitle(mMemories.get(position).getTitle());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        
        //set the current item in pagerview according to id sent through intent
        UUID id =(UUID) getIntent().getSerializableExtra(MemoryFragment.EXTRA_MEMORY_ID);
        for (Memory m : mMemories){
            if (m.getID().equals(id)){
                mViewPager.setCurrentItem(mMemories.indexOf(m));
                break;
            }
        }


    }

}
