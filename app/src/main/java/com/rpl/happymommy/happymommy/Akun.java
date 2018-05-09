package com.rpl.happymommy.happymommy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Akun extends AppCompatActivity {

    private TextView aNama, aAlamat, aNohp;

    private DatabaseReference mUserDatabase;
    private FirebaseUser mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akun);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //tomobl back di actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        aNama = findViewById(R.id.aNama);
        aAlamat = findViewById(R.id.aAlamat);
        aNohp = findViewById(R.id.aNohp);

        //Untuk ambil CurrentUser
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();

        //Untuk ambil UID dari Current User
        String current_uid = mCurrentUser.getUid();

        //Menentukan Letak Database
        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("User").child(current_uid);

        //Untuk Merubah Data
        mUserDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //Memangil data nama,alamat,nohp (database), kemudian di rubah ke string
                String nama = dataSnapshot.child("nama").getValue().toString();
                String alamat = dataSnapshot.child("alamat").getValue().toString();
                String nohp = dataSnapshot.child("nohp").getValue().toString();

                //kemudian dipasang
                aNama.setText(nama);
                aAlamat.setText(alamat);
                aNohp.setText(nohp);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void EditProfile(View view) {
        startActivity(new Intent(Akun.this, EditProfile.class));
    }
}
