package com.example.animebook.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.RequestHeaders;
import com.codepath.asynchttpclient.RequestParams;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.animebook.R;
import com.facebook.stetho.json.annotation.JsonProperty;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.BufferedSink;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AnimeListFragment} factory method to
 * create an instance of this fragment.
 */
public class AnimeListFragment extends Fragment {

    public static final String TAG = "AnimeListFragment";

    public static final String Url = "https://graphql.anilist.co";
    private AsyncHttpClient client;
    private Button btnMakeRequest;

    public AnimeListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_anime_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        client = new AsyncHttpClient();

        btnMakeRequest = view.findViewById(R.id.btnMakeRequest);

        btnMakeRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    sendRequest();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void sendRequest() throws JSONException {
        String query = "query ($genre: String) { # Define which variables will be used in the query (id)\n" +
                "  Media (genre: $genre, type: ANIME) { # Insert our variables into the query arguments (id) (type: ANIME is hard-coded in the query)\n" +
                "    id\n" +
                "    title {\n" +
                "      romaji\n" +
                "      native\n" +
                "    }\n" +
                "genres\n" +
                "  }\n" +
                "}\n";

        JSONObject variables = new JSONObject().put("genre", "Comedy");


        JSONObject body = new JSONObject().put("query", query).put("variables", variables);


        Log.i(TAG, body.toString(3));

        RequestHeaders requestHeaders = new RequestHeaders();
        requestHeaders.put("Content-Type", "application/json");
        requestHeaders.put("Accept", "application/json");

        RequestParams params = new RequestParams();

        client.post(Url, requestHeaders, params, body.toString(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                Log.i(TAG, json.toString());
            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                Log.i(TAG, "request failed: " + s);
            }
        });
    }
}