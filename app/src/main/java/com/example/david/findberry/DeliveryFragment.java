package com.example.david.findberry;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeliveryFragment extends Fragment {


    public DeliveryFragment() {
        // Required empty public constructor
    }


    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    TextView tvProduct,tvProductt,tvSide,tvSidet,tvPrice,tvPricet,tvRoutet,tvUser,tvUsert;
    Button bRoute;
    int pos;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delivery, container, false);
        tvUser = (TextView)view.findViewById(R.id.tvUser);
        tvUsert = (TextView)view.findViewById(R.id.tvUsert);
        tvProduct = (TextView)view.findViewById(R.id.tvProduct);
        tvProductt = (TextView)view.findViewById(R.id.tvProductt);
        tvSide = (TextView)view.findViewById(R.id.tvSide);
        tvSidet = (TextView)view.findViewById(R.id.tvSidet);
        tvPrice = (TextView)view.findViewById(R.id.tvPrice);
        tvPricet = (TextView)view.findViewById(R.id.tvPricet);
        tvRoutet = (TextView)view.findViewById(R.id.tvRoutet);
        bRoute = (Button)view.findViewById(R.id.bRoute);

        List<DeliveryItems> deliveryItemsList = new ArrayList<>();

        deliveryItemsList.add(new DeliveryItems("La miguería","El Varo"));
        deliveryItemsList.add(new DeliveryItems("Arbóreo","David"));


        recyclerView = (RecyclerView) view.findViewById(R.id.fdRecycler);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new DeliveryItemsAdapter(deliveryItemsList);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                tvUser.setVisibility(View.VISIBLE);
                tvUsert.setVisibility(View.VISIBLE);
                tvProduct.setVisibility(View.VISIBLE);
                tvProductt.setVisibility(View.VISIBLE);
                tvSide.setVisibility(View.VISIBLE);
                tvSidet.setVisibility(View.VISIBLE);
                tvPrice.setVisibility(View.VISIBLE);
                tvPricet.setVisibility(View.VISIBLE);
                tvRoutet.setVisibility(View.VISIBLE);
                bRoute.setVisibility(View.VISIBLE);
                pos = position;
                switch (position) {
                    case 0:
                        tvUser.setText("El Varo");
                        tvProduct.setText("Pan de chocolate");
                        tvSide.setText("La miguería");
                        tvPrice.setText("$9.000");
                        break;
                    case 1:
                        tvUser.setText("David");
                        tvProduct.setText("Sandwich");
                        tvSide.setText("Arbóreo");
                        tvPrice.setText("$7.000");
                        break;
                }
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
        return view;
    }

}
