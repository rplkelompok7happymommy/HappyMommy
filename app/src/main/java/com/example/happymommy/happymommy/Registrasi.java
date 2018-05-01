package com.example.happymommy.happymommy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Registrasi extends AppCompatActivity implements View.OnClickListener{

    EditText mUser, mPass, mAlamat, mNohp;
    Button btnRegis;

    private FirebaseAuth auth;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        progressDialog = new ProgressDialog(this);

        mUser = (EditText)findViewById(R.id.Remail);
        mPass = (EditText)findViewById(R.id.Rpassword);
        mAlamat = (EditText)findViewById(R.id.Ralamat);
        mNohp = (EditText)findViewById(R.id.Rnohp);
        btnRegis = (Button) findViewById(R.id.btnRegis);

        btnRegis.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();


    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnRegis){

            signUpUser();
        }

    }

    private void signUpUser() {
        String email = mUser.getText().toString().trim();
        String password = mPass.getText().toString().trim();
        String Alamat = mAlamat.getText().toString().trim();
        String Nohp = mAlamat.getText().toString().trim();

        if (email.isEmpty()) {
            mUser.setError("Mohon Isi Email");
            mUser.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mUser.setError("Struktur Email Salah");
            mUser.requestFocus();
            return;
        }

        if(password.isEmpty()) {
            mPass.setError("Mohon Isi Password");
            mPass.requestFocus();
            return;
        }

        if(password.length()<6){
            mPass.setError("Password harus lebih dari 6 karakter");
            mPass.requestFocus();
            return;
        }

        if (Alamat.isEmpty()) {
            mAlamat.setError("Mohon Isi Alamat");
            mAlamat.requestFocus();
            return;
        }

        if (Nohp.isEmpty()) {
            mNohp.setError("Mohon Isi Nohp");
            mNohp.requestFocus();
            return;
        }

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                    if (!task.isSuccessful()){

                        Toast.makeText(Registrasi.this, "Terjadi Masalah Mohon Coba lagi", Toast.LENGTH_SHORT).show();

                    } else {

                        startActivity(new Intent(Registrasi.this, RegisInfoUser.class));
                    }
            }
        });


    }
}