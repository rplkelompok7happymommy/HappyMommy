package com.rpl.happymommy.happymommy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class HalamanAdmin extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth auth;
    private Button keluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_admin);

        keluar = findViewById(R.id.keluar);

        keluar.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();
    }

    public void LogoutUser(){
        auth.signOut();
        if (auth.getCurrentUser() == null){
            startActivity(new Intent(HalamanAdmin.this, Login.class));
            finish();
        }
    }

    public void rumahsakit(View view) {
        startActivity(new Intent(HalamanAdmin.this, InfoRumahSakit.class));
    }

    @Override
    public void onBackPressed() {
        LogoutUser();

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.keluar){
            LogoutUser();
        }
    }
}
