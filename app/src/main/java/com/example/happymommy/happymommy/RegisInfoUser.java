package com.example.happymommy.happymommy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.happymommy.happymommy.Model.InfoUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisInfoUser extends AppCompatActivity {

    EditText Rnama, Ralamat, Rnohp;
    Button btnSave;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis_info_user);

        Rnama = (EditText)findViewById(R.id.Rnama);
        Ralamat = (EditText)findViewById(R.id.Ralamat);
        Rnohp = (EditText)findViewById(R.id.Rnohp);
        btnSave = (Button) findViewById(R.id.btnSave);


        auth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
       final DatabaseReference table_user = database.getReference("User");

       final FirebaseUser user = auth.getCurrentUser();

       btnSave.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               table_user.addValueEventListener(new ValueEventListener() {

                   @Override
                   public void onDataChange(DataSnapshot dataSnapshot) {
                      InfoUser infoUser = new InfoUser(Rnama.getText().toString(), Ralamat.getText().toString(), Rnohp.getText().toString());
                      table_user.child(user.getUid()).setValue(infoUser);

                      startActivity(new Intent(RegisInfoUser.this, Login.class));
                   }

                   @Override
                   public void onCancelled(DatabaseError databaseError) {

                   }
               });
           }
       });

    }



//    @Override
 //   public void onClick(View view) {
 //       if (view.getId() == R.id.btnSave){
 //          AddInfo();

 //          startActivity(new Intent(RegisInfoUser.this, Login.class));
  //      }
    }

 //   private void AddInfo() {
 //       String nama = Rnama.getText().toString().trim();
  //      String alamat = Ralamat.getText().toString().trim();
   //     String nohp = Rnohp.getText().toString().trim();

     //   InfoUser infoUser = new InfoUser(nama, alamat, nohp);

     //   FirebaseUser user = auth.getCurrentUser();

 //       databaseReference.child(user.getUid()).setValue(infoUser);

    //    Toast.makeText(this, "Data Telah Tersimpan", Toast.LENGTH_SHORT).show();

 //   }

