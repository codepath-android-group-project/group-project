package com.example.animebook.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Anime {
    int animeID;
    String posterPath;
    String title;
    int episodes;
    String status;

    public Anime(JSONObject jsonObject) throws JSONException {
        this.animeID = jsonObject.getInt("id");
        this.posterPath = jsonObject.getJSONObject("coverImage").getString("large");
        this.title = jsonObject.getJSONObject("title").getString("english");
        this.episodes = jsonObject.getInt("episodes");
        this.status = jsonObject.getString("status");
    }


    public static List<Anime> fromJsonArray(JSONArray animeJsonArray) throws JSONException {
        List<Anime> animes = new ArrayList<>();
        for(int i = 0; i < animeJsonArray.length(); i++){
            animes.add(new Anime(animeJsonArray.getJSONObject(i)));
        }
        return animes;
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

    public String getEpisodes() {
        return String.valueOf(episodes) + " ep";
    }

    public String getStatus() {
        return status;
    }
}
