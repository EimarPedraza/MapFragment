package com.example.david.findberry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TopActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    List<TopUsers> topUsersList = new ArrayList<>();

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);
        recyclerView = (RecyclerView)findViewById(R.id.atRecycler);
        recyclerView.setHasFixedSize(true);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                topUsersList.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    switch (getIntent().getStringExtra("op")){
                        case "user":
                            if(snapshot.child("score").exists()&&snapshot.child("name").exists()){
                                topUsersList.add(new TopUsers(snapshot.child("name").getValue().toString(),Double.parseDouble(snapshot.child("score").getValue().toString())));
                            }
                            break;
                        case "deliv":
                            if(snapshot.child("dscore").exists()&&snapshot.child("dname").exists()){
                                topUsersList.add(new TopUsers(snapshot.child("dname").getValue().toString(),Double.parseDouble(snapshot.child("dscore").getValue().toString())));
                            }
                            break;
                    }
                }

                layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);

                adapter = new TopUsersAdapter(topUsersList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
