package com.example.david.findberry;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
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

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    List<RequestItems> requestItemsList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_request, container, false);


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("sides").child("images");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                requestItemsList.clear();
                requestItemsList.add(new RequestItems(dataSnapshot.child("food").getValue().toString(),getString(R.string.foodmenu)));
                requestItemsList.add(new RequestItems(dataSnapshot.child("acad").getValue().toString(),getString(R.string.acadmenu)));
                requestItemsList.add(new RequestItems(dataSnapshot.child("fun").getValue().toString(),getString(R.string.ociomenu)));
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
                                intent.putExtra("op","fun");
                                startActivity(intent);
                                break;
                        }

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                        switch (position) {
                            case 0:
                                Snackbar.make(view,R.string.fooddesc, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                break;
                            case 1:
                                Snackbar.make(view,R.string.acaddesc, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                break;
                            case 2:
                                Snackbar.make(view,R.string.fundesc, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                break;
                        }
                    }
                }));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return view;
    }

}
