package com.okasha.memories;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by ahmed on 12-Aug-16.
 */
public class MemoryListFragment extends ListFragment {

    private ArrayList<Memory> mMemories;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.memories_title);
        MemoryJournal mj = MemoryJournal.get(getActivity());
        mMemories = mj.getMemories();
        ArrayAdapter<Memory> adapter = new ArrayAdapter<Memory>(getActivity(),android.R.layout.simple_list_item_1,mMemories);
        setListAdapter(adapter);
    }
}
