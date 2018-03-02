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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText mUser, mPass;
    private TextView mSignup;
    private Button btnMasuk;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        if (firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), MainMenu.class));
        }

        mUser = (EditText) findViewById(R.id.Username);
        mPass = (EditText) findViewById(R.id.Password);
        mSignup = (TextView) findViewById(R.id.Signup);
        btnMasuk = (Button) findViewById(R.id.btnLogin);

        btnMasuk.setOnClickListener(this);
        mSignup.setOnClickListener(this);
    }

    private void userLogin (){
        String email = mUser.getText().toString().trim();
        String password = mPass.getText().toString().trim();

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

        progressDialog.setMessage("Mohon Tunggu ......");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    finish();
                    startActivity(new Intent(getApplicationContext(), MainMenu.class));
                }else{
                    Toast.makeText(Login.this, "Email atau Password Salah", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == btnMasuk){
            userLogin();
        }
        if (view == mSignup){
            finish();
            startActivity(new Intent(this, Registrasi.class));
        }
    }
}
