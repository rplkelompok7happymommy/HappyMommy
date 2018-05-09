package com.rpl.happymommy.happymommy;

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
import com.google.firebase.auth.FirebaseUser;

public class ChangePassword extends AppCompatActivity implements View.OnClickListener{

    private EditText PassBaru;
    private Button btnChangePassword;

    //untuk authentication
    private FirebaseAuth auth;

    //untuk loading
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        //untuk tombol back di actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        PassBaru = (EditText) findViewById(R.id.PassBaru);
        btnChangePassword = (Button)findViewById(R.id.btnChangePassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        btnChangePassword.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        //jika di klik dan idnya sesuai
        if (view.getId() == R.id.btnChangePassword){
            GantiPassword();
        }
    }

    private void GantiPassword() {
        String newPassword = PassBaru.getText().toString().trim();

        //exception handling bila tidak di isi
        if (newPassword.isEmpty()) {
            PassBaru.setError("Mohon Isi Dengan Password Baru");
            PassBaru.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        //untuk user yang sedang login
        FirebaseUser user = auth.getCurrentUser();

        //code update password
        user.updatePassword(newPassword).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //jika sukses
                if (task.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(ChangePassword.this, "Password Berhasil di Reset", Toast.LENGTH_SHORT).show();

                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(ChangePassword.this, "Gagal Ganti Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
