package com.example.animebook.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Review {
    private String username;
    private String summary;
    private int rating;
    private int id;

    public Review(JSONObject jsonObject) throws JSONException{
        this.username = jsonObject.getJSONObject("node").getJSONObject("user").getString("name");
        this.summary = jsonObject.getJSONObject("node").getString("summary");
        this.rating = jsonObject.getJSONObject("node").getInt("score");
        this.id = jsonObject.getJSONObject("node").getInt("id");
    }

    public static List<Review> fromJsonArray(JSONArray jsonArray) throws JSONException{
        List<Review> reviews = new ArrayList<Review>();
        Log.i("Review", String.valueOf(reviews.size()));
        for(int i = 0; i < jsonArray.length(); i++){
            reviews.add(new Review(jsonArray.getJSONObject(i)));
        }

        return reviews;
    }

    public String getUsername() {
        return username;
    }

    public String getSummary() {
        return summary;
    }

    public float getRating() {
        return (float)((rating / 100.0) * 5) ;
    }

    public int getId() {
        return id;
    }
}
