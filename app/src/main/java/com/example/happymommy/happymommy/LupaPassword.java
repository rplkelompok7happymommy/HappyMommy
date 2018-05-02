package com.example.happymommy.happymommy;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class LupaPassword extends AppCompatActivity implements View.OnClickListener {

    private EditText LupaPass;
    private Button btnLupa;
    private  FirebaseAuth auth;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_password);

        getSupportActionBar().setTitle("Lupa Password");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LupaPass = (EditText)findViewById(R.id.LupaPass);
        btnLupa = (Button) findViewById(R.id.btnlupa);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        btnLupa.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnlupa){
            progressBar.setVisibility(View.VISIBLE);
            ResetPassword(LupaPass.getText().toString());
        }
}

    private void ResetPassword(final String email) {
        auth.sendPasswordResetEmail(email).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(LupaPassword.this, "Reset Password Telah dikirim ke Email"+ email, Toast.LENGTH_SHORT).show();
                }else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(LupaPassword.this, "Gagal Kirim Reset Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    }
