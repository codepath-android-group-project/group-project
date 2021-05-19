package com.example.animebook.adapters;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animebook.R;
import com.example.animebook.models.Review;

import java.util.List;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder> {
    Context context;
    List<Review> reviewsList;

    public ReviewsAdapter(Context context, List<Review> reviews) {

         this.context = context;
         this.reviewsList = reviews;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View reviewView = LayoutInflater.from(context).inflate(R.layout.anime_review_item, parent, false);

        return new ViewHolder(reviewView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Review review = reviewsList.get(position);
        holder.bind(review);
    }

    @Override
    public int getItemCount() {
        return reviewsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvUsername;
        TextView tvSummary;
        RatingBar rbRating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvSummary = itemView.findViewById(R.id.tvSummary);
            rbRating = itemView.findViewById(R.id.rbRating);

        }

        public void bind(Review review){
            tvUsername.setText(review.getUsername());
            tvSummary.setText(review.getSummary());
            rbRating.setRating(review.getRating());
        }
    }

    public void addAll(List<Review> reviews, boolean notify){
        Log.i("ReviewsAdapter", String.valueOf(reviews.size()));
        reviewsList.addAll(reviews);
//        Log.i("ReviewsAdapter", String.valueOf(reviewsList.size()));
        if(notify){
            notifyDataSetChanged();
        }
    }
}
