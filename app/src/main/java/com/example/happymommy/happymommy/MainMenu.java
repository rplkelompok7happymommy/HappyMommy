package com.example.happymommy.happymommy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class MainMenu extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;

    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        firebaseAuth = FirebaseAuth.getInstance();

        btnLogout = (Button) findViewById(R.id.Logout);

        btnLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(getApplicationContext(), Login.class));
    }
}
