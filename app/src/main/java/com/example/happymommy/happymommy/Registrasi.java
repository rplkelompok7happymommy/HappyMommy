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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.happymommy.happymommy.Model.InfoUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Registrasi extends AppCompatActivity implements View.OnClickListener{

    private EditText mUser, mPass, mAlamat, mNohp;
    private Button btnRegis;

    private EditText Rnama, Ralamat, Rnohp;
    private FirebaseAuth auth;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        mUser = (EditText)findViewById(R.id.Remail);
        mPass = (EditText)findViewById(R.id.Rpassword);
        mAlamat = (EditText)findViewById(R.id.Ralamat);
        mNohp = (EditText)findViewById(R.id.Rnohp);
        btnRegis = (Button) findViewById(R.id.btnRegis);

        Rnama = (EditText)findViewById(R.id.Rnama);
        Ralamat = (EditText)findViewById(R.id.Ralamat);
        Rnohp = (EditText)findViewById(R.id.Rnohp);

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

        progressBar.setVisibility(View.VISIBLE);

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()){


                        auth = FirebaseAuth.getInstance();
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        final DatabaseReference table_user = database.getReference("User");

                        final FirebaseUser user = auth.getCurrentUser();

                        table_user.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                InfoUser infoUser = new InfoUser(Rnama.getText().toString(), Ralamat.getText().toString(), Rnohp.getText().toString());
                                table_user.child(user.getUid()).setValue(infoUser);

                                startActivity(new Intent(Registrasi.this, Login.class));
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                        progressBar.setVisibility(View.GONE);

                    } else {
                        if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                            Toast.makeText(getApplicationContext(), "Email Telah Digunakan", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Terdapat Error", Toast.LENGTH_SHORT).show();
                        }
                    }
            }
        });


    }
}