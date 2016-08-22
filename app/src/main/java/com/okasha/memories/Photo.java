package com.okasha.memories;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ahmed on 22-Aug-16.
 */
public class Photo  {
    public static final String JSON_FILENAME = "filename";
    String mFilename;

    public Photo (String filename){
        mFilename = filename;
    }

    public Photo (JSONObject json) throws JSONException{
        mFilename = json.getString(JSON_FILENAME);
    }

    public JSONObject toJson() throws JSONException{
        JSONObject json = new JSONObject();
        json.put(JSON_FILENAME,mFilename);
        return json;
    }

    public String getFilename() {
        return mFilename;
    }


}
