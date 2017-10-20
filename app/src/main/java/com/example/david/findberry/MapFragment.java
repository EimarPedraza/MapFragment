package com.example.david.findberry;


import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.location.LocationListener;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {

    MapView mapView;
    GoogleMap map;
    Bundle bundle = this.getArguments();
    public MapFragment() {
        // Required empty public constructor
    }

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DatabaseReference databaseReference2;
    int flag = 0,flag2 = 0;
    String local="";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("sides");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (flag == 1) {
                    map.clear();
                    map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    for (DataSnapshot postSnapshot : dataSnapshot.child("food").getChildren()) {
                        if(flag2==1){
                            if(local.equals(postSnapshot.child("nombre").getValue().toString())){
                                LatLng marker = new LatLng(Double.parseDouble(postSnapshot.child("lat").getValue().toString()), Double.parseDouble(postSnapshot.child("lng").getValue().toString()));
                                map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.information)).position(marker).title(postSnapshot.child("nombre").getValue().toString()).snippet(getString(R.string.currentOrder)));
                            }else {
                                LatLng mark = new LatLng(Double.parseDouble(postSnapshot.child("lat").getValue().toString()), Double.parseDouble(postSnapshot.child("lng").getValue().toString()));
                                map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.food)).position(mark).title(postSnapshot.child("nombre").getValue().toString()).snippet("Precio del domicilio: " + postSnapshot.child("precio").getValue().toString()));
                            }
                        }else{
                            LatLng mark = new LatLng(Double.parseDouble(postSnapshot.child("lat").getValue().toString()), Double.parseDouble(postSnapshot.child("lng").getValue().toString()));
                            map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.food)).position(mark).title(postSnapshot.child("nombre").getValue().toString()).snippet("Precio del domicilio: " + postSnapshot.child("precio").getValue().toString()));
                        }
                    }
                    for (DataSnapshot postSnapshot : dataSnapshot.child("acad").getChildren()) {
                        if(flag2==1){
                            if(local.equals(postSnapshot.child("nombre").getValue().toString())){
                                LatLng marker = new LatLng(Double.parseDouble(postSnapshot.child("lat").getValue().toString()), Double.parseDouble(postSnapshot.child("lng").getValue().toString()));
                                map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.information)).position(marker).title(postSnapshot.child("nombre").getValue().toString()).snippet(getString(R.string.currentOrder)));
                            }else {
                                LatLng mark = new LatLng(Double.parseDouble(postSnapshot.child("lat").getValue().toString()), Double.parseDouble(postSnapshot.child("lng").getValue().toString()));
                                map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.acad)).position(mark).title(postSnapshot.child("nombre").getValue().toString()).snippet("Precio del domicilio: " + postSnapshot.child("precio").getValue().toString()));
                            }
                        }
                        else {
                            LatLng mark = new LatLng(Double.parseDouble(postSnapshot.child("lat").getValue().toString()), Double.parseDouble(postSnapshot.child("lng").getValue().toString()));
                            map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.acad)).position(mark).title(postSnapshot.child("nombre").getValue().toString()).snippet("Precio del domicilio: " + postSnapshot.child("precio").getValue().toString()));
                        }

                    }
                    for (DataSnapshot postSnapshot : dataSnapshot.child("fun").getChildren()) {
                        if(flag2==1){
                            if(local.equals(postSnapshot.child("nombre").getValue().toString())){
                                LatLng marker = new LatLng(Double.parseDouble(postSnapshot.child("lat").getValue().toString()), Double.parseDouble(postSnapshot.child("lng").getValue().toString()));
                                map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.information)).position(marker).title(postSnapshot.child("nombre").getValue().toString()).snippet(getString(R.string.currentOrder)));
                            }else{
                                LatLng mark = new LatLng(Double.parseDouble(postSnapshot.child("lat").getValue().toString()), Double.parseDouble(postSnapshot.child("lng").getValue().toString()));
                                map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.fun)).position(mark).title(postSnapshot.child("nombre").getValue().toString()).snippet("Precio del domicilio: " + postSnapshot.child("precio").getValue().toString()));
                            }
                        }else {
                            LatLng mark = new LatLng(Double.parseDouble(postSnapshot.child("lat").getValue().toString()), Double.parseDouble(postSnapshot.child("lng").getValue().toString()));
                            map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.fun)).position(mark).title(postSnapshot.child("nombre").getValue().toString()).snippet("Precio del domicilio: " + postSnapshot.child("precio").getValue().toString()));
                        }

                    }
                    bundle = getArguments();
                    LatLng udea;
                    if(bundle!=null){
                        udea = new LatLng(bundle.getDouble("lat"), bundle.getDouble("lng"));
                        map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.me)).position(udea).title(getString(R.string.myLoc)).snippet(getString(R.string.currentLoc)));
                    }
                    else{
                        udea = new LatLng(6.268021,-75.567693);

                    }
                    flag2 = 0;
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(udea, 16));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference2 = firebaseDatabase.getReference("orders");
        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    if(snapshot.child("delivid").exists() && flag == 1){
                        Bundle bundle = getArguments();
                        String id = bundle.getString("id");
                        Log.d("idu",id);
                        flag2 = 1;
                        local = snapshot.child("lugar").getValue().toString();
                        if(snapshot.child("delivid").getValue().toString().equals(id)){
                            if(snapshot.child("ulat").exists() && snapshot.child("ulng").exists()){
                                Double lat = Double.parseDouble(snapshot.child("ulat").getValue().toString());
                                Double lng = Double.parseDouble(snapshot.child("ulng").getValue().toString());
                                String uname = snapshot.child("uname").getValue().toString();
                                String ulugar = snapshot.child("ulugar").getValue().toString();
                                Log.d("lat",lat.toString());
                                Log.d("lng",lng.toString());
                                Log.d("uname",uname);
                                Log.d("ulugar",ulugar);
                                LatLng pedido=new LatLng(lat,lng);
                                map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.information)).position(pedido).title(uname).snippet(ulugar));
                                map.moveCamera(CameraUpdateFactory.newLatLngZoom(pedido, 16));
                            }
                            else{
                                Toast.makeText(getContext(), R.string.userNoLoc,Toast.LENGTH_SHORT).show();
                            }

                        }

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        mapView = (MapView) view.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(this);


        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                map.clear();
                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                for (DataSnapshot postSnapshot : dataSnapshot.child("food").getChildren()) {
                    if(flag2==1){
                        if(local.equals(postSnapshot.child("nombre").getValue().toString())){
                            LatLng marker = new LatLng(Double.parseDouble(postSnapshot.child("lat").getValue().toString()), Double.parseDouble(postSnapshot.child("lng").getValue().toString()));
                            map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.information)).position(marker).title(postSnapshot.child("nombre").getValue().toString()).snippet(getString(R.string.currentOrder)));
                        }else {
                            LatLng mark = new LatLng(Double.parseDouble(postSnapshot.child("lat").getValue().toString()), Double.parseDouble(postSnapshot.child("lng").getValue().toString()));
                            map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.food)).position(mark).title(postSnapshot.child("nombre").getValue().toString()).snippet("Precio del domicilio: " + postSnapshot.child("precio").getValue().toString()));
                        }
                    }else{
                        LatLng mark = new LatLng(Double.parseDouble(postSnapshot.child("lat").getValue().toString()), Double.parseDouble(postSnapshot.child("lng").getValue().toString()));
                        map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.food)).position(mark).title(postSnapshot.child("nombre").getValue().toString()).snippet("Precio del domicilio: " + postSnapshot.child("precio").getValue().toString()));
                    }
                }
                for (DataSnapshot postSnapshot : dataSnapshot.child("acad").getChildren()) {
                    if(flag2==1){
                        if(local.equals(postSnapshot.child("nombre").getValue().toString())){
                            LatLng marker = new LatLng(Double.parseDouble(postSnapshot.child("lat").getValue().toString()), Double.parseDouble(postSnapshot.child("lng").getValue().toString()));
                            map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.information)).position(marker).title(postSnapshot.child("nombre").getValue().toString()).snippet(getString(R.string.currentOrder)));
                        }else {
                            LatLng mark = new LatLng(Double.parseDouble(postSnapshot.child("lat").getValue().toString()), Double.parseDouble(postSnapshot.child("lng").getValue().toString()));
                            map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.acad)).position(mark).title(postSnapshot.child("nombre").getValue().toString()).snippet("Precio del domicilio: " + postSnapshot.child("precio").getValue().toString()));
                        }
                    }
                    else {
                        LatLng mark = new LatLng(Double.parseDouble(postSnapshot.child("lat").getValue().toString()), Double.parseDouble(postSnapshot.child("lng").getValue().toString()));
                        map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.acad)).position(mark).title(postSnapshot.child("nombre").getValue().toString()).snippet("Precio del domicilio: " + postSnapshot.child("precio").getValue().toString()));
                    }
                }
                for (DataSnapshot postSnapshot : dataSnapshot.child("fun").getChildren()) {
                    if(flag2==1){
                        if(local.equals(postSnapshot.child("nombre").getValue().toString())){
                            LatLng marker = new LatLng(Double.parseDouble(postSnapshot.child("lat").getValue().toString()), Double.parseDouble(postSnapshot.child("lng").getValue().toString()));
                            map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.information)).position(marker).title(postSnapshot.child("nombre").getValue().toString()).snippet(getString(R.string.currentOrder)));
                        }else{
                            LatLng mark = new LatLng(Double.parseDouble(postSnapshot.child("lat").getValue().toString()), Double.parseDouble(postSnapshot.child("lng").getValue().toString()));
                            map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.fun)).position(mark).title(postSnapshot.child("nombre").getValue().toString()).snippet("Precio del domicilio: " + postSnapshot.child("precio").getValue().toString()));
                        }
                    }else {
                        LatLng mark = new LatLng(Double.parseDouble(postSnapshot.child("lat").getValue().toString()), Double.parseDouble(postSnapshot.child("lng").getValue().toString()));
                        map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.fun)).position(mark).title(postSnapshot.child("nombre").getValue().toString()).snippet("Precio del domicilio: " + postSnapshot.child("precio").getValue().toString()));
                    }
                }
                LatLng udea;
                Bundle bundle = getArguments();
                if(bundle!=null){
                    udea = new LatLng(bundle.getDouble("lat"), bundle.getDouble("lng"));
                    map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.me)).position(udea).title(getString(R.string.myLoc)).snippet(getString(R.string.currentLoc)));
                }
                else{
                    udea = new LatLng(6.268021,-75.567693);

                }
                flag2 = 0;
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(udea, 16));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        databaseReference2 = firebaseDatabase.getReference("orders");
        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    if(snapshot.child("delivid").exists()){
                        Bundle bundle = getArguments();
                        String id = bundle.getString("id");
                        Log.d("idu",id);
                        flag2 = 1;
                        local = snapshot.child("lugar").getValue().toString();
                        if(snapshot.child("delivid").getValue().toString().equals(id)){
                            if(snapshot.child("ulat").exists()){
                                Double lat = Double.parseDouble(snapshot.child("ulat").getValue().toString());
                                Double lng = Double.parseDouble(snapshot.child("ulng").getValue().toString());
                                String uname = snapshot.child("uname").getValue().toString();
                                String ulugar = snapshot.child("ulugar").getValue().toString();
                                Log.d("lat",lat.toString());
                                Log.d("lng",lng.toString());
                                Log.d("uname",uname);
                                Log.d("ulugar",ulugar);
                                LatLng pedido=new LatLng(lat,lng);
                                map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.information)).position(pedido).title(uname).snippet(ulugar));
                                map.moveCamera(CameraUpdateFactory.newLatLngZoom(pedido, 16));
                            }
                            else{
                                Toast.makeText(getContext(), R.string.userNoLoc,Toast.LENGTH_SHORT).show();
                            }

                        }

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        flag = 1;
    }

}




