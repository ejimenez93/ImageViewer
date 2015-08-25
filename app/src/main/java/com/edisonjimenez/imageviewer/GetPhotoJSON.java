package com.edisonjimenez.imageviewer;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetPhotoJSON extends GetRawJSON {

    private String TAG = GetPhotoJSON.class.getSimpleName();
    private List<Photo> mPhotos;
    private Uri mDestinationUri;

    public GetPhotoJSON() {
        super(null);
        createAndUpdateUri();
        mPhotos = new ArrayList<Photo>();
    }

    public void execute() {
        super.setmRawURL(mDestinationUri.toString());
        DownloadJSONData downloadJSONData = new DownloadJSONData();
        Log.v(TAG, "Built URI = " + mDestinationUri.toString());
        downloadJSONData.execute(mDestinationUri.toString());
    }

    public boolean createAndUpdateUri() {
        final String API_BASE_URL = "http://www.edisonjimenez.com/crossway";

        mDestinationUri = Uri.parse(API_BASE_URL);

        return mDestinationUri != null;
    }

    public List<Photo> getPhotos() {
        return mPhotos;
    }

    public void processResult() {
        if (getmDownloadStatus() != DownloadStatus.OK) {
            Log.e(TAG, "Error downloading the raw data");
            return;
        }

        // All the fields that come back from the JSON object
        final String PHOTO_TITLE = "title";
        final String PHOTO_LINK = "link";
        final String PHOTO_ITEMS = "items";

        try {
            JSONObject jsonData = new JSONObject(getmData());
            JSONArray itemsArray = jsonData.getJSONArray(PHOTO_ITEMS);
            for (int x = 0; x < itemsArray.length(); x++) {
                JSONObject jsonPhoto = itemsArray.getJSONObject(x);
                String title = jsonPhoto.getString(PHOTO_TITLE);
                String link = jsonPhoto.getString(PHOTO_LINK);

                Photo photoObject = new Photo(title, link);

                this.mPhotos.add(photoObject);
            }

            for (Photo singlePhoto : mPhotos) {
                Log.v(TAG, singlePhoto.toString());
            }
        } catch (JSONException jsonError) {
            jsonError.printStackTrace();
            Log.e(TAG, "Error Processing JSON Data");
        }
    }

    public class DownloadJSONData extends DownloadRawData {

        protected void onPostExecute(String webData) {
            super.onPostExecute(webData);
            processResult();
        }

        protected String doInBackground(String... params) {
            String[] parameters = {
                    mDestinationUri.toString()
            };
            return super.doInBackground(parameters);
        }


    }

}

