package com.example.david.findberry;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    Button cancel;
    List<OrdersItems> list = new ArrayList<>();
    List<List<ProductItems>> plists = new ArrayList<>();
    List<ProductItems> list2 = new ArrayList<>();
    List<String> keylist = new ArrayList<>();
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String id;
    int lastp;
    AlertDialog.Builder builder1;
    AlertDialog alert11;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_orders, container, false);
        recyclerView2 = (RecyclerView) v.findViewById(R.id.foRecycler2);
        prod = (TextView)v.findViewById(R.id.tvtpr);
        pre = (TextView)v.findViewById(R.id.tvtp);
        qu = (TextView)v.findViewById(R.id.tvtQ);
        cancel = (Button)v.findViewById(R.id.bCancel);
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
                list2.clear();
                keylist.clear();
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    if(postSnapshot.child("uid").exists()&&
                            postSnapshot.child("lugar").exists()&&
                            postSnapshot.child("precio").exists()&&
                            postSnapshot.child("hora").exists()&&
                            postSnapshot.child("estado").exists()&&
                            postSnapshot.child("list").exists()){
                        if(id.equals(postSnapshot.child("uid").getValue().toString())){
                            keylist.add(postSnapshot.getKey());
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
                            list.add(new OrdersItems(estado,deliverer,lugar,precio,String.valueOf(tiempo)+"min"));
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
                        cancel.setVisibility(View.VISIBLE);

                        recyclerView2.setHasFixedSize(true);

                        // Usar un administrador para LinearLayout
                        layoutManager2 = new LinearLayoutManager(getContext());
                        recyclerView2.setLayoutManager(layoutManager2);
                        list2 = plists.get(position);
                        //Crear un nuevo adaptador
                        adapter2 = new ProductItems2Adapter(plists.get(position));
                        recyclerView2.setAdapter(adapter2);
                        lastp = position;

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                    }
                }));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder1 = new AlertDialog.Builder(getContext());
                builder1.setMessage("¿Está seguro que desea cancelar el pedido? Recuerda que esto podrá afectar tu puntaje de usuario.");
                builder1.setCancelable(true);
                ;
                builder1.setPositiveButton(
                        "Sí",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                databaseReference = firebaseDatabase.getReference("orders").child(keylist.get(lastp));
                                databaseReference.removeValue();
                                prod.setVisibility(View.GONE);
                                pre.setVisibility(View.GONE);
                                qu.setVisibility(View.GONE);
                                recyclerView2.setVisibility(View.GONE);
                                cancel.setVisibility(View.GONE);
                                Toast.makeText(getContext(),"El pedido fue cancelado",Toast.LENGTH_SHORT).show();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                alert11 = builder1.create();
                alert11.show();
            }
        });


        return v;


    }


}
