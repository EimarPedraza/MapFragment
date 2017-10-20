package com.example.david.findberry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EvaluateActivity extends AppCompatActivity {

    TextView text,name;
    Button confirm;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Double score = 1.0;
    String myId;
    String evalTargetId;
    String key;
    int flag = 0;
    Double myscore,newscore,tscore,tnscore,newnscore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate);
        text = (TextView)findViewById(R.id.evalInfo);
        name = (TextView)findViewById(R.id.name);
        confirm = (Button)findViewById(R.id.sendEval);
        key = getIntent().getStringExtra("key");
        if(getIntent().getStringExtra("eval").equals("deliverer")){
            text.setText(R.string.evalinfo);
            myId = getIntent().getStringExtra("uid");
            evalTargetId = getIntent().getStringExtra("myid");
            flag = 1;
        }else{
            text.setText(R.string.evalinfo2);
            myId = getIntent().getStringExtra("myid");
            Log.d("myid",myId);
            evalTargetId = getIntent().getStringExtra("uid");
            flag = 2;
        }

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(flag == 1){
                    myscore = Double.parseDouble(dataSnapshot.child(myId).child("score").getValue().toString());
                    tscore = Double.parseDouble(dataSnapshot.child(evalTargetId).child("dscore").getValue().toString());
                    tnscore = Double.parseDouble(dataSnapshot.child(evalTargetId).child("dnscore").getValue().toString());
                    name.setText(dataSnapshot.child(evalTargetId).child("dname").getValue().toString());
                }else if(flag == 2){
                    myscore = Double.parseDouble(dataSnapshot.child(evalTargetId).child("dscore").getValue().toString());
                    tscore = Double.parseDouble(dataSnapshot.child(myId).child("score").getValue().toString());
                    tnscore = Double.parseDouble(dataSnapshot.child(myId).child("nscore").getValue().toString());
                    name.setText(dataSnapshot.child(evalTargetId).child("name").getValue().toString());
                }
                confirm.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.h1:
                setScore(1);
                score = 1.0;
                break;
            case R.id.h2:
                setScore(2);
                score = 2.0;
                break;
            case R.id.h3:
                setScore(3);
                score = 3.0;
                break;
            case R.id.h4:
                setScore(4);
                score = 4.0;
                break;
            case R.id.h5:
                setScore(5);
                score = 5.0;
                break;
            case R.id.sendEval:
                newnscore = tnscore+myscore;
                newscore = ((tscore*tnscore)+(myscore*score))/(newnscore);
                if(flag == 1){
                    databaseReference = firebaseDatabase.getReference("orders");
                    databaseReference.child(key).child("user").removeValue();
                    databaseReference = firebaseDatabase.getReference("users");
                    databaseReference.child(evalTargetId).child("dscore").setValue(newscore);
                    databaseReference.child(evalTargetId).child("dnscore").setValue(newnscore);
                }else if(flag == 2){
                    databaseReference = firebaseDatabase.getReference("orders");
                    databaseReference.child(key).child("deliv").removeValue();
                    databaseReference = firebaseDatabase.getReference("users");
                    databaseReference.child(evalTargetId).child("score").setValue(newscore);
                    databaseReference.child(evalTargetId).child("nscore").setValue(newnscore);
                    databaseReference = firebaseDatabase.getReference("orders");
                }
                Toast.makeText(getApplicationContext(),"Gracias por enviar tu evaluación",Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }

    private void setScore(int score){
        ImageView h1,h2,h3,h4,h5;
        h1 = (ImageView)findViewById(R.id.h1);
        h2 = (ImageView)findViewById(R.id.h2);
        h3 = (ImageView)findViewById(R.id.h3);
        h4 = (ImageView)findViewById(R.id.h4);
        h5 = (ImageView)findViewById(R.id.h5);


        switch (score){
            case 1:
                h1.setImageResource(R.drawable.ic_favorite_black_24dp);
                h2.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                h3.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                h4.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                h5.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                break;
            case 2:
                h1.setImageResource(R.drawable.ic_favorite_black_24dp);
                h2.setImageResource(R.drawable.ic_favorite_black_24dp);
                h3.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                h4.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                h5.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                break;
            case 3:
                h1.setImageResource(R.drawable.ic_favorite_black_24dp);
                h2.setImageResource(R.drawable.ic_favorite_black_24dp);
                h3.setImageResource(R.drawable.ic_favorite_black_24dp);
                h4.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                h5.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                break;
            case 4:
                h1.setImageResource(R.drawable.ic_favorite_black_24dp);
                h2.setImageResource(R.drawable.ic_favorite_black_24dp);
                h3.setImageResource(R.drawable.ic_favorite_black_24dp);
                h4.setImageResource(R.drawable.ic_favorite_black_24dp);
                h5.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                break;
            case 5:
                h1.setImageResource(R.drawable.ic_favorite_black_24dp);
                h2.setImageResource(R.drawable.ic_favorite_black_24dp);
                h3.setImageResource(R.drawable.ic_favorite_black_24dp);
                h4.setImageResource(R.drawable.ic_favorite_black_24dp);
                h5.setImageResource(R.drawable.ic_favorite_black_24dp);
                break;
        }
    }


}

