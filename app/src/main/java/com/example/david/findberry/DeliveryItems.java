package com.example.david.findberry;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

/**
 * Created by David on 1/05/2017.
 */

public class DeliveryItems {

    private String order;
    private String user;


    //Constructor
    public DeliveryItems(String order, String user) {
        this.order = order;
        this.user = user;
    }

    public String getOrder() {
        return order;
    }

    public String getUser() {
        return user;
    }

}

class DeliveryItemsAdapter extends RecyclerView.Adapter<DeliveryItemsAdapter.DeliveryItemsViewHolder> {
    private List<DeliveryItems> items;

    public DeliveryItemsAdapter(List<DeliveryItems> items) {
        this.items = items;
    }

    public static class DeliveryItemsViewHolder extends RecyclerView.ViewHolder{

        public TextView order;
        public TextView user;
        public Button quantity;

        public DeliveryItemsViewHolder(View v)
        {
            super(v);
            order = (TextView) v.findViewById(R.id.tvRequest);
            user   = (TextView) v.findViewById(R.id.tvUser);
            quantity = (Button) v.findViewById(R.id.bView);
        }
    }

    @Override
    public DeliveryItemsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_delivery, viewGroup, false);
        return new DeliveryItemsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DeliveryItemsViewHolder viewHolder, int i) {
        viewHolder.order.setText(items.get(i).getOrder());
        viewHolder.user.setText(items.get(i).getUser());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}