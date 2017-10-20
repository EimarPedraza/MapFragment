package com.example.david.findberry;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by David on 5/06/2017.
 */

public class TopUsers {
    private String user;
    private Double score;

    public TopUsers(String user, Double score) {
        this.user = user;
        this.score = score;
    }

    public String getUser() {
        return user;
    }

    public Double getScore() {
        return score;
    }
}

class TopUsersAdapter extends RecyclerView.Adapter<TopUsersAdapter.TopUsersViewHolder> {
    private List<TopUsers> items;

    public TopUsersAdapter(List<TopUsers> items) {
        this.items = items;
    }

    public static class TopUsersViewHolder extends RecyclerView.ViewHolder{

        public TextView id;
        public TextView user;
        public ImageView h1;
        public ImageView h2;
        public ImageView h3;
        public ImageView h4;
        public ImageView h5;

        public TopUsersViewHolder(View v)
        {
            super(v);
            id = (TextView) v.findViewById(R.id.num);
            user   = (TextView) v.findViewById(R.id.name);
            h1 = (ImageView)v.findViewById(R.id.h1);
            h2 = (ImageView)v.findViewById(R.id.h2);
            h3 = (ImageView)v.findViewById(R.id.h3);
            h4 = (ImageView)v.findViewById(R.id.h4);
            h5 = (ImageView)v.findViewById(R.id.h5);
        }
    }

    @Override
    public TopUsersViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_top, viewGroup, false);
        return new TopUsersViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TopUsersViewHolder viewHolder, int i) {;
        viewHolder.id.setText(String.valueOf(i+1));
        viewHolder.user.setText(items.get(i).getUser());
        Double score = items.get(i).getScore();
        if(score > 4.5){
            viewHolder.h1.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h2.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h3.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h4.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h5.setImageResource(R.drawable.ic_favorite_black_24dp);
        }
        else if(score > 4.0){
            viewHolder.h1.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h2.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h3.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h4.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h5.setImageResource(R.drawable.ic_favorite_border_black_24dp);
        }
        else if(score > 3.5){
            viewHolder.h1.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h2.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h3.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h4.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h5.setVisibility(View.INVISIBLE);
        }
        else if(score > 3.0){
            viewHolder.h1.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h2.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h3.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h4.setImageResource(R.drawable.ic_favorite_border_black_24dp);
            viewHolder.h5.setVisibility(View.INVISIBLE);
        }
        else if(score > 2.5){
            viewHolder.h1.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h2.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h3.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h4.setVisibility(View.INVISIBLE);
            viewHolder.h5.setVisibility(View.INVISIBLE);
        }
        else if(score > 2.0){
            viewHolder.h1.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h2.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h3.setImageResource(R.drawable.ic_favorite_border_black_24dp);
            viewHolder.h4.setVisibility(View.INVISIBLE);
            viewHolder.h5.setVisibility(View.INVISIBLE);
        }
        else if(score > 1.5){
            viewHolder.h1.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h2.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h3.setVisibility(View.INVISIBLE);
            viewHolder.h4.setVisibility(View.INVISIBLE);
            viewHolder.h5.setVisibility(View.INVISIBLE);
        }
        else if(score > 1.0){
            viewHolder.h1.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h2.setImageResource(R.drawable.ic_favorite_border_black_24dp);
            viewHolder.h3.setVisibility(View.INVISIBLE);
            viewHolder.h4.setVisibility(View.INVISIBLE);
            viewHolder.h5.setVisibility(View.INVISIBLE);
        }
        else {
            viewHolder.h1.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h2.setVisibility(View.INVISIBLE);
            viewHolder.h3.setVisibility(View.INVISIBLE);
            viewHolder.h4.setVisibility(View.INVISIBLE);
            viewHolder.h5.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}