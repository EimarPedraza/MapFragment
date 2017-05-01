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

public class RequestItems
{

    private int imagen;
    private String text;

    public RequestItems(int imagen, String text)
    {
        this.imagen = imagen;
        this.text = text;

    }


    public int getImagen() {
        return imagen;
    }

    public String getText() {
        return text;
    }
}

class RequestItemsAdapter extends RecyclerView.Adapter<RequestItemsAdapter.RequestItemsViewHolder> {
    private List<RequestItems> items;

    public static class RequestItemsViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public ImageView imagen;
        public TextView text;


        public RequestItemsViewHolder(View v) {
            super(v);
            imagen = (ImageView) v.findViewById(R.id.ivCardImage);
            text = (TextView) v.findViewById(R.id.ivCardName);
        }
    }

    public RequestItemsAdapter(List<RequestItems> items) {
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

    public static class RequestItemsViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public ImageView imagen;
        public TextView text;


        public RequestItemsViewHolder(View v) {
            super(v);
            imagen = (ImageView) v.findViewById(R.id.ivOpCardImage);
            text = (TextView) v.findViewById(R.id.tvCard);
        }
    }

    public RequestItemsAdapter2(List<RequestItems> items) {
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

    }
}