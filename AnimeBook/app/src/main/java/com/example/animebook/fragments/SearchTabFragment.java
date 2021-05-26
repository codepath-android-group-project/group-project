package com.example.animebook.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.RequestHeaders;
import com.codepath.asynchttpclient.RequestParams;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.animebook.EndlessRecyclerViewScrollListener;
import com.example.animebook.R;
import com.example.animebook.adapters.SearchTabAdapter;
import com.example.animebook.models.Anime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.Headers;

import static com.example.animebook.MainActivity.search_input;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchTabFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchTabFragment extends Fragment {

    public static final String TAG = "SearchTabFragment";
    public static final String Url = "https://graphql.anilist.co";
    private AsyncHttpClient client;
    private RecyclerView rvAnimes;
    private EndlessRecyclerViewScrollListener scrollListener;
    protected SearchTabAdapter searchTabAdapter;
    protected ArrayList<Anime> allAnimes;

    public static final String query = "query ( $page: Int, $perPage: Int, $search: String) { # Define which variables will be used in the query (id)\n" +
            " Page(page: $page, perPage: $perPage) {\n" +
            "    media(search: $search, type: ANIME, isAdult: false, sort: TRENDING_DESC    ) {\n" +
            "      id\n" +
            "      title{\n" +
            "        english\n" +
            "        romaji\n" +
            "      }\n" +
            "      status\n" +
            "      episodes\n" +
            "      coverImage{\n" +
            "        path: extraLarge\n" +
            "      }\n" +
            "      nextAiringEpisode{\n" +
            "        episode\n" +
            "      }\n" +
            "    }\n" +
            "  }\n"+
            "}";

    public SearchTabFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_tab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        client = new AsyncHttpClient();

        rvAnimes = view.findViewById(R.id.rvAnimes);
        allAnimes = new ArrayList<Anime>();
        searchTabAdapter = new SearchTabAdapter(getContext(), allAnimes);

        rvAnimes.setAdapter(searchTabAdapter);

        // LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        rvAnimes.setLayoutManager(gridLayoutManager);

        scrollListener = new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                Log.i(TAG, String.valueOf(page));
                int currentSize = searchTabAdapter.getItemCount();
                loadDataFromApi(page + 1, false);
                view.post(new Runnable() {
                    @Override
                    public void run() {
                        searchTabAdapter.notifyItemRangeInserted(currentSize, allAnimes.size() - 1);
                    }
                });
            }
        };

        rvAnimes.addOnScrollListener(scrollListener);

        loadDataFromApi(null, true);

        Log.i(TAG, String.valueOf(searchTabAdapter.getItemCount()));
    }


    private void loadDataFromApi(Integer page, boolean notify){
        try {
            if(page == null){
                page = 1;
            }
            JSONObject variables = new JSONObject()
                    .put("page", page)
                    .put("perPage", 50)
                    .put("search", search_input);

            sendRequest(query, variables, notify);


        } catch (JSONException e){
            Log.e(TAG, e.toString());
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


                        JSONArray results = jsonObject.getJSONObject("data").getJSONObject("Page").getJSONArray("media");

                        searchTabAdapter.addAll(Anime.fromJsonArray(results), notify);

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


}