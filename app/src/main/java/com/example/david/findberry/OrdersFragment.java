package com.example.david.findberry;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrdersFragment extends Fragment {


    public OrdersFragment() {
        // Required empty public constructor
    }

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_orders, container, false);
        //Inicializar opFood
        List<OrdersItems> list = new ArrayList<>();

        list.add(new OrdersItems(1800,1,"El varo","Pastel de pollo"));
        list.add(new OrdersItems(2000,2,"David","Brownie"));
        list.add(new OrdersItems(3100,1,"David","Pizza Hawaiana"));
        //obtener el recycler
        recyclerView = (RecyclerView) v.findViewById(R.id.foRecycler);
        recyclerView.setHasFixedSize(true);


        // Usar un administrador para LinearLayout
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);


        //Crear un nuevo adaptador
        adapter = new OrdersItemsAdapter(list);
        recyclerView.setAdapter(adapter);

        return v;


    }

}
