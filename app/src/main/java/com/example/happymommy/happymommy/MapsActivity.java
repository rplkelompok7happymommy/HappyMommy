package com.example.happymommy.happymommy;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        Intent intent = getIntent();
        String id = intent.getStringExtra("Peta");

        if (id.equals("Limijati Women And Children")){
            mMap = googleMap;

            // Add a marker in Sydney and move the camera
            LatLng sydney = new LatLng(-6.9060568,107.6114247);
            mMap.addMarker(new MarkerOptions().position(sydney).title("Limijati Women And Children Hospital"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }

        if (id.equals("Rumah Bersalin Mutiara Cikutra")){
            mMap = googleMap;

            // Add a marker in Sydney and move the camera
            LatLng sydney = new LatLng(-6.900348,107.641055);
            mMap.addMarker(new MarkerOptions().position(sydney).title("Rumah Bersalin Mutiara Cikutra"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }

        if (id.equals("Rumah Bersalin Cuma Cuma")){
            mMap = googleMap;

            // Add a marker in Sydney and move the camera
            LatLng sydney = new LatLng(-6.9060568,107.6114247);
            mMap.addMarker(new MarkerOptions().position(sydney).title("Rumah Bersalin Cuma Cuma"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }

        if (id.equals("Emma Poeradiredja Birth Hospital")){
            mMap = googleMap;

            // Add a marker in Sydney and move the camera
            LatLng sydney = new LatLng(-6.9132686,107.6114561);
            mMap.addMarker(new MarkerOptions().position(sydney).title("Emma Poeradiredja Birth Hospital"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }

        if (id.equals("RSIA Grha Bunda")){
            mMap = googleMap;

            // Add a marker in Sydney and move the camera
            LatLng sydney = new LatLng(-6.9132433,107.6432544);
            mMap.addMarker(new MarkerOptions().position(sydney).title("RSIA Grha Bunda"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }

        if (id.equals("Klinik Utama Kebidanan Harkel")){
            mMap = googleMap;

            // Add a marker in Sydney and move the camera
            LatLng sydney = new LatLng(-6.9453679,107.6146108);
            mMap.addMarker(new MarkerOptions().position(sydney).title("Klinik Utama Kebidanan Harkel"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }

        if (id.equals("Hermina Pasteur Hospital")){
            mMap = googleMap;

            // Add a marker in Sydney and move the camera
            LatLng sydney = new LatLng(-6.8957417,107.5865955);
            mMap.addMarker(new MarkerOptions().position(sydney).title("Hermina Pasteur Hospital"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }

        if (id.equals("Melinda Hospital 1")){
            mMap = googleMap;

            // Add a marker in Sydney and move the camera
            LatLng sydney = new LatLng(-6.9070202,107.6011145);
            mMap.addMarker(new MarkerOptions().position(sydney).title("Melinda Hospital 1"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }

        if (id.equals("RSIA Harapan Bunda")){
            mMap = googleMap;

            // Add a marker in Sydney and move the camera
            LatLng sydney = new LatLng(-6.9559413,107.6599324);
            mMap.addMarker(new MarkerOptions().position(sydney).title("RSIA Harapan Bunda"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }

        if (id.equals("Special Hospital Maternal and Child Bandung")){
            mMap = googleMap;

            // Add a marker in Sydney and move the camera
            LatLng sydney = new LatLng(-6.9292213,107.598437);
            mMap.addMarker(new MarkerOptions().position(sydney).title("Special Hospital Maternal and Child Bandung"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }

    }
}
