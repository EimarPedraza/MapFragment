package com.example.david.findberry;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {


    public SettingsFragment() {
        // Required empty public constructor
    }

    SharedPreferences preferences;//defino mi objeto
    SharedPreferences.Editor editor; //requiero un editor

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        preferences = getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);//nombre y modo
        editor = preferences.edit(); //asi enlazo mis preferencias  y tengo acceso a dicha informacion

        Button logout = (Button)view.findViewById(R.id.fsLogout);
        TextView user = (TextView)view.findViewById(R.id.fsUser);

        user.setText(getActivity().getIntent().getStringExtra("username"));

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("login",-1); //valor con el que valido que alguien no se ha logueado
                editor.apply();
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return view;
    }

}
