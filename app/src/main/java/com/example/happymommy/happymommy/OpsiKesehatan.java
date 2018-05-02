package com.example.happymommy.happymommy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OpsiKesehatan extends AppCompatActivity {

    private Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opsi_kesehatan);

        getSupportActionBar().setTitle("Informasi Kesehatan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OpsiKesehatan.this, JenisKesehatan.class);
                intent.putExtra("Bulan","Bulan1");
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OpsiKesehatan.this, JenisKesehatan.class);
                intent.putExtra("Bulan","Bulan2");
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OpsiKesehatan.this, JenisKesehatan.class);
                intent.putExtra("Bulan","Bulan3");
                startActivity(intent);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OpsiKesehatan.this, JenisKesehatan.class);
                intent.putExtra("Bulan","Bulan4");
                startActivity(intent);
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OpsiKesehatan.this, JenisKesehatan.class);
                intent.putExtra("Bulan","Bulan5");
                startActivity(intent);
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OpsiKesehatan.this, JenisKesehatan.class);
                intent.putExtra("Bulan","Bulan6");
                startActivity(intent);
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OpsiKesehatan.this, JenisKesehatan.class);
                intent.putExtra("Bulan","Bulan7");
                startActivity(intent);
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OpsiKesehatan.this, JenisKesehatan.class);
                intent.putExtra("Bulan","Bulan8");
                startActivity(intent);
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OpsiKesehatan.this, JenisKesehatan.class);
                intent.putExtra("Bulan","Bulan9");
                startActivity(intent);
            }
        });
    }
}
