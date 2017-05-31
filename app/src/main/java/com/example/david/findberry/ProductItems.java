package com.example.david.findberry;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

/**
 * Created by David on 1/05/2017.
 */

public class ProductItems {

    private String product;
    private String price;
    private String quantity;


    //Constructor
    public ProductItems(String product, String price, String quantity) {
        this.product = product;
        this.price = price;
        this.quantity = quantity;
    }

    public String getProduct() {
        return product;
    }

    public String getPrice() {
        return price;
    }

    public String getQuantity() { return quantity; }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}

class ProductItemsAdapter extends RecyclerView.Adapter<ProductItemsAdapter.ProductItemsViewHolder> {
    private List<ProductItems> items;

    public ProductItemsAdapter(List<ProductItems> items) {
        this.items = items;
    }

    public static class ProductItemsViewHolder extends RecyclerView.ViewHolder{

        public TextView product;
        public TextView price;
        public EditText quantity;

        public ProductItemsViewHolder(View v)
        {
            super(v);
            product = (TextView) v.findViewById(R.id.tvProduct);
            price   = (TextView) v.findViewById(R.id.tvPrice);
            quantity = (EditText) v.findViewById(R.id.etQuantity);
        }
    }

    @Override
    public ProductItemsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_product, viewGroup, false);
        return new ProductItemsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ProductItemsViewHolder viewHolder,int i) {
        String price = "$"+items.get(i).getPrice();
        viewHolder.product.setText(items.get(i).getProduct());
        viewHolder.price.setText(price);
        viewHolder.quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int position = viewHolder.getAdapterPosition();
                if(s.toString().equals("")){
                    items.get(position).setQuantity("0");
                }else if(Integer.parseInt(s.toString())>9){
                    viewHolder.quantity.setText("9");
                    items.get(position).setQuantity("9");
                }else {
                    items.get(position).setQuantity(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @Override
    public int getItemCount() {
        return items.size();
    }
}

class ProductItems2Adapter extends RecyclerView.Adapter<ProductItems2Adapter.ProductItems2ViewHolder> {
    private List<ProductItems> items;

    public ProductItems2Adapter(List<ProductItems> items) {
        this.items = items;
    }

    public static class ProductItems2ViewHolder extends RecyclerView.ViewHolder{

        public TextView product;
        public TextView price;
        public TextView quantity;

        public ProductItems2ViewHolder(View v)
        {
            super(v);
            product = (TextView) v.findViewById(R.id.tvProduct);
            price   = (TextView) v.findViewById(R.id.tvPrice);
            quantity = (TextView) v.findViewById(R.id.tvQuantity);
        }
    }

    @Override
    public ProductItems2ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_product2, viewGroup, false);
        return new ProductItems2ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ProductItems2ViewHolder viewHolder,int i) {
        String price = "$"+items.get(i).getPrice();
        viewHolder.product.setText(items.get(i).getProduct());
        viewHolder.price.setText(price);
        viewHolder.quantity.setText(items.get(i).getQuantity());
    }


    @Override
    public int getItemCount() {
        return items.size();
    }
}