package com.rpl.happymommy.happymommy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rpl.happymommy.happymommy.Adapter.AdapterRumahsakit;
import com.rpl.happymommy.happymommy.Interface.ItemClickListener;
import com.rpl.happymommy.happymommy.Model.RumahSakit;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class InfoRumahSakit extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private RecyclerView recycler_hospital;
    private RecyclerView.LayoutManager layoutManager;

    private FirebaseRecyclerAdapter<RumahSakit,AdapterRumahsakit> adapter;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_rumah_sakit);

        getSupportActionBar().setTitle("Informasi Rumah Sakit");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Tentukan lokasi Database
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("InfoRumahsakit");

        //Defined recyclerView
        recycler_hospital = findViewById(R.id.recycler_hospital);
        recycler_hospital.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager (this);
        recycler_hospital.setLayoutManager(layoutManager);

        auth = FirebaseAuth.getInstance();

        loadMenu();
        
    }

    //method ambil data database
    private void loadMenu() {

        //code FirebaseRecyclerAdapter <Model,Adapter> + layout customnya
        adapter = new FirebaseRecyclerAdapter<RumahSakit, AdapterRumahsakit>(RumahSakit.class, R.layout.custom_rumahsakit, AdapterRumahsakit.class, databaseReference) {
            //isi data di viewholder
            @Override
            protected void populateViewHolder(AdapterRumahsakit viewHolder, RumahSakit model, int position) {
                //pasang txtHospital ke dari data getDeskripsi model RumahSakit
                viewHolder.txtHospital.setText(model.getDeskripsi());
                //get gambar
                Picasso.with(getBaseContext()).load(model.getImgLokasi()).into(viewHolder.imageView);
                final RumahSakit clickItem = model;

                //buat onClick pake interface ItemClickListener
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //intent
                        Intent Detail = new Intent(InfoRumahSakit.this, DetailRumahSakit.class);
                        Detail.putExtra("IdRumahSakit", adapter.getRef(position).getKey());
                        startActivity(Detail);
                    }
                });

            }
        };

        //pasang adapter
        recycler_hospital.setAdapter(adapter);
    }
}
