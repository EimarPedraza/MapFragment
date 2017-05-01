package com.example.david.findberry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    String flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Bundle bundle = getIntent().getExtras();
        flag = bundle.getString("op");
        Log.d("Valor",flag);

        List<ProductItems> productItemsList = new ArrayList<>();
        switch (flag){
            case "0":
                productItemsList.add(new ProductItems("Pastel de pollo","$ 1800",""));
                productItemsList.add(new ProductItems("Empanada ","$ 1800",""));
                productItemsList.add(new ProductItems("Brownie","$ 2000",""));
                break;
            case "1":
                productItemsList.add(new ProductItems("Palito de queso","$ 1500",""));
                productItemsList.add(new ProductItems("Milo frío ","$ 2000",""));
                productItemsList.add(new ProductItems("Buñuelo","$ 1500",""));
                break;
            case "2":
                productItemsList.add(new ProductItems("S. Primavera","$ 4700",""));
                productItemsList.add(new ProductItems("Crema tomate ","$ 1800",""));
                productItemsList.add(new ProductItems("Batido energía","$ 5000",""));
                break;
        }

        recyclerView = (RecyclerView)findViewById(R.id.paRecycler);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ProductItemsAdapter(productItemsList);
        recyclerView.setAdapter(adapter);

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
