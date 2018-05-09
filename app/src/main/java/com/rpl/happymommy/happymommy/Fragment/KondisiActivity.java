package com.rpl.happymommy.happymommy.Fragment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.rpl.happymommy.happymommy.Model.Bulan;
import com.rpl.happymommy.happymommy.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class KondisiActivity extends AppCompatActivity {

    private TextView aJudul,aIsi ;
    private ImageView imageView;

    //Get Referensi Database
    private DatabaseReference mUserDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kondisi);
        this.setTitle("KONDISI JANIN");


        aJudul = findViewById(R.id.judul);
        aIsi = findViewById(R.id.isi);
        imageView = findViewById(R.id.imageView3);

        //Get Intent
        Intent intent = getIntent();
        String id = intent.getStringExtra("IdBulan");

        //Menentukan reference atau letak database
        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("InfoKesehatan").child(id);

        //Untuk Merubah Data/ Menambah data
        mUserDatabase.addValueEventListener(new ValueEventListener() {

            //ketika data dirubah
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //buat variable baru untuk class model bulan
                Bulan detail = dataSnapshot.getValue(Bulan.class);
                //untuk get gambar
                Picasso.with(getBaseContext()).load(detail.getImgPerkembangan()).into(imageView);
                //untuk ambil data perkembangan (database)
                String perkembangan = dataSnapshot.child("perkembangan").getValue().toString();
                aJudul.setText("Perkembangan");
                aIsi.setText(perkembangan);

            }
            //ketika batal
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
