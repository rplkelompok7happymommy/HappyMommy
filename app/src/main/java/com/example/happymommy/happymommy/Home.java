package com.example.happymommy.happymommy;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.happymommy.happymommy.Common.Common;
import com.example.happymommy.happymommy.Model.InfoUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth auth;
    private DatabaseReference databaseReference;
    private FirebaseUser mCurrentUser;

    private  TextView fullnama, infoemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Menu");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        auth = FirebaseAuth.getInstance();
        mCurrentUser = auth.getCurrentUser();

        String current_uid = mCurrentUser.getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference("User").child(current_uid);

        View headerView = navigationView.getHeaderView(0);
        fullnama = headerView.findViewById(R.id.fullnama);
        infoemail = headerView.findViewById(R.id.infoemail);
        infoemail.setText(auth.getCurrentUser().getEmail());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String nama = dataSnapshot.child("nama").getValue().toString();
                fullnama.setText(nama);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Apakah Anda Ingin Logout?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                LogoutUser();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            alert.show();
        } else {
            alert.show();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void LogoutUser(){
        auth.signOut();
        if (auth.getCurrentUser() == null){
            startActivity(new Intent(Home.this, Login.class));
            finish();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_akun) {
            // Handle the camera action
            Intent intent = new Intent(Home.this, Akun.class);
            startActivity(intent);

        } else if (id == R.id.nav_changeassword) {
            startActivity(new Intent(Home.this, ChangePassword.class));
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(Home.this, AboutUs.class));
        } else if (id == R.id.nav_logout) {
                LogoutUser();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void InfoKesehatan(View view) {
        startActivity(new Intent(Home.this, OpsiKesehatan.class));
    }

    public void hitungKontraksi(View view) {
        startActivity(new Intent(Home.this, MenuKontraksi.class));
    }

    public void senam(View view) {
        startActivity(new Intent(Home.this, Senam.class));
    }

    public void rumahsakit(View view) {
        startActivity(new Intent(Home.this, InfoRumahSakit.class));
    }
}
