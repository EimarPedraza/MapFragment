package com.example.david.findberry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class SelectActivity extends AppCompatActivity {

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


        List<RequestItems> requestItemsList = new ArrayList<>();


        switch(data){
            case "food":
                requestItemsList.add(new RequestItems(R.drawable.delo,"De Lolita Restó",4.5));
                requestItemsList.add(new RequestItems(R.drawable.pastora,"Pastora",3));
                requestItemsList.add(new RequestItems(R.drawable.arbo,"Arbóreo",4.5));
                break;
            case "acad":
                requestItemsList.add(new RequestItems(R.drawable.cooes,"Cooesdua",4.5));
                requestItemsList.add(new RequestItems(R.drawable.turco,"Papelería Turco",5));
                requestItemsList.add(new RequestItems(R.drawable.copi,"Impresiones y copias",4.5));
                break;
            case "ocio":
                requestItemsList.add(new RequestItems(R.drawable.procinal,"Aventura Cine",4.5));
                requestItemsList.add(new RequestItems(R.drawable.royal," Bosque Plaza Cine",1));
                requestItemsList.add(new RequestItems(R.drawable.otros,"Eventos",4.5));
                break;
        }

        recyclerView = (RecyclerView)findViewById(R.id.sRecycler);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
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
                    case "ocio":
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
}
