package com.example.david.findberry;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrdersFragment extends Fragment {


    public OrdersFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    RecyclerView recyclerView2;
    RecyclerView.Adapter adapter2;
    RecyclerView.LayoutManager layoutManager2;

    TextView prod,pre,qu;

    List<OrdersItems> list = new ArrayList<>();
    List<List<ProductItems>> plists = new ArrayList<>();
    List<ProductItems> list2 = new ArrayList<>();
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_orders, container, false);
        recyclerView2 = (RecyclerView) v.findViewById(R.id.foRecycler2);
        prod = (TextView)v.findViewById(R.id.tvtpr);
        pre = (TextView)v.findViewById(R.id.tvtp);
        qu = (TextView)v.findViewById(R.id.tvtQ);
        prod.setVisibility(View.GONE);
        pre.setVisibility(View.GONE);
        qu.setVisibility(View.GONE);
        //Inicializar opFood
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            id = user.getUid();
        }
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("orders");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                plists.clear();
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    if(postSnapshot.child("uid").exists()&&
                            postSnapshot.child("lugar").exists()&&
                            postSnapshot.child("precio").exists()&&
                            postSnapshot.child("hora").exists()&&
                            postSnapshot.child("estado").exists()&&
                            postSnapshot.child("list").exists()){
                        if(id.equals(postSnapshot.child("uid").getValue().toString())){
                            String estado = "Pendiente";
                            String deliverer = "Ninguno.";
                            String lugar = postSnapshot.child("lugar").getValue().toString();
                            String precio = postSnapshot.child("precio").getValue().toString();
                            long tiempo = Calendar.getInstance().getTimeInMillis()/60000-Long.parseLong(postSnapshot.child("hora").getValue().toString());
                            if(postSnapshot.child("estado").getValue().toString().equals("1")){
                                estado = "En camino";
                            }
                            if(postSnapshot.child("deliverer").exists()){
                                deliverer = postSnapshot.child("deliverer").getValue().toString();
                            }
                            list.add(new OrdersItems(estado,deliverer,lugar,precio,String.valueOf(tiempo)+"min."));
                            list2 = new ArrayList<>();
                            for(DataSnapshot snapshot: postSnapshot.child("list").getChildren()){
                                list2.add(new ProductItems(snapshot.child("producto").getValue().toString(),snapshot.child("precio").getValue().toString(),snapshot.child("cantidad").getValue().toString()));
                            }
                            plists.add(list2);
                        }

                    }
                }
                //obtener el recycler
                recyclerView = (RecyclerView) v.findViewById(R.id.foRecycler);
                recyclerView.setHasFixedSize(true);

                // Usar un administrador para LinearLayout
                layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);


                //Crear un nuevo adaptador
                adapter = new OrdersItemsAdapter(list);
                recyclerView.setAdapter(adapter);

                recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        prod.setVisibility(View.VISIBLE);
                        pre.setVisibility(View.VISIBLE);
                        qu.setVisibility(View.VISIBLE);
                        recyclerView2.setVisibility(View.VISIBLE);

                        recyclerView2.setHasFixedSize(true);

                        // Usar un administrador para LinearLayout
                        layoutManager2 = new LinearLayoutManager(getContext());
                        recyclerView2.setLayoutManager(layoutManager2);
                        list2 = plists.get(position);
                        //Crear un nuevo adaptador
                        adapter2 = new ProductItems2Adapter(plists.get(position));
                        recyclerView2.setAdapter(adapter2);

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        prod.setVisibility(View.GONE);
                        pre.setVisibility(View.GONE);
                        qu.setVisibility(View.GONE);
                        recyclerView2.setVisibility(View.GONE);
                    }
                }));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        return v;


    }

}
