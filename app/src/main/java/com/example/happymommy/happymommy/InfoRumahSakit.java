package com.example.happymommy.happymommy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.happymommy.happymommy.Adapter.AdapterRumahsakit;
import com.example.happymommy.happymommy.Interface.ItemClickListener;
import com.example.happymommy.happymommy.Model.RumahSakit;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class InfoRumahSakit extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    RecyclerView recycler_hospital;
    RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<RumahSakit,AdapterRumahsakit> adapter;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_rumah_sakit);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("InfoRumahsakit");

        recycler_hospital = findViewById(R.id.recycler_hospital);
        recycler_hospital.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager (this);
        recycler_hospital.setLayoutManager(layoutManager);

        auth = FirebaseAuth.getInstance();

        loadMenu();
        
    }

    private void loadMenu() {

        adapter = new FirebaseRecyclerAdapter<RumahSakit, AdapterRumahsakit>(RumahSakit.class, R.layout.custom_rumahsakit, AdapterRumahsakit.class, databaseReference) {
            @Override
            protected void populateViewHolder(AdapterRumahsakit viewHolder, RumahSakit model, int position) {
                viewHolder.txtHospital.setText(model.getDeskripsi());
                Picasso.with(getBaseContext()).load(model.getImgLokasi()).into(viewHolder.imageView);
                final RumahSakit clickItem = model;

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent Detail = new Intent(InfoRumahSakit.this, DetailRumahSakit.class);
                        Detail.putExtra("IdRumahSakit", adapter.getRef(position).getKey());
                        startActivity(Detail);
                    }
                });

            }
        };

        recycler_hospital.setAdapter(adapter);
    }
}
