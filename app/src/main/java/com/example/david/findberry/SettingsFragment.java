package com.example.david.findberry;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {


    public SettingsFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        Button logout = (Button)view.findViewById(R.id.fsLogout);
        TextView nameuser = (TextView)view.findViewById(R.id.fsUser);
        ImageView profilePhoto = (ImageView)view.findViewById(R.id.ivPhoto);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            Picasso.with(getContext()).load(photoUrl).into(profilePhoto);

            nameuser.setText(name);


            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
        }else {
            logOut();
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();
                FirebaseAuth.getInstance().signOut();
                logOut();
            }
        });
        return view;
    }

    private void logOut() {
        Intent intent = new Intent(getActivity(),LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    private void setScore(Double score,View view){
        ImageView h1,h2,h3,h4,h5;
        h1 = (ImageView)view.findViewById(R.id.h1);
        h2 = (ImageView)view.findViewById(R.id.h2);
        h3 = (ImageView)view.findViewById(R.id.h3);
        h4 = (ImageView)view.findViewById(R.id.h4);
        h5 = (ImageView)view.findViewById(R.id.h5);

        h1.setVisibility(View.VISIBLE);
        h2.setVisibility(View.VISIBLE);
        h3.setVisibility(View.VISIBLE);
        h4.setVisibility(View.VISIBLE);
        h5.setVisibility(View.VISIBLE);
        if(score > 4.5){
            h1.setImageResource(R.drawable.ic_favorite_black_24dp);
            h2.setImageResource(R.drawable.ic_favorite_black_24dp);
            h3.setImageResource(R.drawable.ic_favorite_black_24dp);
            h4.setImageResource(R.drawable.ic_favorite_black_24dp);
            h5.setImageResource(R.drawable.ic_favorite_black_24dp);
        }
        else if(score > 4.0){
            h1.setImageResource(R.drawable.ic_favorite_black_24dp);
            h2.setImageResource(R.drawable.ic_favorite_black_24dp);
            h3.setImageResource(R.drawable.ic_favorite_black_24dp);
            h4.setImageResource(R.drawable.ic_favorite_black_24dp);
            h5.setImageResource(R.drawable.ic_favorite_border_black_24dp);
        }
        else if(score > 3.5){
            h1.setImageResource(R.drawable.ic_favorite_black_24dp);
            h2.setImageResource(R.drawable.ic_favorite_black_24dp);
            h3.setImageResource(R.drawable.ic_favorite_black_24dp);
            h4.setImageResource(R.drawable.ic_favorite_black_24dp);
            h5.setVisibility(View.INVISIBLE);
        }
        else if(score > 3.0){
            h1.setImageResource(R.drawable.ic_favorite_black_24dp);
            h2.setImageResource(R.drawable.ic_favorite_black_24dp);
            h3.setImageResource(R.drawable.ic_favorite_black_24dp);
            h4.setImageResource(R.drawable.ic_favorite_border_black_24dp);
            h5.setVisibility(View.INVISIBLE);
        }
        else if(score > 2.5){
            h1.setImageResource(R.drawable.ic_favorite_black_24dp);
            h2.setImageResource(R.drawable.ic_favorite_black_24dp);
            h3.setImageResource(R.drawable.ic_favorite_black_24dp);
            h4.setVisibility(View.INVISIBLE);
            h5.setVisibility(View.INVISIBLE);
        }
        else if(score > 2.0){
            h1.setImageResource(R.drawable.ic_favorite_black_24dp);
            h2.setImageResource(R.drawable.ic_favorite_black_24dp);
            h3.setImageResource(R.drawable.ic_favorite_border_black_24dp);
            h4.setVisibility(View.INVISIBLE);
            h5.setVisibility(View.INVISIBLE);
        }
        else if(score > 1.5){
            h1.setImageResource(R.drawable.ic_favorite_black_24dp);
            h2.setImageResource(R.drawable.ic_favorite_black_24dp);
            h3.setVisibility(View.INVISIBLE);
            h4.setVisibility(View.INVISIBLE);
            h5.setVisibility(View.INVISIBLE);
        }
        else if(score > 1.0){
            h1.setImageResource(R.drawable.ic_favorite_black_24dp);
            h2.setImageResource(R.drawable.ic_favorite_border_black_24dp);
            h3.setVisibility(View.INVISIBLE);
            h4.setVisibility(View.INVISIBLE);
            h5.setVisibility(View.INVISIBLE);
        }
        else {
            h1.setImageResource(R.drawable.ic_favorite_black_24dp);
            h2.setVisibility(View.INVISIBLE);
            h3.setVisibility(View.INVISIBLE);
            h4.setVisibility(View.INVISIBLE);
            h5.setVisibility(View.INVISIBLE);
        }
    }

}
