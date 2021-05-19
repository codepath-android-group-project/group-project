package com.example.animebook.models;

import android.text.Spanned;
import android.util.Log;

import androidx.core.text.HtmlCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DetailAnime {
    int animeID;
    String posterPath;
    String title;
    Spanned description;


    List<Review> reviews;
    List<String> genres;

    public DetailAnime(JSONObject detailAnime) throws JSONException {
        this.animeID = detailAnime.getInt("id");
        this.posterPath = detailAnime.getString("bannerImage");
        this.title = detailAnime.getJSONObject("title").getString("english");

        this.description = HtmlCompat.fromHtml(detailAnime.getString("description").replaceAll("<br><br />", "<br/>"), 0);
        this.genres = new ArrayList<String>();
        this.reviews = new ArrayList<Review>();

        JSONArray genreList = detailAnime.getJSONArray("genres");

        for (int i = 0; i < genreList.length(); i++) {
            this.genres.add(genreList.getString(i));
        }

        JSONArray reviewList = detailAnime.getJSONObject("reviews").getJSONArray("edges");
        this.reviews.addAll(Review.fromJsonArray(reviewList));


    }

    public int getAnimeID() {
        return animeID;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getTitle() {
        return title;
    }

    public Spanned getDescription() {
        Log.i("DeatilAnime", description.toString());
        return description;
    }

    public List<String> getGenres() {
        return genres;
    }

    public List<Review> getReviews() {
        return reviews;
    }
}
