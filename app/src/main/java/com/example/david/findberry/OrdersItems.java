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
    private int price,quantity;
    private String deliverer,product;

    public OrdersItems(int price, int quantity, String deliverer, String product) {
        this.price = price;
        this.quantity = quantity;
        this.deliverer = deliverer;
        this.product = product;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDeliverer() {
        return deliverer;
    }

    public void setDeliverer(String deliverer) {
        this.deliverer = deliverer;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}

class OrdersItemsAdapter extends RecyclerView.Adapter<OrdersItemsAdapter.OrdersItemsViewHolder> {
    private List<OrdersItems> items;

    public OrdersItemsAdapter(List<OrdersItems> items) {
        this.items = items;
    }

    public static class OrdersItemsViewHolder extends RecyclerView.ViewHolder{

        public TextView deliverer;
        public TextView producto;
        public TextView precio;
        public TextView cantidad;

        public OrdersItemsViewHolder(View v)
        {
            super(v);
            deliverer = (TextView) v.findViewById(R.id.ocdeliverer);
            producto = (TextView) v.findViewById(R.id.ocproduct);
            precio   = (TextView) v.findViewById(R.id.ocprice);
            cantidad = (TextView) v.findViewById(R.id.ocquantity);
        }
    }

    @Override
    public OrdersItemsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_orders, viewGroup, false);
        return new OrdersItemsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(OrdersItemsViewHolder viewHolder, int i) {
        viewHolder.deliverer.setText(items.get(i).getDeliverer());
        viewHolder.producto.setText(items.get(i).getProduct());
        viewHolder.precio.setText("$"+String.valueOf(items.get(i).getPrice()));
        viewHolder.cantidad.setText(String.valueOf(items.get(i).getQuantity()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
