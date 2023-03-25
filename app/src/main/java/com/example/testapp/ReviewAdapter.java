package com.example.testapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Item> listItem;

    private Context context;
    private CallBackAddReview callBackAddReview;

    public ReviewAdapter() {}

    public ReviewAdapter(ArrayList<Item> list, Context context) {
        this.listItem = list;
        this.context = context;
    }

    public void setCallBackAddReview(CallBackAddReview callBackAddReview){
        this.callBackAddReview= callBackAddReview;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Item tempSong = listItem.get(position);
        MyViewHolder mvh = (MyViewHolder) holder;
        mvh.setData(tempSong);
//        if (listItem.get(position).getNumOfReviews() == 0)
//            return;

        ChildReviewAdapter childReviewAdapter = new ChildReviewAdapter(listItem.get(position).getReviewList());
        LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        ((MyViewHolder) holder).recyclerView.setAdapter(childReviewAdapter);
        ((MyViewHolder) holder).recyclerView.setLayoutManager(manager);
        boolean isExpandable = listItem.get(position).isExpandable();
        ((MyViewHolder) holder).recyclerView.setVisibility(isExpandable ? View.VISIBLE : View.GONE);
        //int i = position;
        mvh.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Item item = listItem.get(position);
                item.setExpandable(!item.isExpandable());
                notifyItemChanged(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listItem.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title, secondaryTitle, supportingText;
        private MaterialCardView cardView;
        private RecyclerView recyclerView;
       private ExtendedFloatingActionButton addReviewBtn;

        public MyViewHolder(@NonNull View view) {
            super(view);
            title = view.findViewById(R.id.title_TXT_card);
            secondaryTitle = view.findViewById(R.id.secondaryText_TXT_card);
            supportingText = view.findViewById(R.id.supportingText_TXT_card);
            cardView = view.findViewById(R.id.itemCard);
            recyclerView = view.findViewById(R.id.reviews_RYC_Card);
            addReviewBtn = view.findViewById(R.id.addReview_BTB_Card);
            addReviewBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callBackAddReview.openPopUpPage(getAdapterPosition());
                }
            });

        }


        public void setData(Item item) {
            title.setText(item.getItemName());
            secondaryTitle.setText("Rate" + String.format("%.1f", item.getOverAllRate()));
            supportingText.setText("Review" + item.getNumOfReviews() + "");
        }
    }
}
