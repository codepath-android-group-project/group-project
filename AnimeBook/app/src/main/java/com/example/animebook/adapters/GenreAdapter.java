package com.example.animebook.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animebook.R;
import com.example.animebook.models.Anime;

import java.util.List;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolder> {

    Context context;
    List<String> genres;

    public GenreAdapter( Context context, List<String> genreList) {
        this.context = context;
        this.genres = genreList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View genreView = LayoutInflater.from(context).inflate(R.layout.anime_genre_item, parent, false);

        return new ViewHolder(genreView);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreAdapter.ViewHolder holder, int position) {
        String genre = genres.get(position);

        holder.bind(genre);
    }

    @Override
    public int getItemCount() {
        return genres.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvGenre;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvGenre = itemView.findViewById(R.id.tvGenre);
        }

        public void bind(String genre){
            tvGenre.setText(genre);
        }
    }

    public void addAll(List<String> genreList){
        genres.addAll(genreList);

            notifyDataSetChanged();

    }
}
