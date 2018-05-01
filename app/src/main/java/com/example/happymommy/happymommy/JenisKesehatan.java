package com.example.happymommy.happymommy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.happymommy.happymommy.Fragment.GejalaActivity;
import com.example.happymommy.happymommy.Fragment.KondisiActivity;
import com.example.happymommy.happymommy.Fragment.MakananActivity;
import com.example.happymommy.happymommy.Fragment.TipsActivity;

public class JenisKesehatan extends AppCompatActivity {

    Button btnTips,btnMakanan,btnKondisi,btnGejala;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jenis_kesehatan);


        btnTips = findViewById(R.id.btnTips);
        btnMakanan = findViewById(R.id.btnMakanan);
        btnKondisi = findViewById(R.id.btnKondisi);
        btnGejala = findViewById(R.id.btnGejala);

        Intent intent = getIntent();
        final String OpsiKesehatan = intent.getStringExtra("Bulan");
        this.setTitle(OpsiKesehatan);

        btnTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JenisKesehatan.this, TipsActivity.class);
                intent.putExtra("IdBulan", OpsiKesehatan);
                startActivity(intent);
            }
        });

        btnMakanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JenisKesehatan.this, MakananActivity.class);
                intent.putExtra("IdBulan", OpsiKesehatan);
                startActivity(intent);
            }
        });

        btnKondisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JenisKesehatan.this, KondisiActivity.class);
                intent.putExtra("IdBulan", OpsiKesehatan);
                startActivity(intent);
            }
        });

        btnGejala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JenisKesehatan.this, GejalaActivity.class);
                intent.putExtra("IdBulan", OpsiKesehatan);
                startActivity(intent);
            }
        });
    }
}
