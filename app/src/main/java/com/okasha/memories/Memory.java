package com.okasha.memories;

import java.util.Date;
import java.util.UUID;

/**
 * Created by ahmed on 11-Aug-16.
 */
public class Memory {
    private UUID mID;
    private String mTitle;
    private Date mDate;
    private boolean mStarred;

    public Memory(){
        mID = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getID() {
        return mID;
    }


    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isStarred() {
        return mStarred;
    }

    public void setStarred(boolean starred) {
        mStarred = starred;
    }

    @Override
    public String toString() {
        return mTitle;
    }
}
