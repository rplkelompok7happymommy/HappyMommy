package com.rpl.happymommy.happymommy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.rpl.happymommy.happymommy.Model.RumahSakit;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DetailRumahSakit extends AppCompatActivity {

    private TextView mAddress, mHours, mPhone, mJudul, mDummy;
    private ImageView imageView;

    private EditText mComment;

    private String id = "";

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_rumah_sakit);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("InfoRumahsakit");

        mJudul = findViewById(R.id.judul);
        mAddress = findViewById(R.id.address);
        mHours = findViewById(R.id.hours);
        mPhone = findViewById(R.id.phone);
        mDummy = findViewById(R.id.dummy);
        imageView = findViewById(R.id.imageView2);

        mDummy.setVisibility(View.INVISIBLE);

        mComment = findViewById(R.id.input_comment);

        if (getIntent() != null) {
            id = getIntent().getStringExtra("IdRumahSakit");
        }
        if (!id.isEmpty()) {

            mDummy.setText(id);
            getDetail(id);
        }

    }


    private void getDetail(String id) {
        databaseReference.child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                RumahSakit detail = dataSnapshot.getValue(RumahSakit.class);

                Picasso.with(getBaseContext()).load(detail.getImgLokasi()).into(imageView);

                mJudul.setText(detail.getDeskripsi());
                mAddress.setText(detail.getAddress());
                mHours.setText(detail.getHours());
                mPhone.setText(detail.getPhone());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void btnComment(View view) {
        String A = mJudul.getText().toString();
        String B = mDummy.getText().toString();
        Intent komentar = new Intent(DetailRumahSakit.this, CommentActivity.class);
        komentar.putExtra("IdKonten", B);
        komentar.putExtra("IdDeskripsi", A);
        startActivity(komentar);
    }

    public void btnMaps(View view) {
        String A = mJudul.getText().toString();
        Intent peta = new Intent(DetailRumahSakit.this, MapsActivity.class);
        peta.putExtra("Peta", A);
        startActivity(peta);
    }

    public void btnSubmit(View view) {

    }

}