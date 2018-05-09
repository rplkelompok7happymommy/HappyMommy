package com.rpl.happymommy.happymommy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.rpl.happymommy.happymommy.Model.InfoUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditProfile extends AppCompatActivity {

    private EditText aNama, aAlamat, aNohp;

    private DatabaseReference mUserDatabase;
    private FirebaseUser mCurrentUser;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        getSupportActionBar().setTitle("Edit Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        aNama = findViewById(R.id.aNama);
        aAlamat = findViewById(R.id.aAlamat);
        aNohp = findViewById(R.id.aNohp);

        //intance dari auth firebase
        auth = FirebaseAuth.getInstance();

        //get current user
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();

        //get UID dari current User
        String current_uid = mCurrentUser.getUid();

        //Menentukan lokasi databse
        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("User").child(current_uid);

        //untuk merubah data/menambah
        mUserDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //set string berdasarkan data database
                String nama = dataSnapshot.child("nama").getValue().toString();
                String alamat = dataSnapshot.child("alamat").getValue().toString();
                String nohp = dataSnapshot.child("nohp").getValue().toString();

                //pasang
                aNama.setText(nama);
                aAlamat.setText(alamat);
                aNohp.setText(nohp);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    //ketika klik button save, jalankan method save
    public void Save(View view) {
        save();
    }

    private void save() {
        String nama = aNama.getText().toString().trim();
        String alamat = aAlamat.getText().toString().trim();
        String nohp = aNohp.getText().toString().trim();

        //exception handler bila field kosong
        if (nama.isEmpty()) {
            aNama.setError("Mohon Isi Nama");
            aNama.requestFocus();
            return;
        }

        //exception handler bila field kosong
        if(alamat.isEmpty()) {
            aAlamat.setError("Mohon Isi Alamat");
            aAlamat.requestFocus();
            return;
        }

        //exception handler bila field kosong
        if (nohp.isEmpty()) {
            aNohp.setError("Mohon Isi No HP");
            aNohp.requestFocus();
            return;
        }

        //menentukan lokasi database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        //get current user
        final FirebaseUser user = auth.getCurrentUser();

        //utuk tambah data
        table_user.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //tambah data ke database berdasarkan model InfoUser
                        InfoUser infoUser = new InfoUser(aNama.getText().toString(), aAlamat.getText().toString(), aNohp.getText().toString());
                        table_user.child(user.getUid()).setValue(infoUser);

                        Toast.makeText(EditProfile.this, "Sukses Edit", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        }
