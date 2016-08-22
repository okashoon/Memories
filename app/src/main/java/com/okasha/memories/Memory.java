package com.okasha.memories;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.UUID;

/**
 * Created by ahmed on 11-Aug-16.
 */
public class Memory {
    public static final String JSON_PHOTO = "photo";
    private UUID mID;
    private String mTitle;
    private Date mDate;
    private boolean mStarred;
    private Photo mPhoto;

    public Memory(){
        mID = UUID.randomUUID();
        mDate = new Date();
    }

    public Memory(JSONObject json) throws JSONException{
        if(json.has(Photo.JSON_FILENAME)) {
            mPhoto = new Photo(json.getJSONObject(Photo.JSON_FILENAME));
        }
    }

    public JSONObject toJSON() throws JSONException{
        JSONObject json = new JSONObject();
        if(json.has(JSON_PHOTO)) {
            json.put(JSON_PHOTO, mPhoto.toJson());
        }
        return json;
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

    public Photo getPhoto() {
        return mPhoto;
    }

    public void setPhoto(Photo photo) {
        mPhoto = photo;
    }

    @Override
    public String toString() {
        return mTitle;
    }
}
