package com.example.animebook.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animebook.DetailActivity;
import com.example.animebook.GlideApp;
import com.example.animebook.R;
import com.example.animebook.models.Anime;

import java.util.List;

public class AnimeListAdapter extends RecyclerView.Adapter<AnimeListAdapter.ViewHolder> {

    Context context;
    List<Anime> animes;

    public AnimeListAdapter(Context context, List<Anime> animes) {
        this.context = context;
        this.animes = animes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View animeListView = LayoutInflater.from(context).inflate(R.layout.anime_list_item, parent, false);

        return new ViewHolder(animeListView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Anime anime = animes.get(position);

        holder.bind(anime);
    }

    @Override
    public int getItemCount() {
        return  animes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout container;
        TextView tvTitle;
        TextView tvEpisodes;
        TextView tvStatus;
        ImageView ivCover;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvEpisodes = itemView.findViewById(R.id.tvEpisodes);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            ivCover  = itemView.findViewById(R.id.ivCover);
            container = itemView.findViewById(R.id.container);
        }

        public void bind(Anime anime) {
            tvTitle.setText(anime.getTitle());
            tvEpisodes.setText(anime.getEpisodes());
            tvStatus.setText(anime.getStatus());


            GlideApp.with(context).load(anime.getPosterPath()).centerCrop().into(ivCover);

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, DetailActivity.class);
                    i.putExtra("ID", anime.getAnimeID());
                    context.startActivity(i);
                }
            });
        }
    }

    public void addAll(List<Anime> animeList, boolean notify){
        animes.addAll(animeList);
        if(notify) {
            notifyDataSetChanged();
        }
    }

}
