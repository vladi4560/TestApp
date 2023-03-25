package com.example.testapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChildReviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<UserReview> listItem;

    public ChildReviewAdapter() {
    }
    public ChildReviewAdapter(ArrayList<UserReview> list) {
        listItem=list;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_card, parent, false);
        return new ChildReviewAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        UserReview item = listItem.get(position);
        ChildReviewAdapter.MyViewHolder mvh = (ChildReviewAdapter.MyViewHolder) holder;
        mvh.setData(item);
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name,rate,review;
        public MyViewHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.name_TXT_userCard);
            rate = view.findViewById(R.id.rate_TXT_userCard);
            review = view.findViewById(R.id.review_TXT_userCard);
        }


        public void setData(UserReview item) {
            name.setText(item.getName());
            rate.setText(""+item.getRate());
            review.setText(item.getReview());
        }
    }
}
