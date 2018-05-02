package com.example.happymommy.happymommy.Fragment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.happymommy.happymommy.Model.Bulan;
import com.example.happymommy.happymommy.Model.RumahSakit;
import com.example.happymommy.happymommy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class TipsActivity extends AppCompatActivity {

    private TextView aJudul,aIsi ;

    private ImageView imageView;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        this.setTitle("TIPS");

        aJudul = findViewById(R.id.judul);
        aIsi = findViewById(R.id.isi);
        imageView = findViewById(R.id.imageView3);

        Intent intent = getIntent();
        String id = intent.getStringExtra("IdBulan");

        databaseReference = FirebaseDatabase.getInstance().getReference().child("InfoKesehatan").child(id);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Bulan detail = dataSnapshot.getValue(Bulan.class);

                Picasso.with(getBaseContext()).load(detail.getImgTips()).into(imageView);
                String tips = dataSnapshot.child("tips").getValue().toString();
                aJudul.setText("TIPS");
                aIsi.setText(tips);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
