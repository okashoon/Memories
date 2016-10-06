package com.okasha.memories;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmed on 12-Aug-16.
 */
public class MemoryListFragment extends Fragment {
    private final String LOG = "MemoryListFragment log";

    private ArrayList<Memory> mMemories;
    MemoryAdapter adapter ;

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
        adapter = new MemoryAdapter(mMemories);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_memory_list,null);
        TextView addMemoryTextView =(TextView) v.findViewById(R.id.add_place_tv);

        addMemoryTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Memory m = new Memory();
                    mMemories.add(m);
                    Intent i = new Intent(getActivity(), MemoryPagerActivity.class);
                    i.putExtra(MemoryFragment.EXTRA_MEMORY_ID, m.getID());
                    startActivity(i);

            }
        });

        ListView memoriesListView = (ListView) v.findViewById(R.id.memories_list_view);


        memoriesListView.setAdapter(adapter);
        memoriesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Memory m = adapter.getItem(position);
                Intent i = new Intent(getActivity(), MemoryPagerActivity.class);
                i.putExtra(MemoryFragment.EXTRA_MEMORY_ID, m.getID());
                startActivity(i);
            }
        });
        return v;
    }



    //to update the list
    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
