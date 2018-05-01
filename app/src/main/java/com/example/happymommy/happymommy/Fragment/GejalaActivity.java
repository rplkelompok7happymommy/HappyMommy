package com.example.happymommy.happymommy.Fragment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.happymommy.happymommy.Model.Bulan;
import com.example.happymommy.happymommy.Model.RumahSakit;
import com.example.happymommy.happymommy.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class GejalaActivity extends AppCompatActivity {

    TextView aJudul,aIsi ;
    ImageView imageView;

    private DatabaseReference mUserDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gejala);
        this.setTitle("GEJALA");

        aJudul = findViewById(R.id.judul);
        aIsi = findViewById(R.id.isi);
        imageView = findViewById(R.id.imageView3);

        Intent intent = getIntent();
        String id = intent.getStringExtra("IdBulan");

        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("InfoKesehatan").child(id);

        mUserDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Bulan detail = dataSnapshot.getValue(Bulan.class);

                Picasso.with(getBaseContext()).load(detail.getImgGejala()).into(imageView);

                String gejala = dataSnapshot.child("Gejala").getValue().toString();
                aJudul.setText("Gejala");
                aIsi.setText(gejala);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
