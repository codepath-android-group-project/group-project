package com.example.animebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.RequestHeaders;
import com.codepath.asynchttpclient.RequestParams;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.animebook.adapters.AnimeListAdapter;
import com.example.animebook.adapters.GenreAdapter;
import com.example.animebook.adapters.ReviewsAdapter;
import com.example.animebook.models.DetailAnime;
import com.example.animebook.models.Review;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class DetailActivity extends AppCompatActivity {
    public static final String TAG = "DetailActivity";
    public static final String Url = "https://graphql.anilist.co";
    public static final String ARG_ID = "argID";

    private int animeID;
    private AsyncHttpClient client;
    protected AnimeListAdapter animeListAdapter;
    private RecyclerView rvReviews;
    private RecyclerView rvGenres;
    private List<String> genres;
    private List<Review> reviews;
    private ImageView ivPoster;
    private TextView tvTitle;
    private TextView tvDescription;
    private GenreAdapter genreAdapter;
    private ReviewsAdapter reviewsAdapter;

    public static final String query = "query ( $id: Int) {\n" +
            "  Page(page: 1, perPage: 1) {\n" +
            "    media(id: $id) {\n" +
            "      id\n" +
            "      title {\n" +
            "        english\n" +
            "        romaji\n" +
            "      }\n" +
            "      description(asHtml: true)\n" +
            "      genres\n" +
            "      bannerImage\n" +
            "      reviews(sort: UPDATED_AT_DESC, page: 1, perPage: 8) {\n" +
            "        edges {\n" +
            "          node {\n" +
            "            id\n" +
            "            score\n" +
            "            summary\n" +
            "            user {\n" +
            "              id\n" +
            "              name\n" +
            "            }\n" +
            "          }\n" +
            "        }\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        animeID = getIntent().getIntExtra("ID", 0);
        genres = new ArrayList<String>();
        reviews = new ArrayList<Review>();

        client = new AsyncHttpClient();
        rvGenres = findViewById(R.id.rvGenres);
        rvReviews = findViewById(R.id.rvReviews);
        ivPoster = findViewById(R.id.ivPoster);
        tvTitle  = findViewById(R.id.tvTitle);
        tvDescription = findViewById(R.id.tvDescription);

        genreAdapter = new GenreAdapter(this, genres);

        rvGenres.setAdapter(genreAdapter);

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvGenres.setLayoutManager(horizontalLayoutManager);

        reviewsAdapter = new ReviewsAdapter(this, reviews);
        rvReviews.setAdapter(reviewsAdapter);
        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(this);
        rvReviews.setLayoutManager(verticalLayoutManager);


        try {
            JSONObject variables = new JSONObject().put("id", animeID);
            Log.i(TAG, variables.toString(1));
            sendRequest(query, variables, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public void sendRequest(String query, JSONObject variables, boolean notify){
        try{
            JSONObject body = new JSONObject().put("query", query).put("variables", variables);

            RequestHeaders requestHeaders = new RequestHeaders();
            requestHeaders.put("Content-Type", "application/json");
            requestHeaders.put("Accept", "application/json");

            RequestParams params = new RequestParams();

            client.post(Url, requestHeaders, params, body.toString(), new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int i, Headers headers, JSON json) {
//                Log.i(TAG, json.toString());
                    try {

                        JSONObject jsonObject = json.jsonObject;
//                        Log.i(TAG, jsonObject.toString());
                        JSONObject results = jsonObject.getJSONObject("data").getJSONObject("Page").getJSONArray("media").getJSONObject(0);
                        Log.i(TAG, results.toString(2));
                        DetailAnime detailedAnime= new DetailAnime(results);
                        bindDetails(detailedAnime);
                        genreAdapter.addAll(detailedAnime.getGenres());
                        reviewsAdapter.addAll(detailedAnime.getReviews(), true);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                    Log.i(TAG, "request failed: " + s);
                }
            });
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void bindDetails(DetailAnime detailedAnime){
        getSupportActionBar().setTitle(detailedAnime.getTitle());
        tvTitle.setText(detailedAnime.getTitle());
        tvDescription.setText(detailedAnime.getDescription());
        GlideApp.with(this).load(detailedAnime.getPosterPath()).centerCrop().into(ivPoster);


    }
}