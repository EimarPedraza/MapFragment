package com.example.david.findberry;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by David on 30/04/2017.
 */

class RequestItems
{

    private int imagen;
    private String text;
    private double score;

    RequestItems(int imagen, String text,double score)
    {
        this.imagen = imagen;
        this.text = text;
        this.score = score;

    }


    int getImagen() {
        return imagen;
    }

    public String getText() {
        return text;
    }

    double getScore() {
        return score;
    }
}

class RequestItemsAdapter extends RecyclerView.Adapter<RequestItemsAdapter.RequestItemsViewHolder> {
    private List<RequestItems> items;

    static class RequestItemsViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        ImageView imagen;
        public TextView text;


        RequestItemsViewHolder(View v) {
            super(v);
            imagen = (ImageView) v.findViewById(R.id.ivCardImage);
            text = (TextView) v.findViewById(R.id.ivCardName);
        }
    }

    RequestItemsAdapter(List<RequestItems> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public RequestItemsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_request, viewGroup, false);
        return new RequestItemsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RequestItemsViewHolder viewHolder, int i) {
        viewHolder.imagen.setImageResource(items.get(i).getImagen());
        viewHolder.text.setText(items.get(i).getText());
    }

}

class RequestItemsAdapter2 extends RecyclerView.Adapter<RequestItemsAdapter2.RequestItemsViewHolder> {
    private List<RequestItems> items;

    static class RequestItemsViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        ImageView imagen;
        public TextView text;
        ImageView h1;
        ImageView h2;
        ImageView h3;
        ImageView h4;
        ImageView h5;


        RequestItemsViewHolder(View v) {
            super(v);
            imagen = (ImageView) v.findViewById(R.id.ivOpCardImage);
            text = (TextView) v.findViewById(R.id.tvCard);
            h1 = (ImageView) v.findViewById(R.id.h1);
            h2 = (ImageView) v.findViewById(R.id.h2);
            h3 = (ImageView) v.findViewById(R.id.h3);
            h4 = (ImageView) v.findViewById(R.id.h4);
            h5 = (ImageView) v.findViewById(R.id.h5);
        }
    }

    RequestItemsAdapter2(List<RequestItems> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public RequestItemsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_select, viewGroup, false);
        return new RequestItemsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RequestItemsViewHolder viewHolder, int i) {
        viewHolder.imagen.setImageResource(items.get(i).getImagen());
        viewHolder.text.setText(items.get(i).getText());

        viewHolder.h1.setVisibility(View.VISIBLE);
        viewHolder.h2.setVisibility(View.VISIBLE);
        viewHolder.h3.setVisibility(View.VISIBLE);
        viewHolder.h4.setVisibility(View.VISIBLE);
        viewHolder.h5.setVisibility(View.VISIBLE);
        if(items.get(i).getScore() > 4.5){
            viewHolder.h1.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h2.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h3.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h4.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h5.setImageResource(R.drawable.ic_favorite_black_24dp);
        }
        else if(items.get(i).getScore() > 4.0){
            viewHolder.h1.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h2.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h3.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h4.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h5.setImageResource(R.drawable.ic_favorite_border_black_24dp);
        }
        else if(items.get(i).getScore() > 3.5){
            viewHolder.h1.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h2.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h3.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h4.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h5.setVisibility(View.INVISIBLE);
        }
        else if(items.get(i).getScore() > 3.0){
            viewHolder.h1.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h2.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h3.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h4.setImageResource(R.drawable.ic_favorite_border_black_24dp);
            viewHolder.h5.setVisibility(View.INVISIBLE);
        }
        else if(items.get(i).getScore() > 2.5){
            viewHolder.h1.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h2.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h3.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h4.setVisibility(View.INVISIBLE);
            viewHolder.h5.setVisibility(View.INVISIBLE);
        }
        else if(items.get(i).getScore() > 2.0){
            viewHolder.h1.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h2.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h3.setImageResource(R.drawable.ic_favorite_border_black_24dp);
            viewHolder.h4.setVisibility(View.INVISIBLE);
            viewHolder.h5.setVisibility(View.INVISIBLE);
        }
        else if(items.get(i).getScore() > 1.5){
            viewHolder.h1.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h2.setImageResource(R.drawable.ic_favorite_black_24dp);
            viewHolder.h3.setVisibility(View.INVISIBLE);
            viewHolder.h4.setVisibility(View.INVISIBLE);
            viewHolder.h5.setVisibility(View.INVISIBLE);
        }
        else if(items.get(i).getScore() > 1.0){
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
}