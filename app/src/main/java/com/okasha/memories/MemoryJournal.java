package com.okasha.memories;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by ahmed on 11-Aug-16.
 */
public class MemoryJournal {

    private static MemoryJournal sMemoryJournal;
    private Context mAppContext;
    private ArrayList<Memory> mMemories;

    private MemoryJournal(Context appContext){
        mAppContext = appContext;
        mMemories = new ArrayList<Memory>();

    }


    //get an instance of the memoryjournal singleton
    public static MemoryJournal get(Context c){
        if(sMemoryJournal == null){
            sMemoryJournal = new MemoryJournal(c.getApplicationContext());
        }
        return sMemoryJournal;
    }

    //get memories arraylist
    public ArrayList<Memory> getMemories(){
        return mMemories;
    }

    //get a specific memory
    public Memory getMemory(UUID id){
        for (Memory m : mMemories){
            if (m.getID().equals(id)){
                return m;
            }
        }
        return null;
    }


}
