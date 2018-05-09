package com.rpl.happymommy.happymommy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText mUser, mPass;
    private Button btnLogin;
    private TextView tvSignup, tvForgot;

    private FirebaseAuth auth;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUser = (EditText) findViewById(R.id.Username);
        mPass = (EditText) findViewById(R.id.Password);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        tvSignup = (TextView) findViewById(R.id.tvSignup);
        tvForgot = (TextView) findViewById(R.id.tvForgot);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        btnLogin.setOnClickListener(this);
        tvSignup.setOnClickListener(this);
        tvForgot.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();

        //jika auth tidak null langsung ke home
        if (auth.getCurrentUser()!=null){
            startActivity(new Intent(Login.this, Home.class));
        }


    }

    //onClick berdasarkan id
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnLogin){
            loginUser();
        }
        if (view.getId() == R.id.tvSignup){
            startActivity(new Intent(Login.this, Registrasi.class));
        }
        if (view.getId() == R.id.tvForgot){
            startActivity(new Intent(Login.this, LupaPassword.class));
        }
    }

    private void loginUser() {
        final String email = mUser.getText().toString().trim();
        String password = mPass.getText().toString().trim();

        //exception handler bila kosong
        if (email.isEmpty()) {
            mUser.setError("Mohon Isi Email");
            mUser.requestFocus();
            return;
        }
        //exception handler struktur email
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mUser.setError("Struktur Email Salah");
            mUser.requestFocus();
            return;
        }
        //exception handler bila kosong
        if(password.isEmpty()) {
            mPass.setError("Mohon Isi Password");
            mPass.requestFocus();
            return;
        }
        //exception handler panjang password
        if(password.length()<6){
           mPass.setError("Password harus lebih dari 6 karakter");
           mPass.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        //code login
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                    //jika sukses
                if (task.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    //jika yang login admin
                    if (email.equals("happymommyrpl@gmail.com")){
                        startActivity(new Intent(Login.this, HalamanAdmin.class));
                        finish();

                    }else {
                        //jika user biasa
                        progressBar.setVisibility(View.GONE);
                        startActivity(new Intent(Login.this, Home.class));
                        finish();
                    }


                }
                else {
                    //jika gagal
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(Login.this, "Email atau Password Salah", Toast.LENGTH_SHORT).show();


                }
            }
        });
    }

    //ketika klik back maka akan dibersihkan dan keluat aplikasi
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
        System.exit(0);

    }

}
