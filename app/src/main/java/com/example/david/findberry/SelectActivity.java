package com.example.david.findberry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SelectActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    String data="",flag="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        Bundle bundle = getIntent().getExtras();
        data = bundle.getString("op");

        firebaseDatabase = FirebaseDatabase.getInstance();

        final List<RequestItems> requestItemsList = new ArrayList<>();

        databaseReference = firebaseDatabase.getReference("sides").child(data);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("datos",dataSnapshot.getValue().toString());
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    requestItemsList.add(new RequestItems(postSnapshot.child("drawable").getValue().toString(),postSnapshot.child("nombre").getValue().toString(),Double.parseDouble(postSnapshot.child("score").getValue().toString())));
                }
                //Log.d("data",sides[i].toString());
                recyclerView = (RecyclerView)findViewById(R.id.sRecycler);
                recyclerView.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(SelectActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                adapter = new RequestItemsAdapter2(requestItemsList);
                recyclerView.setAdapter(adapter);

                recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent;
                        switch (data){
                            case "food":
                                intent = new Intent(SelectActivity.this,ProductActivity.class);
                                flag = String.valueOf(position);
                                intent.putExtra("op",flag);
                                startActivity(intent);
                                break;
                            case "acad":
                                intent = new Intent(SelectActivity.this,ProductActivity.class);
                                flag = String.valueOf(position);
                                intent.putExtra("op",flag);
                                startActivity(intent);
                                break;
                            case "fun":
                                intent = new Intent(SelectActivity.this,EventActivity.class);
                                flag = String.valueOf(position);
                                intent.putExtra("op",flag);
                                startActivity(intent);
                                break;
                        }
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




    }
}
