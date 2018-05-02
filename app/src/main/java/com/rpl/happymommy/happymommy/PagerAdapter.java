package com.rpl.happymommy.happymommy;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.rpl.happymommy.happymommy.Fragment.HitungKontraksiFragment;
import com.rpl.happymommy.happymommy.Fragment.InfoKontraksiFragment;

/**
 * Created by M. Satria Wibawa on 07/04/2018.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    //Membuat variable int mNumOfTabs
    private int mNumOfTabs;

    //Construction
    public PagerAdapter(FragmentManager fm, int mNumOfTabs) {
        super(fm);
        this.mNumOfTabs = mNumOfTabs;
    }

    //Posisi Fragment
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new HitungKontraksiFragment();
            case 1:
                return new InfoKontraksiFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}

