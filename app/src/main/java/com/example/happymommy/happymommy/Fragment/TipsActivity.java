package com.example.happymommy.happymommy.Fragment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.happymommy.happymommy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TipsActivity extends AppCompatActivity {

    TextView aJudul,aIsi ;

    private DatabaseReference mUserDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);

        aJudul = findViewById(R.id.judul);
        aIsi = findViewById(R.id.isi);

        Intent intent = getIntent();
        String id = intent.getStringExtra("IdBulan");

        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("InfoKesehatan").child(id);

        mUserDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String tips = dataSnapshot.child("Tips").getValue().toString();
                aJudul.setText("TIPS");
                aIsi.setText(tips);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
