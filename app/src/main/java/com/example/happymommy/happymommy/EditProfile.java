package com.example.happymommy.happymommy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.happymommy.happymommy.Model.InfoUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditProfile extends AppCompatActivity {

    EditText aNama, aAlamat, aNohp;

    private DatabaseReference mUserDatabase;
    private FirebaseUser mCurrentUser;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        aNama = findViewById(R.id.aNama);
        aAlamat = findViewById(R.id.aAlamat);
        aNohp = findViewById(R.id.aNohp);

        auth = FirebaseAuth.getInstance();


        final FirebaseUser user = auth.getCurrentUser();


        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();

        String current_uid = mCurrentUser.getUid();

        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("User").child(current_uid);

        mUserDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String nama = dataSnapshot.child("nama").getValue().toString();
                String alamat = dataSnapshot.child("alamat").getValue().toString();
                String nohp = dataSnapshot.child("nohp").getValue().toString();

                aNama.setText(nama);
                aAlamat.setText(alamat);
                aNohp.setText(nohp);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void Save(View view) {
        save();
    }

    private void save() {
        String nama = aNama.getText().toString().trim();
        String alamat = aAlamat.getText().toString().trim();
        String nohp = aNohp.getText().toString().trim();

        if (nama.isEmpty()) {
            aNama.setError("Mohon Isi Nama");
            aNama.requestFocus();
            return;
        }


        if(alamat.isEmpty()) {
            aAlamat.setError("Mohon Isi Alamat");
            aAlamat.requestFocus();
            return;
        }


        if (nohp.isEmpty()) {
            aNohp.setError("Mohon Isi No HP");
            aNohp.requestFocus();
            return;
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        final FirebaseUser user = auth.getCurrentUser();

        table_user.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
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
