package com.rpl.happymommy.happymommy;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MenuKontraksi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_kontraksi);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TabLayout tab = findViewById(R.id.tab);
        final ViewPager viewPager = findViewById(R.id.pager);

        //Set Text untuk setiap Tab
        tab.addTab(tab.newTab().setText("Hitung Kontraksi"));
        tab.addTab(tab.newTab().setText("Informasi Kontraksi"));
        tab.setTabGravity(tab.GRAVITY_FILL);

        //Memangil PagerAdapter
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tab.getTabCount());
        //Set ViewPager Adapater
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));

        //Ketika Tab Di pilih
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                //set viewpager sesuai get posisi tab
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
