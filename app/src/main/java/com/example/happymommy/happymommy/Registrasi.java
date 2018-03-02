package com.example.happymommy.happymommy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class Registrasi extends AppCompatActivity implements View.OnClickListener{

    EditText mEmail, mPassword;
    Button btnSubmit;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        mEmail = (EditText) findViewById(R.id.Remail);
        mPassword = (EditText) findViewById(R.id.Rpassword);
        btnSubmit = (Button) findViewById(R.id.btnRegis);

        btnSubmit.setOnClickListener(this);
    }

    private void registerUser() {
        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            //jika email tidak di isi
            Toast.makeText(this, "Mohon Isi Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            //jika password tidak di isi
            Toast.makeText(this, "Mohon Isi Password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Mohon Tunggu Process Registrasi User......");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    finish();
                    startActivity(new Intent(getApplicationContext(), MainMenu.class));

                }else{
                    Toast.makeText(Registrasi.this, "Terdapat Masalah ketika Registrasi Mohon Coba Lagi", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public void onClick(View view) {
    if (view == btnSubmit){
        registerUser();
    }
    }
}
