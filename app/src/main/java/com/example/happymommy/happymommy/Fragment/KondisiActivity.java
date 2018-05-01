package com.example.happymommy.happymommy.Fragment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.happymommy.happymommy.Model.Bulan;
import com.example.happymommy.happymommy.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class KondisiActivity extends AppCompatActivity {

    TextView aJudul,aIsi ;
    ImageView imageView;

    private DatabaseReference mUserDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kondisi);
        this.setTitle("KONDISI JANIN");


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

                Picasso.with(getBaseContext()).load(detail.getImgPerkembangan()).into(imageView);

                String perkembangan = dataSnapshot.child("perkembangan").getValue().toString();
                aJudul.setText("Perkembangan");
                aIsi.setText(perkembangan);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
