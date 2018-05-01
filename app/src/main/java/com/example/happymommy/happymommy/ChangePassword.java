package com.example.happymommy.happymommy;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePassword extends AppCompatActivity implements View.OnClickListener{

    EditText PassBaru;
    Button btnChangePassword;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        PassBaru = (EditText) findViewById(R.id.PassBaru);
        btnChangePassword = (Button)findViewById(R.id.btnChangePassword);

        btnChangePassword.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnChangePassword){
            GantiPassword(PassBaru.getText().toString());
        }
    }

    private void GantiPassword(String newPassword) {
        FirebaseUser user = auth.getCurrentUser();
        user.updatePassword(newPassword).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ChangePassword.this, "Password Berhasil di Reset", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(ChangePassword.this, "Gagal Ganti Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
