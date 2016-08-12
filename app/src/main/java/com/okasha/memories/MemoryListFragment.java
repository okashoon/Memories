package com.okasha.memories;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmed on 12-Aug-16.
 */
public class MemoryListFragment extends ListFragment {
    private final String LOG = "MemoryListFragment log";

    private ArrayList<Memory> mMemories;

    //custom list adapter inner class
    private class MemoryAdapter extends ArrayAdapter<Memory>{
        public MemoryAdapter( ArrayList<Memory> memories) {
            super(getActivity(), 0, memories);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_memory,null);
            }
            Memory m = getItem(position);
            //setting title
            TextView titleTextView = (TextView)convertView.findViewById(R.id.memory_list_item_titleTextView);
            titleTextView.setText(m.getTitle());
            //setting date
            TextView dateTextView = (TextView)convertView.findViewById(R.id.memory_list_item_dateTextView);
            dateTextView.setText(m.getDate().toString());
            //setting starred
            CheckBox starredCheckBox = (CheckBox) convertView.findViewById(R.id.memory_list_item_starredCheckBox);
            starredCheckBox.setChecked(m.isStarred());

            return convertView;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.memories_title);
        MemoryJournal mj = MemoryJournal.get(getActivity());
        mMemories = mj.getMemories();
        MemoryAdapter adapter = new MemoryAdapter(mMemories);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Memory m = ((MemoryAdapter)getListAdapter()).getItem(position);
        Intent i = new Intent(getActivity(),MemoryActivity.class);
        i.putExtra(MemoryFragment.EXTRA_CRIME_ID,m.getID());
        startActivity(i);
    }
}
