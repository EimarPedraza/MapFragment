package com.example.david.findberry;


import android.content.Intent;
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
public class RequestFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    public RequestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_request, container, false);

        List<RequestItems> requestItemsList = new ArrayList<>();

        requestItemsList.add(new RequestItems(R.drawable.food,getString(R.string.foodmenu)));
        requestItemsList.add(new RequestItems(R.drawable.acad,getString(R.string.acadmenu)));
        requestItemsList.add(new RequestItems(R.drawable.ocio,getString(R.string.ociomenu)));

        recyclerView = (RecyclerView) view.findViewById(R.id.rfRecycler);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RequestItemsAdapter(requestItemsList);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(),SelectActivity.class);
                switch (position) {
                    case 0:
                        intent.putExtra("op","food");
                        startActivity(intent);
                        break;
                    case 1:
                        intent.putExtra("op","acad");
                        startActivity(intent);
                        break;
                    case 2:
                        intent.putExtra("op","ocio");
                        startActivity(intent);
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
