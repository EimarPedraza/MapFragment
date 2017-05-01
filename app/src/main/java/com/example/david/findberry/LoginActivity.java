package com.example.david.findberry;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText etUser,etPass;
    Button bLog;
    TextView tvReg;
    String username="";
    String password="";
    String correo="";

    //preferencias compartidas
    SharedPreferences prefs;//defino mi objeto
    SharedPreferences.Editor editor; //requiero un editor

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE); //quitar el action bar
        setContentView(R.layout.activity_login);


        //preferencias
        prefs = getSharedPreferences("preferences",MODE_PRIVATE);//nombre y modo
        editor = prefs.edit(); //asi enlazo mis preferencias  y tengo acceso a dicha informacion



        etUser = (EditText)findViewById(R.id.etLogUser); //Enlace entre Xml y java
        etPass = (EditText)findViewById(R.id.etLogPass);
        bLog = (Button)findViewById(R.id.bLogLog);
        tvReg = (TextView)findViewById(R.id.tvLogReg);


        username=prefs.getString("username","");
        password=prefs.getString("password","");
        correo=prefs.getString("correo","");

        if(prefs.getInt("login",-1)==1)
        { //quiere decir que hay alguien logueado
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            intent.putExtra("username",username);
            intent.putExtra("password",password);
            intent.putExtra("correo",correo);
            startActivity(intent);
            finish();

        }



        tvReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivityForResult(intent,1234); //envio codigo de confirmacion
                //finish();
            }
        });

        //etUser.setText(getIntent().getStringExtra("user"));
        //etPass.setText(getIntent().getStringExtra("pass"));

        bLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etUser.getText().toString().equals(username)){
                    if(etPass.getText().toString().equals(password)){
                        editor.putInt("login",1); //1 hay alguien logeado, 0 noo hay logueado
                        editor.apply();
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        intent.putExtra("username",username);
                        intent.putExtra("correo",correo);
                        startActivity(intent);
                        finish();
                    }
                    else if(etPass.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(),R.string.tLogEmpty,Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),R.string.tLogWrongPass,Toast.LENGTH_SHORT).show();
                    }
                }
                else if(etUser.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),R.string.tLogEmpty,Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),R.string.tLogWrongUser,Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    //Este metodo me permite recibir la respuesta de la actividad que llame inicialmente
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1234 && resultCode==RESULT_OK)
        {
            username = data.getExtras().getString("username"); //aqui almaceno lo que guarde desde register activity
            password = data.getExtras().getString("password");//aqui almaceno lo que guarde desde register activity
            correo   = data.getExtras().getString("correo");//aqui almaceno lo que guarde desde register activiy
            //Log.d("nombre",data.getExtras().getString("username")); //me permite ver lo que estoy recibiendo pero en consola en vez de en la app como tal

            editor.putString("username", username);
            editor.putString("password", password);
            editor.putString("correo", correo);



        }
        if (requestCode == 1234 && resultCode==RESULT_CANCELED)
        {
            Toast.makeText(this,getText(R.string.loginerror), Toast.LENGTH_SHORT).show();

        }

    }






}

