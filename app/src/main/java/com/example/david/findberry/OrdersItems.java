package com.example.david.findberry;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by David on 1/05/2017.
 */

public class OrdersItems {
    private String status,deliverer,side,price,time;

    public OrdersItems(String status, String deliverer, String side, String price, String time) {
        this.status = status;
        this.deliverer = deliverer;
        this.side = side;
        this.price = price;
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public String getDeliverer() {
        return deliverer;
    }

    public String getSide() {
        return side;
    }

    public String getPrice() {
        return price;
    }

    public String getTime() {
        return time;
    }
}

class OrdersItemsAdapter extends RecyclerView.Adapter<OrdersItemsAdapter.OrdersItemsViewHolder> {
    private List<OrdersItems> items;

    public OrdersItemsAdapter(List<OrdersItems> items) {
        this.items = items;
    }

    public static class OrdersItemsViewHolder extends RecyclerView.ViewHolder{

        public TextView status;
        public TextView deliverer;
        public TextView side;
        public TextView price;
        public TextView time;

        public OrdersItemsViewHolder(View v)
        {
            super(v);
            status = (TextView) v.findViewById(R.id.ocstatus);
            deliverer = (TextView) v.findViewById(R.id.ocdeliverer);
            side = (TextView) v.findViewById(R.id.ocside);
            price   = (TextView) v.findViewById(R.id.ocprice);
            time = (TextView) v.findViewById(R.id.octime);
        }
    }

    @Override
    public OrdersItemsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_orders, viewGroup, false);
        return new OrdersItemsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(OrdersItemsViewHolder viewHolder, int i) {
        String price = "$"+items.get(i).getPrice();
        viewHolder.status.setText(items.get(i).getStatus());
        viewHolder.deliverer.setText(items.get(i).getDeliverer());
        viewHolder.side.setText(items.get(i).getSide());
        viewHolder.price.setText(price);
        viewHolder.time.setText(String.valueOf(items.get(i).getTime()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
