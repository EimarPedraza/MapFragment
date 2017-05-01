package com.example.david.findberry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class EventActivity extends AppCompatActivity {

    Intent intent;
    ImageView imageView;
    Button confirm;
    String flag="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        confirm = (Button)findViewById(R.id.bConfirm);
        imageView =(ImageView)findViewById(R.id.eaImage);

        Bundle bundle = getIntent().getExtras();
        flag = bundle.getString("op");

        switch (flag){
            case "0":
                imageView.setImageResource(R.drawable.procinal);
                break;
            case "1":
                imageView.setImageResource(R.drawable.royal);
                break;
            case "2":
                imageView.setImageResource(R.drawable.otros);
                break;
        }

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Datos Recibidos", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }
}
