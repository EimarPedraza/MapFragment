package com.example.david.findberry;


import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeliveryFragment extends Fragment {


    public DeliveryFragment() {
        // Required empty public constructor
    }


    RecyclerView recyclerView,recyclerView2;
    RecyclerView.Adapter adapter,adapter2;
    RecyclerView.LayoutManager layoutManager,layoutManager2;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,databaseReference2;

    TextView tvDUser,delivInfo,sideDesc,reqDesc;
    Button accept;
    ScrollView scrollView;
    LinearLayout layout;
    List<DeliveryItems> deliveryItemsList = new ArrayList<>();
    List<String> sidedescl = new ArrayList<>();
    List<String> reqdescl = new ArrayList<>();
    List<ProductItems> productItemsList = new ArrayList<>();
    List<List<ProductItems>> plist = new ArrayList<>();
    List<String> keys = new ArrayList<>();
    String id,dname,uname,mykey="1",uid;
    AlertDialog alert11;
    AlertDialog.Builder builder;
    int lastp,myd=0,flagev=0;



    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_delivery, container, false);
        //Toast.makeText(getContext(),R.string.deliverydesc,Toast.LENGTH_LONG).show();
        tvDUser = (TextView)view.findViewById(R.id.dnameid);
        scrollView = (ScrollView)view.findViewById(R.id.fdScroll);
        delivInfo = (TextView)view.findViewById(R.id.delivInfo);
        sideDesc = (TextView)view.findViewById(R.id.sideDesc);
        reqDesc = (TextView)view.findViewById(R.id.reqDesc);
        recyclerView = (RecyclerView) view.findViewById(R.id.fdRecycler);
        recyclerView2 = (RecyclerView) view.findViewById(R.id.fdRecycler2);
        layout = (LinearLayout)view.findViewById(R.id.llTitle);
        accept = (Button)view.findViewById(R.id.bAccept);


        ImageView profilePhoto = (ImageView)view.findViewById(R.id.ivPhoto);

        firebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            id = user.getUid();
            Uri photoUrl = user.getPhotoUrl();
            Picasso.with(getContext()).load(photoUrl).into(profilePhoto);
        }else {
            logOut();
        }

        databaseReference = firebaseDatabase.getReference("orders");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                deliveryItemsList.clear();
                plist.clear();
                productItemsList.clear();
                keys.clear();
                myd = 0;
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    if(snapshot.child("deliv").exists()&&
                            snapshot.child("uid").exists()&&
                            snapshot.child("myid").exists()){
                        if(snapshot.child("myid").getValue().toString().equals(id)&&flagev==0){
                            flagev = 1;
                            Intent intent = new Intent(getActivity(),EvaluateActivity.class);
                            intent.putExtra("uid",snapshot.child("uid").getValue().toString());
                            intent.putExtra("key",snapshot.getKey());
                            intent.putExtra("myid",id);
                            intent.putExtra("eval","user");
                            startActivity(intent);
                        }
                    }
                    if(snapshot.child("hora").exists()&&
                            snapshot.child("lugar").exists()&&
                            snapshot.child("uname").exists()&&
                            snapshot.child("ulugar").exists()&&
                            snapshot.child("desc").exists()&&
                            !snapshot.child("uid").getValue().toString().equals(id)&&
                            (!snapshot.child("delivid").exists()||snapshot.child("delivid").getValue().toString().equals(id))){
                        keys.add(snapshot.getKey());
                        if(snapshot.child("delivid").exists()){
                            if(snapshot.child("delivid").getValue().toString().equals(id)){
                                myd++;
                                uid = snapshot.child("uid").getValue().toString();
                                mykey = snapshot.getKey();
                                Log.d("myd",String.valueOf(myd));
                                accept.setText(R.string.finalize);
                            }
                        }
                        uname = snapshot.child("uname").getValue().toString();
                        long time = Calendar.getInstance().getTimeInMillis()/60000-Long.parseLong(snapshot.child("hora").getValue().toString());
                        deliveryItemsList.add(new DeliveryItems(snapshot.child("lugar").getValue().toString(),uname,String.valueOf(time)));
                        sidedescl.add("Descripción del lugar: "+snapshot.child("ulugar").getValue().toString());
                        reqdescl.add("Descripción del pedido: "+snapshot.child("desc").getValue().toString());
                        productItemsList = new ArrayList<>();
                        for(DataSnapshot dsnapshot: snapshot.child("list").getChildren()){
                            productItemsList.add(new ProductItems(dsnapshot.child("producto").getValue().toString(),dsnapshot.child("precio").getValue().toString(),dsnapshot.child("cantidad").getValue().toString()));
                        }
                        plist.add(productItemsList);
                    }
                }
                recyclerView.setHasFixedSize(true);

                layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);

                adapter = new DeliveryItemsAdapter(deliveryItemsList);
                recyclerView.setAdapter(adapter);

                recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        sideDesc.setText(sidedescl.get(position));
                        reqDesc.setText(reqdescl.get(position));
                        layout.setVisibility(View.VISIBLE);
                        accept.setVisibility(View.VISIBLE);
                        if(mykey.equals(keys.get(position))){
                            accept.setText(R.string.finalize);
                        }else {
                            accept.setText(R.string.acccept);
                        }

                        recyclerView2.setHasFixedSize(true);

                        // Usar un administrador para LinearLayout
                        layoutManager2 = new LinearLayoutManager(getContext());
                        recyclerView2.setLayoutManager(layoutManager2);
                        productItemsList = plist.get(position);
                        //Crear un nuevo adaptador
                        adapter2 = new ProductItems2Adapter(productItemsList);
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

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myd<1&&accept.getText().equals(getString(R.string.acccept))){
                    builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("¿Estás seguro que deseas tomar el pedido del usuario "+uname+"?");
                    builder.setCancelable(true);
                    ;
                    builder.setPositiveButton(
                            getString(R.string.acccept),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    databaseReference = firebaseDatabase.getReference("orders");
                                    databaseReference.child(keys.get(lastp)).child("deliverer").setValue(dname);
                                    databaseReference.child(keys.get(lastp)).child("delivid").setValue(id);
                                    databaseReference.child(keys.get(lastp)).child("hora").setValue(String.valueOf(Calendar.getInstance().getTimeInMillis()/60000));
                                    databaseReference.child(keys.get(lastp)).child("estado").setValue("1");
                                    Toast.makeText(getContext(),"Pedido aceptado.",Toast.LENGTH_SHORT).show();
                                    mykey = keys.get(lastp);
                                    accept.setText(R.string.finalize);
                                }
                            });

                    builder.setNegativeButton(
                            getString(R.string.cancel),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    alert11 = builder.create();
                    alert11.show();
                }else if(accept.getText().equals(getString(R.string.finalize))){
                    builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("Recuerda que sólo puedes utilizar esta opción cuando ya hayas finalizado el pedido, de lo contrario se te penalizará tu puntuación");
                    builder.setCancelable(true);
                    builder.setPositiveButton(
                            getString(R.string.finalize),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    databaseReference = firebaseDatabase.getReference("orders");
                                    databaseReference.child(mykey).removeValue();
                                    databaseReference.child(mykey).child("uid").setValue(uid);
                                    databaseReference.child(mykey).child("myid").setValue(id);
                                    databaseReference.child(mykey).child("user").setValue("ok");
                                    databaseReference.child(mykey).child("deliv").setValue("ok");
                                    Toast.makeText(getContext(),"Pedido consolidado",Toast.LENGTH_SHORT).show();
                                    accept.setText(R.string.acccept);
                                    accept.setVisibility(View.GONE);
                                    layout.setVisibility(View.GONE);
                                    reqDesc.setText("");
                                    sideDesc.setText("");
                                    if(flagev==0){
                                        flagev=1;
                                        Intent intent = new Intent(getContext(),EvaluateActivity.class);
                                        intent.putExtra("uid",uid);
                                        intent.putExtra("key",mykey);
                                        intent.putExtra("myid",id);
                                        intent.putExtra("eval","user");
                                        startActivity(intent);
                                    }
                                }
                            });

                    builder.setNegativeButton(
                            getString(R.string.cancel),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    alert11 = builder.create();
                    alert11.show();
                }
                else {
                    Toast.makeText(getContext(),"No puede aceptar más de un pedido",Toast.LENGTH_SHORT).show();
                }
            }
        });
        databaseReference2 = firebaseDatabase.getReference("users").child(id);
        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("dname").exists()&&dataSnapshot.child("dscore").exists()){
                    dname = dataSnapshot.child("dname").getValue().toString();
                    tvDUser.setText(dname);
                    setScore(Double.parseDouble(dataSnapshot.child("dscore").getValue().toString()),view);
                    notDeliv(false);
                }else {
                    notDeliv(true);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }

    private void notDeliv(boolean f){
        if(f){
            scrollView.setVisibility(View.GONE);
            delivInfo.setVisibility(View.VISIBLE);
        }else{
            scrollView.setVisibility(View.VISIBLE);
            delivInfo.setVisibility(View.GONE);
        }
    }
    private void setScore(Double score,View view){
        ImageView h1,h2,h3,h4,h5;
        h1 = (ImageView)view.findViewById(R.id.h1);
        h2 = (ImageView)view.findViewById(R.id.h2);
        h3 = (ImageView)view.findViewById(R.id.h3);
        h4 = (ImageView)view.findViewById(R.id.h4);
        h5 = (ImageView)view.findViewById(R.id.h5);

        h1.setVisibility(View.VISIBLE);
        h2.setVisibility(View.VISIBLE);
        h3.setVisibility(View.VISIBLE);
        h4.setVisibility(View.VISIBLE);
        h5.setVisibility(View.VISIBLE);
        if(score > 4.5){
            h1.setImageResource(R.drawable.ic_favorite_black_24dp);
            h2.setImageResource(R.drawable.ic_favorite_black_24dp);
            h3.setImageResource(R.drawable.ic_favorite_black_24dp);
            h4.setImageResource(R.drawable.ic_favorite_black_24dp);
            h5.setImageResource(R.drawable.ic_favorite_black_24dp);
        }
        else if(score > 4.0){
            h1.setImageResource(R.drawable.ic_favorite_black_24dp);
            h2.setImageResource(R.drawable.ic_favorite_black_24dp);
            h3.setImageResource(R.drawable.ic_favorite_black_24dp);
            h4.setImageResource(R.drawable.ic_favorite_black_24dp);
            h5.setImageResource(R.drawable.ic_favorite_border_black_24dp);
        }
        else if(score > 3.5){
            h1.setImageResource(R.drawable.ic_favorite_black_24dp);
            h2.setImageResource(R.drawable.ic_favorite_black_24dp);
            h3.setImageResource(R.drawable.ic_favorite_black_24dp);
            h4.setImageResource(R.drawable.ic_favorite_black_24dp);
            h5.setVisibility(View.INVISIBLE);
        }
        else if(score > 3.0){
            h1.setImageResource(R.drawable.ic_favorite_black_24dp);
            h2.setImageResource(R.drawable.ic_favorite_black_24dp);
            h3.setImageResource(R.drawable.ic_favorite_black_24dp);
            h4.setImageResource(R.drawable.ic_favorite_border_black_24dp);
            h5.setVisibility(View.INVISIBLE);
        }
        else if(score > 2.5){
            h1.setImageResource(R.drawable.ic_favorite_black_24dp);
            h2.setImageResource(R.drawable.ic_favorite_black_24dp);
            h3.setImageResource(R.drawable.ic_favorite_black_24dp);
            h4.setVisibility(View.INVISIBLE);
            h5.setVisibility(View.INVISIBLE);
        }
        else if(score > 2.0){
            h1.setImageResource(R.drawable.ic_favorite_black_24dp);
            h2.setImageResource(R.drawable.ic_favorite_black_24dp);
            h3.setImageResource(R.drawable.ic_favorite_border_black_24dp);
            h4.setVisibility(View.INVISIBLE);
            h5.setVisibility(View.INVISIBLE);
        }
        else if(score > 1.5){
            h1.setImageResource(R.drawable.ic_favorite_black_24dp);
            h2.setImageResource(R.drawable.ic_favorite_black_24dp);
            h3.setVisibility(View.INVISIBLE);
            h4.setVisibility(View.INVISIBLE);
            h5.setVisibility(View.INVISIBLE);
        }
        else if(score > 1.0){
            h1.setImageResource(R.drawable.ic_favorite_black_24dp);
            h2.setImageResource(R.drawable.ic_favorite_border_black_24dp);
            h3.setVisibility(View.INVISIBLE);
            h4.setVisibility(View.INVISIBLE);
            h5.setVisibility(View.INVISIBLE);
        }
        else if(score == 0){
            h1.setVisibility(View.GONE);
            h2.setVisibility(View.GONE);
            h3.setVisibility(View.GONE);
            h4.setVisibility(View.GONE);
            h5.setVisibility(View.GONE);
        }
        else {
            h1.setImageResource(R.drawable.ic_favorite_black_24dp);
            h2.setVisibility(View.INVISIBLE);
            h3.setVisibility(View.INVISIBLE);
            h4.setVisibility(View.INVISIBLE);
            h5.setVisibility(View.INVISIBLE);
        }
    }

    private void logOut() {
        Intent intent = new Intent(getActivity(),LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }


}
