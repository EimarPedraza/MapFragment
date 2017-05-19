package com.example.david.findberry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    String flag,data,name;
    TextView title;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    List<ProductItems> productItemsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        title = (TextView)findViewById(R.id.tvTitle);
        Bundle bundle = getIntent().getExtras();
        flag = bundle.getString("op");
        data = bundle.getString("data");
        title.setText(bundle.getString("title"));


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("sides").child(data).child(flag).child("products");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    productItemsList.add(new ProductItems(postSnapshot.child("nombre").getValue().toString(),postSnapshot.child("precio").getValue().toString(),""));
                }
                recyclerView = (RecyclerView)findViewById(R.id.paRecycler);
                recyclerView.setHasFixedSize(true);

                layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);

                adapter = new ProductItemsAdapter(productItemsList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.opFAccept:
                Toast.makeText(getApplicationContext(),"Pedido aceptado",Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.opFCancel:
                finish();
                break;
        }
    }
}
