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


    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,databaseReference2;

    TextView tvProduct,tvProductt,tvSide,tvSidet,tvPrice,tvPricet,tvRoutet,tvUser,tvUsert;
    Button bRoute;
    int pos;
    List<DeliveryItems> deliveryItemsList = new ArrayList<>();
    String id;
    List<String> ids = new ArrayList<>();
    List<String> names = new ArrayList<>();



    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_delivery, container, false);
        //Toast.makeText(getContext(),R.string.deliverydesc,Toast.LENGTH_LONG).show();
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


        ImageView profilePhoto = (ImageView)view.findViewById(R.id.ivPhoto);

        firebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
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
                ids.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    if(snapshot.child("hora").exists()&&
                            snapshot.child("lugar").exists()&&
                            snapshot.child("uname").exists()&&
                            snapshot.child("").exists()&&
                            snapshot.child("").exists()){
                        long time = Calendar.getInstance().getTimeInMillis()/60000-Long.parseLong(snapshot.child("hora").getValue().toString());
                        deliveryItemsList.add(new DeliveryItems(snapshot.child("lugar").getValue().toString(),snapshot.child("uname").getValue().toString(),String.valueOf(time)));

                    }
                }
                recyclerView = (RecyclerView) view.findViewById(R.id.fdRecycler);
                recyclerView.setHasFixedSize(true);

                layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);

                adapter = new DeliveryItemsAdapter(deliveryItemsList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





        /*deliveryItemsList.add(new DeliveryItems("La miguería","El Varo","2000"));
        deliveryItemsList.add(new DeliveryItems("Arbóreo","David","3000"));
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
        }));*/
        return view;
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
